package ru.otus.otuskotlin.ia.mp

import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SharedClassTestJs {
    @Test
    fun requestTest() = runBlocking<Unit>{
        val res = SharedClass().request("id-1")
        assertEquals("Jvm response", res)
    }
}