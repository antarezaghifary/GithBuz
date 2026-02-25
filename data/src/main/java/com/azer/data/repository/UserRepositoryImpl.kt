package com.azer.data.repository

import com.azer.data.mapper.toDomain
import com.azer.data.remote.network.GithubApi
import com.azer.domain.model.User
import com.azer.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi
) : UserRepository {

    override fun searchUsers(query: String): Flow<Result<List<User>>> = flow {
        try {
            val response = githubApi.searchUsers(query)
            emit(Result.success(response.items.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getUserDetail(username: String): Flow<Result<User>> = flow {
        try {
            val response = githubApi.getUserDetail(username)
            emit(Result.success(response.toDomain()))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
