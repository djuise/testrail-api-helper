package com.github.djuise.testrail.api.helpers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.util.*

class UnixTimestampDeserializer : JsonDeserializer<Date>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Date {
        val timestamp = p.longValue
        return Date(timestamp * 1000)  // Конвертируем секунды в миллисекунды
    }
}