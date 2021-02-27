package lesson2

fun main() {
    val exp1 = Implication(BooleanVariable('x'), Implication(BooleanVariable('y'), BooleanVariable('x')))
    exp1.makeTruthTable()
    println()

    val exp2 = Xor(BooleanConstant(true), Xor(BooleanVariable('x'), Xor(BooleanVariable('y'), BooleanVariable('z'))))
    exp2.makeTruthTable()
    println()

    val exp3 = Or(Not(BooleanVariable('y')), BooleanVariable('z'))
    exp3.makeTruthTable()
    println()

    val exp4 =
        And(Equal(BooleanVariable('x'), BooleanVariable('y')), NotEqual(BooleanVariable('x'), BooleanVariable('y')))
    exp4.makeTruthTable()
    println()
}