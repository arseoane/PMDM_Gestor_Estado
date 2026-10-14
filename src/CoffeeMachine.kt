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

    fun selectCoffee(type: String = "Nescafé") {
        stateMachine.selectCoffee(type)
        currentState = stateMachine.currentState
    }

    fun cancelOrder() {
        stateMachine.cancelOrder()
        currentState = stateMachine.currentState
    }

    fun makeCoffee() {
        println("Estado actual: $currentState")

        when (currentState) {
            is CoffeeMachineState.Idle -> {
                val idleState = currentState as CoffeeMachineState.Idle
                println("Máquina encendida desde: ${idleState.timestamp}. Empezando a hacer café...")
                insertCoins(1)
                selectCoffee("Nescafé")
                Thread.sleep(2000)
                stateMachine.serveCoffee("Nescafé")
                stateMachine.finishService()
                currentState = stateMachine.currentState
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

fun main() {
    CoffeeMachine.makeCoffee()
}
