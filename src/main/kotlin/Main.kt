fun test(col: Iterable<Any>) {
    col.forEach {
        println(it)
    }
    println("\n----------///----------\n")
}

fun test(map: Map<String, Any>) {
    map.forEach{
        println(it)
    }
    println("\n----------///----------\n")
}

fun main() {
    test(Service().f5a())
    test(Service().f5b())
    test(Service().f5c())
    println(Service().f5d())
}