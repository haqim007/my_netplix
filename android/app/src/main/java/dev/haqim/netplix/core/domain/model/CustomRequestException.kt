package dev.haqim.netplix.core.domain.model

import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.JsonElement

class CustomRequestException (
    val dataJson: JsonElement? = null,
    val statusCode: HttpStatusCode,
    val errorMessage: String? = null
) : Exception("Client Request Exception: ${statusCode.value} - ${errorMessage ?: ""}")