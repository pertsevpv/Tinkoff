package lesson2

class Or(l: BaseBooleanExpression, r: BaseBooleanExpression) :
    BooleanBinOperation(l = l, r = r, sign = "||") {
    override fun operate(a: Boolean, b: Boolean): Boolean = a or b
}