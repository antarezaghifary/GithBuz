package com.githbuz.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_users")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val login: String,
    val avatarUrl: String
)