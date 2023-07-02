package dev.tavieto.movielibrary.data.server.interceptor

import dev.tavieto.movielibrary.data.server.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class TmdbInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader(name = "accept", value = "application/json")
            .addHeader(name = "Authorization", value = "Bearer ${BuildConfig.TMDB_READ_TOKEN}")
            .build()
        return chain.proceed(newRequest)
    }
}