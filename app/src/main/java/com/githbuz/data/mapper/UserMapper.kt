package com.githbuz.data.mapper

import com.githbuz.data.source.local.entity.UserEntity
import com.githbuz.domain.model.User

fun UserEntity.toUser() = User(
    id = id,
    username = username,
    avatarUrl = avatarUrl,
    isFavorite = true
)

fun User.toEntity() = UserEntity(
    id = id,
    username = username,
    avatarUrl = avatarUrl
)
