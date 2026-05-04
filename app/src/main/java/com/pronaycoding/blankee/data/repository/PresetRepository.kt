package com.pronaycoding.blankee.data.repository

import com.pronaycoding.blankee.data.local.entities.PresetEntity
import kotlinx.coroutines.flow.Flow

interface PresetRepository {

    fun observePresets(): Flow<List<PresetEntity>>

    suspend fun savePreset(entity: PresetEntity): Long

    suspend fun deletePreset(id: Long)
}