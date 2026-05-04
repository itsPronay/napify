package com.pronaycoding.blankee.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.pronaycoding.blankee.core.database.entities.CustomSoundEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomSoundDao {
    @Insert
    suspend fun insertCustomSound(sound: CustomSoundEntity)

    @Delete
    suspend fun deleteCustomSound(sound: CustomSoundEntity)

    @Query("SELECT * FROM custom_sounds ORDER BY createdAt ASC")
    fun getAllCustomSounds(): Flow<List<CustomSoundEntity>>

    @Query("SELECT * FROM custom_sounds WHERE id = :id")
    suspend fun getCustomSoundById(id: Int): CustomSoundEntity?

    @Query("DELETE FROM custom_sounds WHERE id = :id")
    suspend fun deleteCustomSoundById(id: Int)

    @Query("UPDATE custom_sounds SET displayName = :displayName WHERE id = :id")
    suspend fun updateCustomSoundDisplayName(
        id: Int,
        displayName: String,
    )
}
