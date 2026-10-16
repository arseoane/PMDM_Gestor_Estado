class StateMachine {
    var currentState: CoffeeMachineState = CoffeeMachineState.Idle

    fun insertCoins(amount: Int = 1) {
        if (amount <= 0) {
            currentState = CoffeeMachineState.Error("No se ha introducido dinero válido.")
            return
        }

        when (currentState) {
            is CoffeeMachineState.Idle -> {
                currentState = CoffeeMachineState.SelectingCoffee
                println("[StateMachine] Monedas insertadas. Estado actual: $currentState")
            }
            else -> println("[StateMachine] No se puede introducir dinero en el estado actual: $currentState")
        }
    }

    fun cancelOrder() {
        when (currentState) {
            is CoffeeMachineState.SelectingCoffee -> {
                currentState = CoffeeMachineState.ReturningCoins
                println("[StateMachine] Pedido cancelado. Se están devolviendo las monedas.")
            }
            else -> println("[StateMachine] No hay un pedido activo para cancelar.")
        }
    }

    fun completeCoinReturn() {
        when (currentState) {
            is CoffeeMachineState.ReturningCoins -> {
                currentState = CoffeeMachineState.Idle
                println("[StateMachine] Monedas devueltas. Estado actual: $currentState")
            }
            else -> println("[StateMachine] No hay monedas pendientes de devolución.")
        }
    }

    fun selectCoffee(type: CoffeeType) {
        when (currentState) {
            is CoffeeMachineState.SelectingCoffee -> {
                currentState = CoffeeMachineState.MakingCoffee(type)
                println("[StateMachine] Seleccionando café: ${type.displayName}.")
            }
            else -> println("[StateMachine] Debes estar en selección para pedir un café.")
        }
    }

    fun serveCoffee() {
        when (val state = currentState) {
            is CoffeeMachineState.MakingCoffee -> {
                currentState = CoffeeMachineState.ServingCoffee(state.type)
                println("[StateMachine] Café servido: ${state.type.displayName}")
            }
            else -> println("[StateMachine] El café no puede servirse en este estado: $currentState")
        }
    }

    fun finishService() {
        when (currentState) {
            is CoffeeMachineState.ServingCoffee -> {
                currentState = CoffeeMachineState.SayingThanks
                println("[StateMachine] Gracias por usar la máquina.")
            }
            else -> println("[StateMachine] No hay un café servido para cerrar.")
        }
    }

    fun completeThanks() {
        when (currentState) {
            is CoffeeMachineState.SayingThanks -> {
                currentState = CoffeeMachineState.Idle
                println("[StateMachine] Agradecimiento completado. Estado actual: $currentState")
            }
            else -> println("[StateMachine] No hay un agradecimiento pendiente.")
        }
    }

    fun resetToIdle() {
        currentState = CoffeeMachineState.Idle
        println("[StateMachine] Máquina devuelta a Idle.")
    }
}

object Idle {
    val timestamp: Long = System.currentTimeMillis()

    fun onEnter(stateMachine: StateMachine) {
        println("[Idle] Entrando en estado Idle a las $timestamp")
        println("[Idle] La máquina está lista para hacer café.")
        stateMachine.resetToIdle()
    }

    init {
        println("[Idle] ejecutando init")
    }
}