package dev.haqim.netplix.di

import dev.haqim.netplix.core.di.dataModule
import dev.haqim.netplix.core.di.dispatcherModule
import dev.haqim.netplix.feature.detail.di.detailMovieModule
import dev.haqim.netplix.feature.discover.di.discoverModule
import dev.haqim.netplix.feature.home.di.homeModule

val allModules = listOf(
    dispatcherModule, dataModule, homeModule, discoverModule,
    detailMovieModule
)