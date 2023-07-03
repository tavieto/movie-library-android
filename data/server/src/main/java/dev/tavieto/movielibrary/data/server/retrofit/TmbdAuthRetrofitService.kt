package dev.tavieto.movielibrary.data.server.retrofit

import dev.tavieto.movielibrary.data.server.model.AccountDetailsServerResponse
import dev.tavieto.movielibrary.data.server.model.RequestTokenServerRequest
import dev.tavieto.movielibrary.data.server.model.RequestTokenServerResponse
import dev.tavieto.movielibrary.data.server.model.SessionIdServerResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TmbdAuthRetrofitService {

    @GET("/3/account")
    fun getAccountDetails(
        @Query("session_id") sessionId: String
    ): Call<AccountDetailsServerResponse>

    @GET("/3/authentication/token/new")
    fun getRequestToken(): Call<RequestTokenServerResponse>

    @POST("/3/authentication/session/new")
    fun getSessionId(
        @Body requestToken: RequestTokenServerRequest
    ): Call<SessionIdServerResponse>
}
