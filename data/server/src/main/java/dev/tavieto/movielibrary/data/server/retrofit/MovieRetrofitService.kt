package dev.tavieto.movielibrary.data.server.retrofit

import dev.tavieto.movielibrary.data.server.model.FavoriteMediaServerRequest
import dev.tavieto.movielibrary.data.server.model.FavoriteMediaServerResponse
import dev.tavieto.movielibrary.data.server.model.MoviePageServerResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRetrofitService {
    @POST("/3/movie/{movieListType}")
    fun getMovieList(
        @Path("movieListType") movieListType: String,
        @Query("page") page: Int,
        @Query("language") language: String = "pt-BR"
    ): Call<MoviePageServerResponse>

    @POST("/3/account/{account_id}/favorite")
    fun postFavoriteMovie(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Body favorite: FavoriteMediaServerRequest
    ): Call<FavoriteMediaServerResponse>

    @GET("/3/account/{account_id}/favorite/movies")
    fun getFavoriteMovies(
        @Path("account_id") accountId: Int,
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("sorted_by") sortedBy: String,
        @Query("session_id") sessionId: String
    ): Call<MoviePageServerResponse>
}
