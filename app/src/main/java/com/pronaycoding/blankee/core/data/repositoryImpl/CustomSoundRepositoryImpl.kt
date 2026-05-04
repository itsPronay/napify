package com.pronaycoding.blankee.core.data.repositoryImpl

import com.pronaycoding.blankee.core.database.dao.CustomSoundDao
import com.pronaycoding.blankee.core.database.entities.CustomSoundEntity
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of the [CustomSoundRepository] interface.
 *
 * This class provides concrete implementations for all custom sound database operations.
 * It acts as a bridge between the UI/business logic layer and the Room database DAO,
 * delegating all operations to [CustomSoundDao] while maintaining the repository abstraction.
 *
 * @param customSoundDao The Data Access Object used for database operations
 *
 * @see CustomSoundRepository for the interface contract
 * @see CustomSoundDao for lower-level database access
 */
class CustomSoundRepositoryImpl(
    private val customSoundDao: CustomSoundDao
) : com.pronaycoding.blankee.core.data.repository.CustomSoundRepository {

    /**
     * Retrieves all custom sounds from the database as a Flow.
     *
     * @return A Flow from the DAO that emits updated custom sound lists on database changes
     */
    override fun getAllCustomSounds(): Flow<List<CustomSoundEntity>> {
        return customSoundDao.getAllCustomSounds()
    }

    /**
     * Adds a new custom sound to the database.
     *
     * Creates a new [CustomSoundEntity] with the provided display name and file path,
     * then inserts it into the database.
     *
     * @param displayName User-friendly name for the custom sound
     * @param filePath Full path or URI to the audio file
     */
    override suspend fun addCustomSound(displayName: String, filePath: String) {
        val entity = CustomSoundEntity(
                displayName = displayName,
                filePath = filePath
            )
        customSoundDao.insertCustomSound(entity)
    }

    /**
     * Removes a custom sound from the database by its ID.
     *
     * @param id The ID of the custom sound to remove
     */
    override suspend fun removeCustomSound(id: Int) {
        customSoundDao.deleteCustomSoundById(id)
    }

    /**
     * Removes a custom sound from the database.
     *
     * @param sound The [CustomSoundEntity] to remove
     */
    override suspend fun removeCustomSound(sound: CustomSoundEntity) {
        customSoundDao.deleteCustomSound(sound)
    }

    /**
     * Retrieves a specific custom sound by its ID.
     *
     * @param id The ID of the custom sound to retrieve
     * @return The [CustomSoundEntity] if found, null otherwise
     */
    override suspend fun getCustomSoundById(id: Int): CustomSoundEntity? {
        return customSoundDao.getCustomSoundById(id)
    }

    /**
     * Updates the display name of an existing custom sound.
     *
     * @param id The ID of the custom sound to update
     * @param displayName The new display name for the custom sound
     */
    override suspend fun updateCustomSoundDisplayName(id: Int, displayName: String) {
        customSoundDao.updateCustomSoundDisplayName(id, displayName)
    }
}
