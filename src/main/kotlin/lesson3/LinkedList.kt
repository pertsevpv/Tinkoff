package lesson3

fun <T> linkedListOf(vararg elements: T): LinkedList<T> {
    if (elements.isEmpty()) return emptyLinkedList();
    val result: LinkedList<T> = LinkedList()
    for (e: T in elements) {
        result.addLast(e)
    }
    return result
}

fun <T> emptyLinkedList(): LinkedList<T> = LinkedList()

class LinkedList<T> {

    open class BaseNode<T>(var prev: BaseNode<T>?, var next: BaseNode<T>?)

    class Node<T>(var value: T, prv: BaseNode<T>, nxt: BaseNode<T>) : BaseNode<T>(prev = prv, next = nxt) {
        override fun toString(): String = value.toString()
    }

    private val head: BaseNode<T>
    private val tail: BaseNode<T>
    private var size: Int = 0

    init {
        //У head всегда есть next - tail
        //У tail всегда есть prev - head
        head = BaseNode(null, null)
        tail = BaseNode(null, null)

        head.next = tail
        tail.prev = head
    }

    fun addFirst(e: T) {
        size++
        val n: Node<T> = Node(e, head, head.next!!)
        head.next!!.prev = n
        head.next = n
    }

    fun addLast(e: T) {
        size++
        val n: Node<T> = Node(e, tail.prev!!, tail)
        tail.prev!!.next = n
        tail.prev = n
    }

    fun removeFirst(): T? {
        return if (isEmpty()) null
        else {
            val a = head.next as Node<T>
            head.next = a.next
            head.next!!.prev = head
            size--
            a.value
        }
    }

    fun removeLast(): T? {
        return if (isEmpty()) null
        else {
            val a = tail.prev as Node<T>
            tail.prev = a.prev
            tail.prev!!.next = tail
            size--
            a.value
        }
    }

    fun size() = size

    fun isEmpty() = size == 0

    override fun toString(): String {
        val sb: StringBuilder = StringBuilder()
        var cur = head.next
        while (cur?.next != null) {
            sb.append(cur)
            cur = cur.next
            if (cur?.next != null) sb.append(", ")
        }
        return "$sb"
    }
}