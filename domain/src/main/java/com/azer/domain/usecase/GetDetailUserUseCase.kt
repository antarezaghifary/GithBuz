package com.azer.domain.usecase

import com.azer.domain.model.User
import com.azer.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetailUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(username: String): Flow<Result<User>> {
        return userRepository.getUserDetail(username)
    }
}
