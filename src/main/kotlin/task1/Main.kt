package task1

import kotlinx.coroutines.*

fun main() {
    var time = ""
    var weath = ""
    runBlocking {
        launch {
            time = getTime()
        }
        launch {
            weath = getWeather()
        }
    }
    println(time.concat(weath))
}

fun String.concat(str: String) =
    "$this\n$str"

suspend fun getTime(): String {
    return TimeService().getTime()
}

suspend fun getWeather(): String {
    return WeatherService().getExtraInfo()
}