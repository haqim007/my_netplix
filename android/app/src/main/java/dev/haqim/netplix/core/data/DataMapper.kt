package dev.haqim.netplix.core.data

import dev.haqim.netplix.core.data.remote.response.MovieResponse
import dev.haqim.netplix.core.data.remote.response.MoviesResponse
import dev.haqim.netplix.core.domain.model.Movie
import dev.haqim.netplix.core.domain.model.MovieTrailer

fun MoviesResponse.toMovies(): List<Movie>{
    return this.results.map { 
        it.toMovie()
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
            // TODO: replace by fetching from API later
            trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
        )
    }
}