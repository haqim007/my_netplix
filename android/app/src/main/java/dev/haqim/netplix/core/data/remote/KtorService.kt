package dev.haqim.netplix.core.data.remote

import android.util.Log
import dev.haqim.netplix.core.di.ktorHttpClient
import dev.haqim.netplix.core.domain.model.CustomRequestException
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class KtorService: KoinComponent {

    protected abstract val BASE_URL: String
    protected abstract val API_VERSION: String

    protected open val client: HttpClient by inject(ktorHttpClient)

    fun HttpRequestBuilder.endpoint(
        path: String,
        parametersList: List<Pair<String, String>> = listOf(), // Pair<Name, Value>
        type: ContentType = ContentType.Application.Json
    ){
        url {
            takeFrom(BASE_URL)
            //path("$API_VERSION$path")
            path(path)
            parametersList.forEach { param ->
                parameters.append(param.first, param.second)
            }
            contentType(type)
        }
    }

    protected suspend fun checkOrThrowError(response: HttpResponse) {
        // Parse JSON string into a dynamic structure (JsonElement)
        try {
            val json = Json.parseToJsonElement(response.bodyAsText())
            val errors = json.jsonObject["errors"]?.let {
                when (it) {
                    is JsonObject -> it // If it's a JsonObject
                    is JsonArray -> it // If it's a JsonArray
                    else -> null // Handle other cases if necessary
                }
            }
            val message = json.jsonObject["message"]?.jsonPrimitive?.content
            if (response.status != HttpStatusCode.OK && (message != null || errors != null)) {
                throw CustomRequestException(
                    dataJson = json,
                    statusCode = response.status,
                    errorMessage = message
                )
            }
        } catch (e: CustomRequestException){
            throw  e
        } catch (e: Exception){
            Log.e("checkOrThrowError", e.stackTraceToString())
        }
    }
}
