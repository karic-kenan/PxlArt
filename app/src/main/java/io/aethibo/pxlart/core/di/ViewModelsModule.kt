package io.aethibo.pxlart.core.di

import io.aethibo.pxlart.features.curated.viewmodel.CuratedViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { CuratedViewModel(get()) }
}