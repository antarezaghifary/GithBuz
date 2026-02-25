package com.githbuz.data.source.remote

import com.githbuz.data.source.remote.model.User
import com.githbuz.data.source.remote.model.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): UsersResponse

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") username: String
    ): User
}