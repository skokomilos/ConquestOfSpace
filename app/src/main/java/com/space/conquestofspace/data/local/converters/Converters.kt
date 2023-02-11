package com.space.conquestofspace.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.space.conquestofspace.data.local.util.JsonParser
import com.space.conquestofspace.domain.model.Pad
import com.space.conquestofspace.domain.model.Rocket
import com.space.conquestofspace.domain.model.Status
/*
See GsonParses
 */
@ProvidedTypeConverter
class
Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromStatusJson(json: String): Status?{
        return jsonParser.fromJson<Status>(
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

    @TypeConverter
    fun fromPadJson(json: String): Pad? {
        return jsonParser.fromJson<Pad>(
            json,
            object : TypeToken<Pad>() {}.type
        )

    }

    @TypeConverter
    fun toPadJson(pad: Pad): String {
        return jsonParser.toJson(
            pad,
            object : TypeToken<Pad>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromRocketJson(json: String): Rocket? {
        return jsonParser.fromJson(
            json,
            object : TypeToken<Rocket>() {}.type
        )
    }

    @TypeConverter
    fun toRocketJson(rocket: Rocket): String {
        return Gson().toJson(
            rocket,
            object : TypeToken<Rocket>() {}.type
        ) ?: "[]"
    }
}