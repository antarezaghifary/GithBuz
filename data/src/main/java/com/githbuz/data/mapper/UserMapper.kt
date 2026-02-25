package com.githbuz.data.mapper

import com.githbuz.data.source.local.entity.UserEntity
import com.githbuz.domain.model.User

class UserMapper {
    fun UserEntity.toUser(): User {
        return User(
            id = id,
            login = login,
            avatarUrl = avatarUrl,
            name = null,
            company = null,
            blog = null,
            location = null,
            email = null,
            bio = null,
            publicRepos = null,
            followers = null,
            following = null,
            isFavorite = true
        )
    }

    fun User.toEntity(): UserEntity {
        return UserEntity(
            id = id,
            login = login,
            avatarUrl = avatarUrl
        )
    }
}
