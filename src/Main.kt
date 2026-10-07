//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// punto de partida de la app
fun main() {
    println("--- Encendiendo la máquina ---")
    StateMachine.setState(CoffeeMachineState.Idle)

    println("\n--- Sirviendo cafe ---")
    StateMachine.setState(CoffeeMachineState.ServingCoffee)

}