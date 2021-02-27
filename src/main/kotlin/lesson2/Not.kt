package lesson2

class Not(a: BaseBooleanExpression) : BooleanUnOperation(a = a, sign = "!") {
    override fun operate(a: Boolean): Boolean = !a
}