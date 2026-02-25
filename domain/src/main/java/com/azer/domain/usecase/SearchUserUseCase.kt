package com.azer.domain.usecase

import com.azer.domain.model.User
import com.azer.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(query: String): Flow<Result<List<User>>> {
        return userRepository.searchUsers(query)
    }
}
