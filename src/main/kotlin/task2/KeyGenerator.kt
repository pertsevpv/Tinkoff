package task2

import kotlin.random.Random

fun CharArray.join() =
    this.joinToString(separator = "")


class KeyGenerator {

    fun generateKey(): String {
        val block: Array<CharArray> = Array(3) { "00000".toCharArray() }
        for (i in block.indices) {
            for (c in 0 until 5) {
                block[i][c] = Random.nextInt('A'.toInt(), 'Z'.toInt() + 1).toChar()
            }
        }
        return "${block[0].join()}-${block[1].join()}-${block[2].join()}"
    }
}

fun main() {
    print(KeyGenerator().generateKey())
}