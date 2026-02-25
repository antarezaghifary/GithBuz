package com.githbuz.domain.repository

import com.githbuz.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun searchUsers(query: String): List<User>
    fun getUser(username: String): Flow<Result<User>>
    fun getFavoriteUsers(): Flow<List<User>>
    suspend fun setFavorite(user: User, isFavorite: Boolean)
    suspend fun addFavoriteUser(user: User)
    suspend fun removeFavoriteUser(user: User)
    fun isFavoriteUser(userId: Int): Flow<Boolean>
}