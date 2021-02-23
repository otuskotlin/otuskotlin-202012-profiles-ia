package ru.otus.otuskotlin.ia.mp

import kotlinx.coroutines.delay

actual class SharedClass {
    actual suspend fun request(id: String): String {
        delay(1000)
        return "Js response"
    }
}