package dev.tavieto.movielibrary.data.server.core

import dev.tavieto.movielibrary.data.remote.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object RetrofitClient {
    operator fun invoke(
        interceptor: Interceptor?
    ): OkHttpClient {
        return OkHttpClient().newBuilder().apply {
            interceptor?.let { addInterceptor(it) }
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            connectTimeout(timeout = 30, TimeUnit.SECONDS)
            readTimeout(timeout = 30, TimeUnit.SECONDS)
            writeTimeout(timeout = 30, TimeUnit.SECONDS)
        }.build()
    }
}
