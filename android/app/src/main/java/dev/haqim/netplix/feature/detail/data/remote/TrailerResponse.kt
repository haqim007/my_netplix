package dev.haqim.netplix.feature.detail.data.remote

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class TrailerResponse(

	@SerialName("id")
	val id: Int,

	@SerialName("results")
	val results: List<TrailerItemResponse>
)

@Serializable
data class TrailerItemResponse(

	@SerialName("site")
	val site: String,

	@SerialName("size")
	val size: Int,

	@SerialName("iso_3166_1")
	val iso31661: String,

	@SerialName("name")
	val name: String,

	@SerialName("official")
	val official: Boolean,

	@SerialName("id")
	val id: String,

	@SerialName("type")
	val type: String,

	@SerialName("published_at")
	val publishedAt: String,

	@SerialName("iso_639_1")
	val iso6391: String,

	@SerialName("key")
	val key: String
)
