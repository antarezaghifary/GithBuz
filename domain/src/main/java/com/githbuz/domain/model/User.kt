package com.githbuz.domain.model

data class User(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    val publicRepos: Int?,
    val followers: Int?,
    val following: Int?,
    val isFavorite: Boolean = false
)