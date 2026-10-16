/**
 * Singleton que maneja lo que ocurre en cada estado
 */
object CoffeeMachine {
    var currentState: CoffeeMachineState = CoffeeMachineState.Idle
    private val stateMachine = StateMachine()

    init {
        stateMachine.currentState = currentState
    }

    fun insertCoins(amount: Int = 1) {
        stateMachine.insertCoins(amount)
        currentState = stateMachine.currentState
    }

    fun selectCoffee(type: CoffeeType) {
        stateMachine.selectCoffee(type)
        currentState = stateMachine.currentState
    }

    fun cancelOrder() {
        stateMachine.cancelOrder()
        currentState = stateMachine.currentState
    }

    fun completeCoinReturn() {
        stateMachine.completeCoinReturn()
        currentState = stateMachine.currentState
    }

    fun serveCoffee() {
        stateMachine.serveCoffee()
        currentState = stateMachine.currentState
    }

    fun finishService() {
        stateMachine.finishService()
        currentState = stateMachine.currentState
    }

    fun completeThanks() {
        stateMachine.completeThanks()
        currentState = stateMachine.currentState
    }

    fun makeCoffee(type: CoffeeType) {
        println("Estado actual: $currentState")

        when (currentState) {
            is CoffeeMachineState.Idle -> {
                val idleState = currentState as CoffeeMachineState.Idle
                println("Máquina encendida desde: ${idleState.timestamp}. Empezando a hacer café...")
                insertCoins(1)
                selectCoffee(type)
                Thread.sleep(2000)
                serveCoffee()
                finishService()
                completeThanks()
                println("¡Café listo! Estado: $currentState")
            }
            is CoffeeMachineState.SelectingCoffee -> {
                println("La máquina está esperando confirmación del café.")
            }
            is CoffeeMachineState.MakingCoffee -> {
                println("¡Espera! La máquina ya está haciendo café.")
            }
            is CoffeeMachineState.ServingCoffee -> {
                println("Ya hay café servido. Por favor, toma tu café.")
            }
            is CoffeeMachineState.SayingThanks -> {
                println("La máquina ya ha agradecido el servicio.")
            }
            is CoffeeMachineState.Error -> {
                println("La máquina tiene un error: ${(currentState as CoffeeMachineState.Error).message}")
            }
            is CoffeeMachineState.ReturningCoins -> {
                println("La máquina está devolviendo monedas.")
            }
        }
    }

    fun clean() {
        println("Limpiando la máquina...")
        stateMachine.resetToIdle()
        currentState = stateMachine.currentState
        println("Máquina limpia. Estado: $currentState")
    }
}

private fun readCoffeeType(): CoffeeType? {
    println("Elige un café:")
    println("1. Capuccino")
    println("2. Americano")
    println("3. Con chocolate")

    while (true) {
        when (readLine()?.trim()) {
            "1" -> return CoffeeType.CAPUCCINO
            "2" -> return CoffeeType.AMERICANO
            "3" -> return CoffeeType.CHOCOLATE
            null -> {
                println("No se recibió ninguna opción.")
                return null
            }
            else -> println("Opción no válida. Elige 1, 2 o 3:")
        }
    }
}

fun main() {
    val coffeeType = readCoffeeType() ?: return
    CoffeeMachine.makeCoffee(coffeeType)
}
