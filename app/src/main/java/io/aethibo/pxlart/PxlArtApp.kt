package io.aethibo.pxlart

import android.app.Application
import io.aethibo.pxlart.core.di.networkModule
import io.aethibo.pxlart.core.di.repositoriesModule
import io.aethibo.pxlart.core.di.useCasesModule
import io.aethibo.pxlart.core.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class PxlArtApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(
                networkModule,
                repositoriesModule,
                useCasesModule,
                viewModelsModule
            )
        }
    }
}