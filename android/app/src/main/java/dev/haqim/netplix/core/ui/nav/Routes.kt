package dev.haqim.netplix.core.ui.nav

import dev.haqim.netplix.core.domain.model.Genre
import kotlinx.serialization.Serializable


@Serializable
object HomeRoute

@Serializable
data class DetailRoute(
    val id: Int
)

@Serializable
data class DiscoverRoute(
    val genre: Genre? = null
)