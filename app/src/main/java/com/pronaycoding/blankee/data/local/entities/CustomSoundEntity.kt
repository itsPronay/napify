package com.pronaycoding.blankee.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "custom_sounds")
data class CustomSoundEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val displayName: String,
    val filePath: String,
    val createdAt: Long = System.currentTimeMillis()
)