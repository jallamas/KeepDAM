package com.salesianostriana.keepdam.retrofit

import com.salesianostriana.keepdam.common.Constantes
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class KeepDamClient {

    @Singleton
    @Provides
    @Named("url")
    fun provideBaseUrl(): String = Constantes.API_BASE_URL

    @Singleton
    @Provides
    fun createClient(@Named("url") baseUrl: String): KeepDamService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).addInterceptor(KeepDamTokenInterceptor()).build())
            .build()
            .create(KeepDamService::class.java)
    }

}