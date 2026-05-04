package com.pronaycoding.blanket_mobile

import android.content.Context
import com.pronaycoding.blanket_mobile.data.local.dao.CustomSoundDao
import com.pronaycoding.blanket_mobile.data.local.NapifyDatabase
import com.pronaycoding.blanket_mobile.data.local.dao.PresetDao
import com.pronaycoding.blanket_mobile.data.repository.CustomSoundRepository
import com.pronaycoding.blanket_mobile.data.repository.PresetRepository
import com.pronaycoding.blanket_mobile.ui.screens.homeScreen.SoundManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Pronay Sarker on 12/01/2025 (11:01 AM)
 */
@Module
@InstallIn(SingletonComponent::class) // Specify the Hilt component where this module will be installed
object AppModule {

    @Provides
    @Singleton
    fun provideSoundManager2(@ApplicationContext context: Context): SoundManager {
        return SoundManager(context) // Provide context here if SoundManager needs it
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NapifyDatabase {
        return NapifyDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideCustomSoundDao(database: NapifyDatabase): CustomSoundDao = database.customSoundDao()

    @Singleton
    @Provides
    fun providePresetDao(database: NapifyDatabase): PresetDao = database.presetDao()

    @Singleton
    @Provides
    fun provideCustomSoundRepository(customSoundDao: CustomSoundDao): CustomSoundRepository {
        return CustomSoundRepository(customSoundDao)
    }

    @Singleton
    @Provides
    fun providePresetRepository(presetDao: PresetDao): PresetRepository {
        return PresetRepository(presetDao)
    }

}

