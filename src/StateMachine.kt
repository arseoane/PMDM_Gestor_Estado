/**
 * Singleton que maneja lo que ocurre en cada estado
 */
object StateMachine {
    public var currentState: CoffeeMachineState = CoffeeMachineState.Idle

    fun setState(newState: CoffeeMachineState) {
        if (isValidTransition(currentState, newState)) {
            currentState = newState
            updateState()
        } else {
            println("Transición inválida de $currentState a $newState")
        }
    }

    private fun isValidTransition(from: CoffeeMachineState, to: CoffeeMachineState): Boolean {
        return when (from) {
            CoffeeMachineState.Idle -> to == CoffeeMachineState.MakingCoffee
            CoffeeMachineState.MakingCoffee -> to == CoffeeMachineState.ServingCoffee
            CoffeeMachineState.ServingCoffee -> to == CoffeeMachineState.Idle
            is CoffeeMachineState.Error -> to == CoffeeMachineState.Idle
            else -> false
        }
    }

    fun getState(): CoffeeMachineState {
        return currentState
    }

    fun updateState() {
        println("[StateMachine] Estado actual: $currentState")
        currentState.onEnter(this)
    }

}

/*fun clean() {
    println("Limpiando la máquina...")
    currentState = CoffeeMachineState.Idle
    println("Máquina limpia. Estado: $currentState")
}*/

