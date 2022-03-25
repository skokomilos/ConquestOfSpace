package com.space.conquestofspace.data.local.util

import java.lang.reflect.Type

interface JsonParser {

    fun <T> fromJason(json: String, type: Type): Type?

    fun <T> toJson(obj: T, type: Type): String?
}