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
    /**
     * Inserts a new custom sound into the database.
     *
     * @param sound The [CustomSoundEntity] to insert
     * @throws android.database.sqlite.SQLiteConstraintException if unique constraint is violated
     */
    @Insert
    suspend fun insertCustomSound(sound: CustomSoundEntity)

    /**
     * Deletes a custom sound from the database.
     *
     * @param sound The [CustomSoundEntity] to delete
     */
    @Delete
    suspend fun deleteCustomSound(sound: CustomSoundEntity)

    /**
     * Retrieves all custom sounds from the database as a Flow.
     *
     * The returned Flow automatically emits a new list whenever the custom_sounds table changes.
     * Custom sounds are ordered by creation time in ascending order (oldest first).
     * This is a cold Flow that can have multiple collectors.
     *
     * @return A Flow of lists containing all custom sounds, ordered by creation time (ascending)
     */
    @Query("SELECT * FROM custom_sounds ORDER BY createdAt ASC")
    fun getAllCustomSounds(): Flow<List<CustomSoundEntity>>

    /**
     * Retrieves a specific custom sound by its ID.
     *
     * @param id The ID of the custom sound to retrieve
     * @return The [CustomSoundEntity] if found, null otherwise
     */
    @Query("SELECT * FROM custom_sounds WHERE id = :id")
    suspend fun getCustomSoundById(id: Int): CustomSoundEntity?

    /**
     * Deletes a custom sound by its ID.
     *
     * @param id The ID of the custom sound to delete
     */
    @Query("DELETE FROM custom_sounds WHERE id = :id")
    suspend fun deleteCustomSoundById(id: Int)

    /**
     * Updates the display name of a custom sound.
     *
     * @param id The ID of the custom sound to update
     * @param displayName The new display name for the custom sound
     */
    @Query("UPDATE custom_sounds SET displayName = :displayName WHERE id = :id")
    suspend fun updateCustomSoundDisplayName(id: Int, displayName: String)
}
