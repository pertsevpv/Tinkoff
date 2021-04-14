package task1

import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.abs
import kotlin.random.Random

class CurrentTime(private val now: LocalDateTime) {
    val time: String = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
}

class CurrentWeather(private val now: LocalDateTime) {
    private val possibleWeather = listOf(
        "Sunny",
        "Rain",
        "Snow",
        "Cloudy"
    )

    val weather: String = possibleWeather[abs(now.hashCode()) % 4] // Не думал, что хеш может быть отрицательным(
    val humidity: Int = abs(now.hashCode()) % 100 + 1
}

class TimeService(private val now: LocalDateTime) {

    suspend fun getTime(): String {
        val time = CurrentTime(now)
        delay(Random.nextLong(100, 1000))
        return time.time
    }

}

class WeatherService(private val now: LocalDateTime) {

    suspend fun getExtraInfo(): String {
        val weather = CurrentWeather(now)
        delay(Random.nextLong(100, 1000))
        return "${weather.weather} ${weather.humidity}%"
    }

}