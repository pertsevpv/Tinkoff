package p3

val res: Array<Long> = Array(3) { 0L }

fun testPull(num: Int) {
    repeat(100) {
        val start = System.nanoTime()
        PullThread(num * 10).apply {
            start()
        }.join()
        res[num - 1] += System.nanoTime() - start
    }
}

fun main() {
    for (i in 1..3)
        testPull(i)

    // Среднее время работы разных пулов
    println("pull = 10")
    println(res[0] / 100)
    println()

    println("pull = 20")
    println(res[1] / 100)
    println()

    println("pull = 30")
    println(res[2] / 100)
    println()
}