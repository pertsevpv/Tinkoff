package p2

import kotlin.random.Random

class BooleanList {
    @Volatile
    private var list = mutableListOf<Boolean>()

    @Synchronized
    fun add() {
        list.add(Random.nextBoolean())
        println("added new value")
    }

    @Synchronized
    fun count(): String {
        var t = 0
        var f = 0
        for (b in list) {
            if (b) t++
            else f++
        }
        return StringBuilder()
            .append("TOTAL: ${list.size}, ")
            .append("TRUE: $t, ")
            .append("FALSE: $f")
            .toString()
    }
}
