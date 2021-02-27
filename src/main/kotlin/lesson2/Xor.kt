package lesson2

class Xor(l: BaseBooleanExpression, r: BaseBooleanExpression) :
    BooleanBinOperation(l = l, r = r, sign = "^") {
    override fun operate(a: Boolean, b: Boolean): Boolean = a xor b
}
