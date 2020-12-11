package io.aethibo.pxlart.core.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.aethibo.pxlart.BuildConfig
import io.aethibo.pxlart.core.data.remote.api.ApiService
import io.aethibo.pxlart.core.data.remote.api.interceptors.TimberLoggingInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.PEXELS_API_KEY)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(TimberLoggingInterceptor())
                    .build()
            )
            .build()
            .create(ApiService::class.java)
    }
}