package dev.tavieto.movielibrary.core.navigation.di

import dev.tavieto.movielibrary.core.navigation.manager.NavigationManager
import dev.tavieto.movielibrary.core.navigation.navigation.AuthNavigationImpl
import dev.tavieto.movielibrary.core.navigation.navigation.MainNavigationImpl
import dev.tavieto.movielibrary.feature.auth.ui.di.authFeatureModule
import dev.tavieto.movielibrary.feature.auth.ui.introduction.IntroductionNavigation
import dev.tavieto.movielibrary.feature.auth.ui.signin.SignInNavigation
import dev.tavieto.movielibrary.feature.main.di.mainFeatureModule
import dev.tavieto.movielibrary.feature.main.ui.details.DetailsNavigation
import dev.tavieto.movielibrary.feature.main.ui.home.HomeNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val navigationModule = module {
    single { NavigationManager(CoroutineScope(SupervisorJob() + Dispatchers.Main)) }

    single<IntroductionNavigation> { AuthNavigationImpl(get()) }
    single<SignInNavigation> { AuthNavigationImpl(get()) }
    single<HomeNavigation> { MainNavigationImpl(get()) }
    single<DetailsNavigation> { MainNavigationImpl(get()) }

    loadKoinModules(authFeatureModule)
    loadKoinModules(mainFeatureModule)
}