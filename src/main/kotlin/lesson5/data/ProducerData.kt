package lesson5.data

import lesson5.dao.Producer

class ProducerData {

    private val producerList: List<Producer> = listOf(
        Producer(1, "Apple", 85),
        Producer(2, "Samsung", 79),
        Producer(3, "Xiaomi", 71),
        Producer(4, "Huawei", 75)
    )

    fun getProducers(): List<Producer> =
        producerList
}