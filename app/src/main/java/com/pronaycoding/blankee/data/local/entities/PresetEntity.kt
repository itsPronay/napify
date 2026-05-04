package com.pronaycoding.blankee.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "presets")
data class PresetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val builtInVolumesJson: String,
    val customVolumesJson: String,
    val createdAt: Long = System.currentTimeMillis()
)