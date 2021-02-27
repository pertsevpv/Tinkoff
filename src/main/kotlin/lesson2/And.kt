package lesson2

class And(l: BaseBooleanExpression, r: BaseBooleanExpression) :
    BooleanBinOperation(l = l, r = r, sign = "&&") {
    override fun operate(a: Boolean, b: Boolean): Boolean = a and b
}