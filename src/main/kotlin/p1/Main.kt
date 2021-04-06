package p1

import java.lang.StringBuilder
import kotlin.concurrent.thread

fun getThreadInfo(): String {
    val cur = Thread.currentThread()
    return StringBuilder().apply {
        appendln("NAME: ${cur.name}")
        appendln("ID: ${cur.id}")
        appendln("DAEMON: ${cur.isDaemon}")
        appendln("PRIORITY: ${cur.priority}")
    }.toString()
}

fun main(args: Array<String>) {
    println(getThreadInfo())

    MyThread().apply {
        start()
    }.join()

    Thread(MyRunnable()).apply {
        start()
    }.join()

    thread {
        println(getThreadInfo())
    }.join()

    MyDaemon().apply {
        start()
    }.join()

    thread(priority = Thread.MAX_PRIORITY) {
        println(getThreadInfo())
    }.join()
}