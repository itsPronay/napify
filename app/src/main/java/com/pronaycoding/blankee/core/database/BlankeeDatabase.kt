package com.pronaycoding.blankee.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pronaycoding.blankee.core.database.dao.CustomSoundDao
import com.pronaycoding.blankee.core.database.dao.PresetDao
import com.pronaycoding.blankee.core.database.entities.CustomSoundEntity
import com.pronaycoding.blankee.core.database.entities.PresetEntity

/**
 * Room database for the Blankee application.
 *
 * This database manages persistent storage for:
 * - Custom sound files added by users ([CustomSoundEntity])
 * - Sound presets (combinations of sounds with specific volumes) ([PresetEntity])
 *
 * The database uses the Singleton pattern to ensure only one instance exists throughout the app lifecycle.
 * All database access is performed through Data Access Objects (DAOs) which provide type-safe database queries.
 *
 * @see CustomSoundDao for custom sound database operations
 * @see PresetDao for preset database operations
 * @see CustomSoundEntity for custom sound data model
 * @see PresetEntity for preset data model
 */
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

        /**
         * Returns the singleton instance of BlankeeDatabase.
         *
         * This method implements the thread-safe singleton pattern using double-checked locking.
         * The first call initializes the database, and subsequent calls return the cached instance.
         *
         * The database is created with:
         * - Database name: "blankee_database"
         * - No schema export (schema is not exported to files for debugging)
         *
         * @param context The application context used to create the database
         * @return The singleton instance of BlankeeDatabase
         * @see Room.databaseBuilder for Room database builder configuration
         */
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

