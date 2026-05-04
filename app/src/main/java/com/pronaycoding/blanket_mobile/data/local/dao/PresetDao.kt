package com.pronaycoding.blanket_mobile.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pronaycoding.blanket_mobile.data.local.entities.PresetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PresetDao {
    @Insert
    suspend fun insertPreset(preset: PresetEntity): Long

    @Query("SELECT * FROM presets ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<PresetEntity>>

    @Query("DELETE FROM presets WHERE id = :id")
    suspend fun deleteById(id: Long)
}