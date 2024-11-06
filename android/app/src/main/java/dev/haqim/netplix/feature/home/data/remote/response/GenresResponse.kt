package dev.haqim.netplix.feature.home.data.remote.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GenresResponse(

	@SerialName("genres")
	val genres: List<GenresItemResponse>
)

@Serializable
data class GenresItemResponse(

	@SerialName("name")
	val name: String,

	@SerialName("id")
	val id: Int
)
