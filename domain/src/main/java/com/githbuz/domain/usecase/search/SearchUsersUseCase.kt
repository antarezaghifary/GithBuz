package com.githbuz.domain.usecase.search

import com.githbuz.domain.model.User
import com.githbuz.domain.repository.UserRepository
import javax.inject.Inject

class SearchUsersUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(query: String): List<User> {
        return userRepository.searchUsers(query)
    }
}
