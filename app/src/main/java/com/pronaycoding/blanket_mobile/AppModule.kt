package com.pronaycoding.blanket_mobile

import android.content.Context
import com.pronaycoding.blanket_mobile.ui.screens.homeScreen.SoundManager2
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
    fun provideSoundManager2(@ApplicationContext context: Context): SoundManager2 {
        return SoundManager2(context) // Provide context here if SoundManager needs it
    }

}

