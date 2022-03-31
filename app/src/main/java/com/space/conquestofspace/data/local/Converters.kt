package com.space.conquestofspace.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.space.conquestofspace.data.local.util.JsonParser
import com.space.conquestofspace.domain.model.Status

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromStatusJson(json: String): Status?{
        return jsonParser.fromJason<Status>(
            json,
            object : TypeToken<Status>(){}.type
        )
    }

    @TypeConverter
    fun toStatusJson(status: Status): String {
        return jsonParser.toJson(
            status,
            object : TypeToken<Status>(){}.type
        ) ?: "[]"
    }
}