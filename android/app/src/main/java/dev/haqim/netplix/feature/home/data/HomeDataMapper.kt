package dev.haqim.netplix.feature.home.data

import dev.haqim.netplix.core.domain.model.Genre
import dev.haqim.netplix.feature.home.data.remote.response.GenresItemResponse
import dev.haqim.netplix.feature.home.data.remote.response.GenresResponse

fun GenresResponse.toGenres(): List<Genre>{
    return this.genres.map {
        it.toGenre()
    }
}

fun GenresItemResponse.toGenre(): Genre{
    return Genre(
        id = this.id,
        name = this.name
    )
}
