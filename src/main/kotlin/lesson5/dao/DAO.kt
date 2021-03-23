package lesson5.dao

interface DAO {
    fun toList(): List<Any>
    fun toValueString(): String
}