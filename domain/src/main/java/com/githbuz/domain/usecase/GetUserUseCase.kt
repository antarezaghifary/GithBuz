package com.githbuz.domain.usecase

import com.githbuz.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(username: String) = userRepository.getUser(username)
}
