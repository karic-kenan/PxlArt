package io.aethibo.pxlart

import android.app.Application
import io.aethibo.pxlart.core.di.networkModule
import io.aethibo.pxlart.core.di.repositoriesModule
import io.aethibo.pxlart.core.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PxlArtApp : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(
                networkModule,
                repositoriesModule,
                useCasesModule
            )
        }
    }
}