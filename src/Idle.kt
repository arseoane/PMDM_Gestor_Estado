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

    fun selectCoffee(type: String = "Nescafé") {
        when (currentState) {
            is CoffeeMachineState.SelectingCoffee -> {
                currentState = CoffeeMachineState.MakingCoffee
                println("[StateMachine] Seleccionando café: $type.")
            }
            else -> println("[StateMachine] Debes estar en selección para pedir un café.")
        }
    }

    fun serveCoffee(type: String = "Nescafé") {
        when (currentState) {
            is CoffeeMachineState.MakingCoffee -> {
                currentState = CoffeeMachineState.ServingCoffee(type)
                println("[StateMachine] Café servido: $type")
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