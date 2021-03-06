package com.example.testmvvm.data.network

import com.example.testmvvm.data.network.responses.AuthResponses
import com.example.testmvvm.data.network.responses.NetworkConnectionInterceptor
import com.example.testmvvm.data.network.responses.QuotesResponeses
import com.example.testmvvm.database.entities.Quote
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface CallAPI {

    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<AuthResponses>

    @FormUrlEncoded
    @POST("register")
    suspend fun userSignup(
        @Field("email") email: String,
        @Field("password") password: String
    ):Response<AuthResponses>


    @GET("users")
    suspend fun getQuotes(

    ):Response<QuotesResponeses>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor

        ): CallAPI {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CallAPI::class.java)
        }
    }
}