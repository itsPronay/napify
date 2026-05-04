package com.pronaycoding.blankee.data.repository

import com.pronaycoding.blankee.data.local.entities.CustomSoundEntity
import kotlinx.coroutines.flow.Flow


interface CustomSoundRepository {

    fun getAllCustomSounds(): Flow<List<CustomSoundEntity>>

    suspend fun addCustomSound(displayName: String, filePath: String)

    suspend fun removeCustomSound(id: Int)

    suspend fun removeCustomSound(sound: CustomSoundEntity)

    suspend fun getCustomSoundById(id: Int): CustomSoundEntity?

    suspend fun updateCustomSoundDisplayName(id: Int, displayName: String)
}