package com.azer.core.error

import java.io.IOException

object ErrorMapper {

    fun toMessage(throwable: Throwable): String {
        return when (throwable) {
            is IOException -> "Network error. Please check your connection."
            is IllegalStateException -> "Unexpected app state occurred."
            else -> throwable.message ?: "Something went wrong."
        }
    }
}