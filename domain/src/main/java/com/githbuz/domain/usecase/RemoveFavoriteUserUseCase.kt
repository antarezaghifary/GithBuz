package com.githbuz.domain.usecase

import com.githbuz.domain.model.User
import com.githbuz.domain.repository.UserRepository
import javax.inject.Inject

class RemoveFavoriteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        userRepository.removeFavoriteUser(user)
    }
}
