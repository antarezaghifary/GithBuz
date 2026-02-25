package com.githbuz.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(user: UserEntity)

    @Query("DELETE FROM favorite_users WHERE id = :id")
    suspend fun deleteFavorite(id: Int)

    @Query("SELECT * FROM favorite_users")
    fun getFavoriteUsers(): Flow<List<UserEntity>>

    @Query("SELECT EXISTS(SELECT * FROM favorite_users WHERE id = :id)")
    fun isFavorite(id: Int): Flow<Boolean>
}