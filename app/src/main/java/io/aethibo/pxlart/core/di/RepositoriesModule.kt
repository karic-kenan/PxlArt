package io.aethibo.pxlart.core.di

import io.aethibo.pxlart.core.data.repositories.DefaultMainRepository
import io.aethibo.pxlart.core.data.repositories.MainRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single<MainRepository> { DefaultMainRepository(get()) }
}