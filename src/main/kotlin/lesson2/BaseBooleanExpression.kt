package lesson2

abstract class BaseBooleanExpression {
    abstract fun evaluate(x: Boolean = false, y: Boolean = false, z: Boolean = false): Boolean

    fun makeTruthTable() {
        val sb: StringBuilder = StringBuilder()
        val exp = this.toString()
        with(sb) {
            appendln("F(x, y, z) = $exp")
            append("x").append("\t")
            append("y").append("\t")
            append("z").append("\t")
            appendln("F(x, y, z)")
        }
        val l = listOf(false, true)
        for (x in l)
            for (y in l)
                for (z in l) {
                    val res = evaluate(x, y, z);
                    with(sb) {
                        append(booleanToInt(x)).append("\t")
                        append(booleanToInt(y)).append("\t")
                        append(booleanToInt(z)).append("\t")
                        appendln(booleanToInt(res))
                    }
                }
        print("$sb")
    }

    fun booleanToInt(a: Boolean): Int = if (a) 1 else 0
}