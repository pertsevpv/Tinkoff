package lesson2

class BooleanConstant(private val a: Boolean) : BaseBooleanExpression() {

    override fun evaluate(x: Boolean, y: Boolean, z: Boolean): Boolean = a

    override fun toString(): String = booleanToInt(a).toString()
}
