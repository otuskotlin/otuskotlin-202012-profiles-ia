package ru.otus.otuskotlin.ia.mp

import kotlin.test.Test
import kotlin.test.assertTrue

internal class SomeFunKtTestJvm {
    @Test
    fun someFunTestJvm(){
        val str = "SomeStr"
        assertTrue {
            someFun(str).contains("JVM")
        }
    }
}