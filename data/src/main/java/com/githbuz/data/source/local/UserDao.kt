package com.githbuz.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.githbuz.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(user: UserEntity)

    @Query("DELETE FROM user WHERE id = :userId")
    suspend fun deleteFavorite(userId: Int)

    @Query("SELECT * FROM user")
    fun getFavoriteUsers(): Flow<List<UserEntity>>

    @Query("SELECT EXISTS(SELECT * FROM user WHERE id = :userId)")
    fun isFavoriteUser(userId: Int): Flow<Boolean>
}
