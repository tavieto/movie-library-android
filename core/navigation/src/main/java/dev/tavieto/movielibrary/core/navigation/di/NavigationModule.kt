package dev.tavieto.movielibrary.core.navigation.di

import dev.tavieto.movielibrary.core.navigation.core.NavigationManager
import dev.tavieto.movielibrary.core.navigation.navigation.AuthNavigationImpl
import dev.tavieto.movielibrary.feature.auth.ui.di.authFeatureModule
import dev.tavieto.movielibrary.feature.auth.ui.introduction.IntroductionNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val navigationModule = module {
    single { NavigationManager(CoroutineScope(SupervisorJob() + Dispatchers.Main)) }

    single<IntroductionNavigation> { AuthNavigationImpl(get()) }

    loadKoinModules(authFeatureModule)
}