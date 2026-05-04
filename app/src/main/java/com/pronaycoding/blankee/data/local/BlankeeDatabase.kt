package com.pronaycoding.blankee.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pronaycoding.blankee.data.local.dao.CustomSoundDao
import com.pronaycoding.blankee.data.local.dao.PresetDao
import com.pronaycoding.blankee.data.local.entities.CustomSoundEntity
import com.pronaycoding.blankee.data.local.entities.PresetEntity

@Database(
    entities = [
        CustomSoundEntity::class,
        PresetEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BlankeeDatabase : RoomDatabase() {
    abstract fun customSoundDao(): CustomSoundDao
    abstract fun presetDao(): PresetDao

    companion object {
        @Volatile
        private var INSTANCE: BlankeeDatabase? = null

        fun getDatabase(context: Context): BlankeeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BlankeeDatabase::class.java,
                    "blankee_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

