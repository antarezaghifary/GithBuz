package com.azer.domain.repository

import com.azer.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun searchUsers(query: String): Flow<Result<List<User>>>
    fun getUserDetail(username: String): Flow<Result<User>>
}
