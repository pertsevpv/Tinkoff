package lesson3

fun <T> queueOf(vararg elements: T) =
    if (elements.isNotEmpty()) {
        Queue(linkedListOf(*elements))
    } else {
        emptyQueue()
    }

fun <T> emptyQueue() = Queue<T>()

class Queue<T>(private val data: LinkedList<T> = emptyLinkedList()) {

    fun enqueue(e: T) = data.addLast(e)

    fun dequeue(): T? = data.removeFirst()

    fun isEmpty() = data.isEmpty()

    fun size() = data.size()

    override fun toString(): String = data.toString()
}