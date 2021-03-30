package p3

fun test(pull: Int): Long {
    val start = System.nanoTime()
    PullThread(pull).apply {
        start()
    }.join()
    return (System.nanoTime() - start)
}

fun main() {
    val res: Array<Long> = Array(3) { 0L }
    repeat(100) {
        res[0] += test(10)
        res[1] += test(20)
        res[2] += test(30)
    }
    // Среднее время работы разных пулов
    println("pull = 20")
    println(res[1] / 100)
    println()

    println("pull = 10")
    println(res[0] / 100)
    println()

    println("pull = 30")
    println(res[2] / 100)
    println()
}