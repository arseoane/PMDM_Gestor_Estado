object Idle : CoffeeMachineState() {
    val timestamp: Long = System.currentTimeMillis()
    fun onEnter(stateMachine: StateMachine) {
        println("[Idle] Entrando en estado Idle a las $timestamp")
        println("[Idle] La máquina está lista para hacer café.")
    }

    init {
        println("[Idle] ejecutando init")
    }
}