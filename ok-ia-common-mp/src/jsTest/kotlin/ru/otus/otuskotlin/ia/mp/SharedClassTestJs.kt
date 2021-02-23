package ru.otus.otuskotlin.ia.mp

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SharedClassTestJs {
    @Test
    fun requestTest() {
        GlobalScope.launch {
            val res = SharedClass().request("id-1")
            assertEquals("Js response", res)
        }
    }
}