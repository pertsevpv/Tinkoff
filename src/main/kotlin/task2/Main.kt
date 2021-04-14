package task2

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.channels.produce

fun main() {
    runBlocking {
        generate().apply {
            val iter = this.iterator()
            while (iter.hasNext()) {
                println(iter.next())
            }
        }.cancel()
    }
}

suspend fun CoroutineScope.generate() = produce {
    repeat(100) {
        delay(2000L)
        send(KeyGenerator().generateKey())
    }
}