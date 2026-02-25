package com.githbuz.domain.usecase

import com.githbuz.domain.model.User
import com.githbuz.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<List<User>> {
        return userRepository.getFavoriteUsers()
    }
}