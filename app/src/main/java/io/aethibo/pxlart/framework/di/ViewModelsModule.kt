package io.aethibo.pxlart.framework.di

import io.aethibo.pxlart.ui.curated.viewmodel.CuratedViewModel
import io.aethibo.pxlart.ui.search.viewmodel.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { CuratedViewModel(get()) }

    viewModel { SearchViewModel(get()) }
}