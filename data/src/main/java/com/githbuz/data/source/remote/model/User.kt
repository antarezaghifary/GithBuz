package com.githbuz.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("blog")
    val blog: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("public_repos")
    val publicRepos: Int,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("following")
    val following: Int
)