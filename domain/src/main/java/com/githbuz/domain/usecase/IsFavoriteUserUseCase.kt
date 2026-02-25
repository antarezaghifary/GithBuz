package com.githbuz.domain.usecase

import com.githbuz.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsFavoriteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(id: Int): Flow<Boolean> {
        return userRepository.isFavoriteUser(id)
    }
}
