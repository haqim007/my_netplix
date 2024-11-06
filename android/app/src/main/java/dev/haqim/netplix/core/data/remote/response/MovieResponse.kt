package dev.haqim.netplix.core.data.remote.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class MovieResponse(

	@SerialName("overview")
	val overview: String,

	@SerialName("original_language")
	val originalLanguage: String,

	@SerialName("original_title")
	val originalTitle: String,

	@SerialName("video")
	val video: Boolean,

	@SerialName("title")
	val title: String,

	@SerialName("genre_ids")
	val genreIds: List<Int?>,

	@SerialName("poster_path")
	val posterPath: String,

	@SerialName("backdrop_path")
	val backdropPath: String,

	@SerialName("release_date")
	val releaseDate: String,

	@SerialName("popularity")
	val popularity: Double,

	@SerialName("vote_average")
	val voteAverage: Double,

	@SerialName("id")
	val id: Int,

	@SerialName("adult")
	val adult: Boolean,

	@SerialName("vote_count")
	val voteCount: Int
)
