package com.azer.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserSearchResponse(
    @SerialName("items")
    val items: List<UserDto>
)

@Serializable
data class UserDto(
    @SerialName("id")
    val id: Int,
    @SerialName("login")
    val login: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("html_url")
    val htmlUrl: String
)

@Serializable
data class UserDetailResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("login")
    val login: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("name")
    val name: String? = null,
    @SerialName("bio")
    val bio: String? = null,
    @SerialName("followers")
    val followers: Int? = null,
    @SerialName("following")
    val following: Int? = null,
    @SerialName("public_repos")
    val publicRepos: Int? = null,
    @SerialName("location")
    val location: String? = null
)
