package dev.haqim.netplix.core.data

import dev.haqim.netplix.core.data.remote.response.MovieResponse
import dev.haqim.netplix.core.data.remote.response.MoviesResponse
import dev.haqim.netplix.core.domain.model.Movie

fun MoviesResponse.toMovies(): List<Movie>{
    return this.results.map { 
        Movie(
            overview = it.overview,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle,
            video = it.video,
            title = it.title,
            posterPath = it.posterPath,
            backdropPath = it.backdropPath,
            releaseDate = it.releaseDate,
            popularity = it.popularity,
            voteAverage = it.voteAverage,
            id = it.id,
            adult = it.adult,
            voteCount = it.voteCount,
        )
    }
}

fun MovieResponse.toMovie(): Movie{
    return this.let {
        Movie(
            overview = it.overview,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle,
            video = it.video,
            title = it.title,
            posterPath = it.posterPath,
            backdropPath = it.backdropPath,
            releaseDate = it.releaseDate,
            popularity = it.popularity,
            voteAverage = it.voteAverage,
            id = it.id,
            adult = it.adult,
            voteCount = it.voteCount,
        )
    }
}