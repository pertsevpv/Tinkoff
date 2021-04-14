package task2

import kotlin.random.Random

fun CharArray.join() =
    this.joinToString(separator = "")

class KeyGenerator {

    fun generateKey(): String {
        val block: Array<CharArray> = Array(3) { CharArray(5) { '9' } }
        block.forEach { bl ->
            bl.forEachIndexed { index, _ ->
                bl[index] = Random.nextInt('A'.toInt(), 'Z'.toInt() + 1).toChar()
            }
        }
        return "${block[0].join()}-${block[1].join()}-${block[2].join()}"
    }
}