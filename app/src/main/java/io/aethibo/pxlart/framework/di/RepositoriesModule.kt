package io.aethibo.pxlart.framework.di

import io.aethibo.pxlart.data.repositories.DefaultMainRepository
import io.aethibo.pxlart.data.repositories.MainRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single<MainRepository> { DefaultMainRepository(get()) }
}