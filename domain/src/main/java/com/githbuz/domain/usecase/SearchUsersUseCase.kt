package com.githbuz.domain.usecase

import com.githbuz.domain.model.User
import com.githbuz.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(query: String): Flow<Result<List<User>>> {
        return userRepository.searchUsers(query)
    }
}