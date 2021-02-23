package ru.otus.otuskotlin.ia.mp

expect class SharedClass {
    suspend fun request (id: String): String
}