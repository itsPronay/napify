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
    /**
     * Inserts a new preset into the database.
     *
     * @param preset The [PresetEntity] to insert
     * @return The row ID of the newly inserted preset
     * @throws android.database.sqlite.SQLiteConstraintException if unique constraint is violated
     */
    @Insert
    suspend fun insertPreset(preset: PresetEntity): Long

    /**
     * Observes all presets in the database as a Flow.
     *
     * The returned Flow automatically emits a new list whenever the presets table changes.
     * Presets are ordered by creation time in descending order (newest first).
     * This is a cold Flow that can have multiple collectors.
     *
     * @return A Flow of lists containing all presets, ordered by creation time (descending)
     */
    @Query("SELECT * FROM presets ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<PresetEntity>>

    /**
     * Deletes a preset by its ID.
     *
     * @param id The ID of the preset to delete
     */
    @Query("DELETE FROM presets WHERE id = :id")
    suspend fun deleteById(id: Long)

    /**
     * Updates an existing preset in the database.
     *
     * The preset is identified by its primary key (id). All fields except the ID are updated.
     *
     * @param preset The [PresetEntity] with updated values. The ID must match an existing preset.
     * @throws android.database.sqlite.SQLiteConstraintException if the preset ID doesn't exist
     */
    @Update
    suspend fun updatePreset(preset : PresetEntity)
}
