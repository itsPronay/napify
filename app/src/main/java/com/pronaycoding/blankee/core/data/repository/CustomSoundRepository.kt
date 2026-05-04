package com.pronaycoding.blankee.core.data.repository

import com.pronaycoding.blankee.core.database.entities.CustomSoundEntity
import kotlinx.coroutines.flow.Flow


/**
 * Repository interface for custom sound data access operations.
 *
 * This interface defines a contract for custom sound persistence, abstracting the underlying database implementation.
 * It provides high-level operations for managing custom sound metadata without exposing database details.
 *
 * @see CustomSoundRepositoryImpl for the concrete implementation
 * @see CustomSoundEntity for the data model
 * @see CustomSoundDao for lower-level database access
 */
interface CustomSoundRepository {

    /**
     * Observes all custom sounds as a continuous Flow.
     *
     * The returned Flow emits a new list of custom sounds whenever changes occur in the database.
     * This enables reactive UI updates when custom sounds are added or deleted.
     *
     * @return A Flow of lists containing all custom sounds
     */
    fun getAllCustomSounds(): Flow<List<CustomSoundEntity>>

    /**
     * Adds a new custom sound to the database.
     *
     * @param displayName User-friendly name for the custom sound
     * @param filePath Full path or URI to the audio file
     * @throws Exception if the add operation fails
     */
    suspend fun addCustomSound(displayName: String, filePath: String)

    /**
     * Removes a custom sound by its ID.
     *
     * @param id The ID of the custom sound to remove
     * @throws Exception if the remove operation fails
     */
    suspend fun removeCustomSound(id: Int)

    /**
     * Removes a custom sound from the database.
     *
     * @param sound The [CustomSoundEntity] to remove
     * @throws Exception if the remove operation fails
     */
    suspend fun removeCustomSound(sound: CustomSoundEntity)

    /**
     * Retrieves a specific custom sound by its ID.
     *
     * @param id The ID of the custom sound to retrieve
     * @return The [CustomSoundEntity] if found, null otherwise
     * @throws Exception if the retrieval operation fails
     */
    suspend fun getCustomSoundById(id: Int): CustomSoundEntity?

    /**
     * Updates the display name of a custom sound.
     *
     * @param id The ID of the custom sound to update
     * @param displayName The new display name for the custom sound
     * @throws Exception if the update operation fails
     */
    suspend fun updateCustomSoundDisplayName(id: Int, displayName: String)
}
