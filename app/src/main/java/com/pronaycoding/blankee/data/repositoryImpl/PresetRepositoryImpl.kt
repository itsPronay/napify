package com.pronaycoding.blankee.data.repositoryImpl

import com.pronaycoding.blankee.data.local.dao.PresetDao
import com.pronaycoding.blankee.data.local.entities.PresetEntity
import com.pronaycoding.blankee.data.repository.PresetRepository
import kotlinx.coroutines.flow.Flow

class PresetRepositoryImpl(
    private val presetDao: PresetDao
) : PresetRepository {

    override fun observePresets(): Flow<List<PresetEntity>> =
        presetDao.observeAll()

    override suspend fun savePreset(entity: PresetEntity): Long =
        presetDao.insertPreset(entity)

    override suspend fun deletePreset(id: Long) =
        presetDao.deleteById(id)
}
