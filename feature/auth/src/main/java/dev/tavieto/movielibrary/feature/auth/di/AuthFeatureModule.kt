package dev.tavieto.movielibrary.feature.auth.di

import dev.tavieto.movielibrary.feature.auth.ui.introduction.IntroductionViewModel
import dev.tavieto.movielibrary.feature.auth.ui.signin.SignInViewModel
import dev.tavieto.movielibrary.feature.auth.ui.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authFeatureModule = module {
    viewModel { IntroductionViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
}
