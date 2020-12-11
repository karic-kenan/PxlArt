package io.aethibo.pxlart.core.di

import io.aethibo.pxlart.domain.curated.CuratedUseCase
import io.aethibo.pxlart.domain.curated.ICuratedUseCase
import io.aethibo.pxlart.domain.search.ISearchUseCase
import io.aethibo.pxlart.domain.search.SearchUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single<ICuratedUseCase> { CuratedUseCase(get()) }

    single<ISearchUseCase> { SearchUseCase(get()) }
}