package lesson4

fun test(col: Iterable<Any>) {
    col.forEach {
        println(it)
    }
    line()
}

fun line() =
    println("\n----------///----------\n")

fun main() {
    test(Service().f5a())
    test(Service().f5b())
    Service().f5c().forEach {
        println(it)
    }
    line()
    println(Service().f5d())
}