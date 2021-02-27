package lesson2

abstract class BooleanUnOperation(private val a: BaseBooleanExpression, private val sign: String) :
    BaseBooleanExpression() {

    abstract fun operate(a: Boolean): Boolean

    override fun evaluate(x: Boolean, y: Boolean, z: Boolean): Boolean =
        operate(a.evaluate(x, y, z))

    override fun toString(): String = "$sign($a)"
}