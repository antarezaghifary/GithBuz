package com.azer.data.mapper

import com.azer.data.remote.model.UserDetailResponse
import com.azer.data.remote.model.UserDto
import com.azer.domain.model.User

fun UserDto.toDomain(): User {
    return User(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        htmlUrl = htmlUrl
    )
}

fun UserDetailResponse.toDomain(): User {
    return User(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        htmlUrl = htmlUrl,
        name = name,
        bio = bio,
        followers = followers,
        following = following,
        publicRepos = publicRepos,
        location = location
    )
}
