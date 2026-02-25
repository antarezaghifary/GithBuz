package com.azer.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val htmlUrl: String,
    val name: String? = null,
    val bio: String? = null,
    val followers: Int? = null,
    val following: Int? = null,
    val publicRepos: Int? = null,
    val location: String? = null
)
