package task2

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val iter = generate().iterator()
        while (iter.hasNext()) {
            println(iter.next())
        }
    }
}

suspend fun CoroutineScope.generate() = produce{
    while (true) {
        delay(2000L)
        send(KeyGenerator().generateKey())
    }
}