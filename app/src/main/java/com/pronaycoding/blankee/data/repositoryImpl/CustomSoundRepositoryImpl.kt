package com.pronaycoding.blankee.data.repositoryImpl

import com.pronaycoding.blankee.data.local.dao.CustomSoundDao
import com.pronaycoding.blankee.data.local.entities.CustomSoundEntity
import com.pronaycoding.blankee.data.repository.CustomSoundRepository
import kotlinx.coroutines.flow.Flow

class CustomSoundRepositoryImpl(
    private val customSoundDao: CustomSoundDao
) : CustomSoundRepository {

    override fun getAllCustomSounds(): Flow<List<CustomSoundEntity>> {
        return customSoundDao.getAllCustomSounds()
    }

    override suspend fun addCustomSound(displayName: String, filePath: String) {
        val entity = CustomSoundEntity(
            displayName = displayName,
            filePath = filePath
        )
        customSoundDao.insertCustomSound(entity)
    }

    override suspend fun removeCustomSound(id: Int) {
        customSoundDao.deleteCustomSoundById(id)
    }

    override suspend fun removeCustomSound(sound: CustomSoundEntity) {
        customSoundDao.deleteCustomSound(sound)
    }

    override suspend fun getCustomSoundById(id: Int): CustomSoundEntity? {
        return customSoundDao.getCustomSoundById(id)
    }

    override suspend fun updateCustomSoundDisplayName(id: Int, displayName: String) {
        customSoundDao.updateCustomSoundDisplayName(id, displayName)
    }
}