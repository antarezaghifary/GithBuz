package com.githbuz.data.network

import com.githbuz.data.network.dto.SearchResponse
import com.githbuz.data.network.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): SearchResponse

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") username: String
    ): UserDto
}
