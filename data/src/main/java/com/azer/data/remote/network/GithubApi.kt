package com.azer.data.remote.network

import com.azer.data.remote.model.UserDetailResponse
import com.azer.data.remote.model.UserSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): UserSearchResponse

    @GET("users/{username}")
    suspend fun getUserDetail(
        @Path("username") username: String
    ): UserDetailResponse
}
