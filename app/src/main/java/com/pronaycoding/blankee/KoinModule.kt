package com.pronaycoding.blankee

import com.pronaycoding.blankee.core.PreferenceManagerRepository
import com.pronaycoding.blankee.core.PreferenceManagerRepositoryImpl
import com.pronaycoding.blankee.data.local.BlankeeDatabase
import com.pronaycoding.blankee.data.local.dao.CustomSoundDao
import com.pronaycoding.blankee.data.local.dao.PresetDao
import com.pronaycoding.blankee.data.repository.CustomSoundRepository
import com.pronaycoding.blankee.data.repositoryImpl.CustomSoundRepositoryImpl
import com.pronaycoding.blankee.data.repository.PresetRepository
import com.pronaycoding.blankee.data.repositoryImpl.PresetRepositoryImpl
import com.pronaycoding.blankee.playback.GlobalPlaybackState
import com.pronaycoding.blankee.playback.MediaPlaybackNotifications
import com.pronaycoding.blankee.ui.screens.homeScreen.HomeViewmodel
import com.pronaycoding.blankee.ui.screens.settings.SettingsViewModel
import com.pronaycoding.blankee.ui.screens.homeScreen.SoundManager
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Created by Pronay Sarker on 12/01/2025 (11:01 AM)
 */
object KoinModule {

    private val repositoryModule = module {
        singleOf(::PreferenceManagerRepositoryImpl) bind PreferenceManagerRepository::class
        singleOf(::PresetRepositoryImpl) bind PresetRepository::class
        singleOf(::CustomSoundRepositoryImpl) bind CustomSoundRepository::class
    }
    private val viewmodelModule = module {
        viewModelOf(::HomeViewmodel)
        viewModelOf(::SettingsViewModel)
    }
    private val databaseModule = module{
        single { BlankeeDatabase.getDatabase(get()) }
        single<CustomSoundDao> { get<BlankeeDatabase>().customSoundDao() }
        single<PresetDao> { get<BlankeeDatabase>().presetDao() }
    }
    private val helperClassModules = module{
        single { SoundManager(get()) }
        single { GlobalPlaybackState(get()) }
        single { MediaPlaybackNotifications(get()) }
    }

    val allModules = module {
        includes(
            repositoryModule,
            viewmodelModule,
            databaseModule,
            helperClassModules,
        )
    }

}
