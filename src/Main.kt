fun gestor_estados(a: Boolean, b: Boolean){
    if (a == true){
        if (b == true){
            estadoprint(3)
            println("Fin")
        } else if (b == false){
            estadoprint(4)
            estadoprint(1)
        }
    }

}

fun estadoprint(estado: Int){
    println("Estado " + estado)
}

fun main() {
    gestor_estados(true,false)
}