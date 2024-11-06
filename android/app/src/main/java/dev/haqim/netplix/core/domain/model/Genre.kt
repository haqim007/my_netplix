package dev.haqim.netplix.core.domain.model

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
@Parcelize
data class Genre(
    val id: Int,
    val name: String
) : Parcelable


val GenreArgsNavType = object : NavType<Genre?>(
    isNullableAllowed = true
) {
    override fun put(bundle: Bundle, key: String, value: Genre?) {
        bundle.putParcelable(key, value)
    }
    override fun get(bundle: Bundle, key: String): Genre? {
        return bundle.getParcelable(key)
    }

    override fun serializeAsValue(value: Genre?): String {
        // Serialized values must always be Uri encoded
        return Uri.encode(Json.encodeToString(value))
    }

    override fun parseValue(value: String): Genre? {
        // Navigation takes care of decoding the string
        // before passing it to parseValue()
        return Json.decodeFromString<Genre?>(value)
    }

}
