sealed class CoffeeMachineState {
    object Idle : CoffeeMachineState() {
        val timestamp: Long = System.currentTimeMillis()
    }

    object SelectingCoffee : CoffeeMachineState()
    object ReturningCoins : CoffeeMachineState()
    object MakingCoffee : CoffeeMachineState()
    data class ServingCoffee(val type: String) : CoffeeMachineState()
    object SayingThanks : CoffeeMachineState()
    data class Error(val message: String) : CoffeeMachineState()
}