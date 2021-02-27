package lesson3

fun main() {
    val queue: Queue<Int> = queueOf(1, 2, 3, 4)
    println(queue.dequeue())
    println(queue.dequeue())
    queue.enqueue(5)
    queue.enqueue(6)
    println(queue.dequeue())
    println(queue.dequeue())
    println(queue.dequeue())
    println(queue.dequeue())
    queue.enqueue(7)
    queue.enqueue(8)
    println(queue.dequeue())
    println(queue.dequeue())

    println("-----//-----")

    val stack: Stack<Int> = stackOf(1, 2, 3, 4)
    println(stack.pop())
    println(stack.pop())
    stack.push(5)
    stack.push(6)
    println(stack.pop())
    println(stack.pop())
    println(stack.pop())
    println(stack.pop())
    stack.push(7)
    stack.push(8)
    println(stack.pop())
    println(stack.pop())
}