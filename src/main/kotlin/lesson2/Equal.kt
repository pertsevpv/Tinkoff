package lesson2

class Equal(l: BaseBooleanExpression, r: BaseBooleanExpression) :
    BooleanBinOperation(l = l, r = r, sign = "==") {
    override fun operate(a: Boolean, b: Boolean): Boolean = a == b
}