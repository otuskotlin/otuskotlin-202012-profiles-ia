package ru.otus.otuskotlin.ia.mp

import kotlin.test.Test
import kotlin.test.assertTrue

internal class SomeFunKtTestJs {
    @Test
    fun someFunTestJs(){
        val str = "SomeStr"
        assertTrue {
            someFun(str).contains("Js")
        }
    }
}