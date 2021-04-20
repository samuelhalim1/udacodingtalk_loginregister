package com.codingtalk.udacoding.presentation.utils

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException

fun String.prettyJson(): String = if (this.startsWith("{") || this.startsWith("[")) {
    try {
        GsonBuilder().setPrettyPrinting()
            .create().toJson(JsonParser().parse(this))
    } catch (ex: JsonSyntaxException) {
        this
    }
} else {
    this
}