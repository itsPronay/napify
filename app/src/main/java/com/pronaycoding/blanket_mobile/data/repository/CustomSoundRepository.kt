package com.pronaycoding.blanket_mobile.data.repository

import com.pronaycoding.blanket_mobile.data.local.dao.CustomSoundDao
import com.pronaycoding.blanket_mobile.data.local.entities.CustomSoundEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CustomSoundRepository @Inject constructor(
    private val customSoundDao: CustomSoundDao
) {
    fun getAllCustomSounds(): Flow<List<CustomSoundEntity>> {
        return customSoundDao.getAllCustomSounds()
    }

    suspend fun addCustomSound(displayName: String, filePath: String) {
        val entity = CustomSoundEntity(
            displayName = displayName,
            filePath = filePath
        )
        customSoundDao.insertCustomSound(entity)
    }

    suspend fun removeCustomSound(id: Int) {
        customSoundDao.deleteCustomSoundById(id)
    }

    suspend fun removeCustomSound(sound: CustomSoundEntity) {
        customSoundDao.deleteCustomSound(sound)
    }

    suspend fun getCustomSoundById(id: Int): CustomSoundEntity? {
        return customSoundDao.getCustomSoundById(id)
    }

    suspend fun updateCustomSoundDisplayName(id: Int, displayName: String) {
        customSoundDao.updateCustomSoundDisplayName(id, displayName)
    }
}

