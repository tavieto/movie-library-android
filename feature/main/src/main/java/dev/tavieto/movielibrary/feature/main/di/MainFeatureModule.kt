package dev.tavieto.movielibrary.feature.main.di

import dev.tavieto.movielibrary.feature.main.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainFeatureModule = module {
    viewModel { HomeViewModel() }
}