package com.gamefort.webservice.builder

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

object RetrofitServiceBuilder {

    private const val RAWG_BASE_URL = "https://api.rawg.io/api/"

    fun <S> buildService(serviceType: Class<S>): S {
        val loggingInterceptor = HttpLoggingInterceptor { Timber.d(it) }
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .baseUrl(RAWG_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttpClientBuilder.build())
            .build()
            .create(serviceType)
    }
}