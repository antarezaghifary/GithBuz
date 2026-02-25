package com.githbuz.data.repository

import com.githbuz.data.mapper.UserMapper
import com.githbuz.data.source.local.UserDao
import com.githbuz.data.source.remote.ApiService
import com.githbuz.domain.model.User
import com.githbuz.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val userMapper: UserMapper
) : UserRepository {
    override suspend fun searchUsers(query: String): List<User> {
        val response = apiService.searchUsers(query)
        val favoriteUserIds = userDao.getFavoriteUsers().first().map { it.id }.toSet()
        return response.items.map {
            userMapper.mapFromRemote(it, favoriteUserIds.contains(it.id))
        }
    }

    override fun getUser(username: String): Flow<Result<User>> = flow {
        try {
            val userResponse = apiService.getUser(username)
            userDao.isFavoriteUser(userResponse.id).collect { isFavorite ->
                 emit(Result.success(userMapper.mapFromRemote(userResponse, isFavorite)))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getFavoriteUsers(): Flow<List<User>> {
        return userDao.getFavoriteUsers().map { entities ->
            userMapper.mapFromEntityList(entities)
        }
    }

    override suspend fun setFavorite(user: User, isFavorite: Boolean) {
        if (isFavorite) {
            addFavoriteUser(user)
        } else {
            removeFavoriteUser(user)
        }
    }

    override suspend fun addFavoriteUser(user: User) {
        userDao.insertFavorite(userMapper.mapToEntity(user))
    }

    override suspend fun removeFavoriteUser(user: User) {
        userDao.deleteFavorite(user.id)
    }

    override fun isFavoriteUser(userId: Int): Flow<Boolean> {
        return userDao.isFavoriteUser(userId)
    }
}