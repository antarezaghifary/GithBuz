package com.githbuz.domain.usecase.favorite

import com.githbuz.domain.model.User
import com.githbuz.domain.repository.UserRepository
import javax.inject.Inject

class SetFavoriteUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User, isFavorite: Boolean) {
        userRepository.setFavorite(user, isFavorite)
    }
}
