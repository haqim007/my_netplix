package dev.haqim.netplix.core.data.remote.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class MoviesResponse(

	@SerialName("page")
	val page: Int,

	@SerialName("total_pages")
	val totalPages: Int,

	@SerialName("results")
	val results: List<MovieResponse>,

	@SerialName("total_results")
	val totalResults: Int
)
