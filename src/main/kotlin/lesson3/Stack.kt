package lesson3

fun <T> stackOf(vararg elements: T) =
    if (elements.isNotEmpty()) {
        Stack(linkedListOf(*elements))
    } else {
        emptyStack()
    }

fun <T> emptyStack() = Stack<T>()

class Stack<T>(private val data: LinkedList<T> = emptyLinkedList()) {

    fun push(e: T) = data.addFirst(e)

    fun pop(): T? = data.removeFirst()

    fun isEmpty() = data.isEmpty()

    fun size() = data.size()

    override fun toString(): String = data.toString()
}