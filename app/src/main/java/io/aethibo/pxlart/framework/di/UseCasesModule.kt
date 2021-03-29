package io.aethibo.pxlart.framework.di

import io.aethibo.pxlart.domain.search.ISearchUseCase
import io.aethibo.pxlart.domain.search.SearchUseCase
import io.aethibo.pxlart.usecases.curated.CuratedUseCase
import io.aethibo.pxlart.usecases.curated.ICuratedUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single<ICuratedUseCase> { CuratedUseCase(get()) }

    single<ISearchUseCase> { SearchUseCase(get()) }
}