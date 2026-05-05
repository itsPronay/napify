package com.pronaycoding.blankee.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pronaycoding.blankee.core.database.entities.PresetEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing [PresetEntity] database operations.
 *
 * A preset is a saved combination of sounds with their respective volumes, allowing users to save
 * and restore their favorite audio mixes. This DAO provides type-safe database queries for all
 * preset CRUD (Create, Read, Update, Delete) operations.
 *
 * @see PresetEntity for the data model
 * @see BlankeeDatabase for the database definition
 */
@Dao
interface PresetDao {
    @Insert
    suspend fun insertPreset(preset: PresetEntity): Long

    @Query("SELECT * FROM presets ORDER BY createdAt ASC")
    fun observeAll(): Flow<List<PresetEntity>>

    @Query("DELETE FROM presets WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Update
    suspend fun updatePreset(preset: PresetEntity)
}
