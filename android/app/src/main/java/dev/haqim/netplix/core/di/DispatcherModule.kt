package dev.haqim.netplix.core.di

import dev.haqim.netplix.core.util.AndroidDispatcher
import dev.haqim.netplix.core.util.DispatcherProvider
import org.koin.dsl.module

val dispatcherModule = module{
    single<DispatcherProvider>{ AndroidDispatcher() }
}