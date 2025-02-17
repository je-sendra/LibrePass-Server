package dev.medzik.librepass.types.adapters

import com.google.gson.*
import java.lang.reflect.Type
import java.util.*

class DateAdapter : JsonSerializer<Date>, JsonDeserializer<Date> {
    override fun serialize(
        src: Date?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return if (src != null) {
            JsonPrimitive(src.time / 1000) // Convert milliseconds to seconds
        } else {
            JsonNull.INSTANCE
        }
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date {
        return if (json != null) {
            Date(json.asLong * 1000) // Convert seconds to milliseconds
        } else {
            Date()
        }
    }
}
