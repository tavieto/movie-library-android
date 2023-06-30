package dev.tavieto.movielibrary.feature.main.ui.details

import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

class DetailsViewModel(
    private val navigation: DetailsNavigation
) : ViewModel(), KoinComponent {

    fun navigateBack() {
        navigation.popBackStack()
    }
}
