package lesson2

abstract class BooleanBinOperation(
    private val l: BaseBooleanExpression,
    private val r: BaseBooleanExpression,
    private val sign: String
) :
    BaseBooleanExpression() {

    abstract fun operate(a: Boolean, b: Boolean): Boolean

    override fun evaluate(x: Boolean, y: Boolean, z: Boolean): Boolean =
        operate(l.evaluate(x, y, z), r.evaluate(x, y, z))

    override fun toString(): String = "($l $sign $r)"
}