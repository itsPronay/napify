package com.pronaycoding.blanket_mobile.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pronaycoding.blanket_mobile.data.local.dao.CustomSoundDao
import com.pronaycoding.blanket_mobile.data.local.dao.PresetDao
import com.pronaycoding.blanket_mobile.data.local.entities.CustomSoundEntity
import com.pronaycoding.blanket_mobile.data.local.entities.PresetEntity

@Database(
    entities = [
        CustomSoundEntity::class,
        PresetEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class NapifyDatabase : RoomDatabase() {
    abstract fun customSoundDao(): CustomSoundDao
    abstract fun presetDao(): PresetDao

    companion object {
        @Volatile
        private var INSTANCE: NapifyDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `presets` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `name` TEXT NOT NULL,
                        `builtInVolumesJson` TEXT NOT NULL,
                        `customVolumesJson` TEXT NOT NULL,
                        `createdAt` INTEGER NOT NULL
                    )
                    """.trimIndent()
                )
            }
        }

        fun getDatabase(context: Context): NapifyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NapifyDatabase::class.java,
                    "napify_database"
                )
//                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

