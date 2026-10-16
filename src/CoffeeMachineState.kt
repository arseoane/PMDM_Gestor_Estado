enum class CoffeeType(val displayName: String) {
    CAPUCCINO("Capuccino"),
    AMERICANO("Americano"),
    CHOCOLATE("Con chocolate")
}

sealed class CoffeeMachineState {
    object Idle : CoffeeMachineState() {
        val timestamp: Long = System.currentTimeMillis()
    }

    object SelectingCoffee : CoffeeMachineState()
    object ReturningCoins : CoffeeMachineState()
    data class MakingCoffee(val type: CoffeeType) : CoffeeMachineState()
    data class ServingCoffee(val type: CoffeeType) : CoffeeMachineState()
    object SayingThanks : CoffeeMachineState()
    data class Error(val message: String) : CoffeeMachineState()
}