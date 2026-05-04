package com.pronaycoding.blanket_mobile.data.repository

import com.pronaycoding.blanket_mobile.data.local.dao.PresetDao
import com.pronaycoding.blanket_mobile.data.local.entities.PresetEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PresetRepository @Inject constructor(
    private val presetDao: PresetDao
) {
    fun observePresets(): Flow<List<PresetEntity>> = presetDao.observeAll()

    suspend fun savePreset(entity: PresetEntity): Long = presetDao.insertPreset(entity)

    suspend fun deletePreset(id: Long) = presetDao.deleteById(id)
}
