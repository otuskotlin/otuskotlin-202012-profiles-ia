package ru.otus.otuskotlin.ia.mp

import kotlin.js.JsName
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads

data class SharedModel @JvmOverloads constructor(
    val id: String = "",
    val name: String = ""
) {
    @get:JvmName("getMyName")
    @JsName("my_name")
    val `my name` = "my name"
}