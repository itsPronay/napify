package com.pronaycoding.blankee.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.pronaycoding.blankee.core.database.entities.CustomSoundEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing [CustomSoundEntity] database operations.
 *
 * Custom sounds are audio files uploaded or imported by users to extend the built-in sound library.
 * This DAO provides type-safe database queries for all custom sound CRUD (Create, Read, Update, Delete) operations.
 *
 * @see CustomSoundEntity for the data model
 * @see BlankeeDatabase for the database definition
 */
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
