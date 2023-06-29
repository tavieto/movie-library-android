package dev.tavieto.movielibrary.data.server.retrofit

import dev.tavieto.movielibrary.data.server.model.MoviePageResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRetrofitService {
    @POST("movie/{movieListType}")
    fun getMovieList(
        @Path("movieListType") movieListType: String,
        @Query("page") page: Int
    ): Call<MoviePageResponse>
}
