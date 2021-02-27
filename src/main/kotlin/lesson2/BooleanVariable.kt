package lesson2

class BooleanVariable(private val name: Char) : BaseBooleanExpression() {
    override fun evaluate(x: Boolean, y: Boolean, z: Boolean): Boolean {
        return when (name) {
            'x' -> x
            'y' -> y
            'z' -> z
            else -> throw Exception("Illegal variable name: $name")
        }
    }

    override fun toString(): String = "$name"
}