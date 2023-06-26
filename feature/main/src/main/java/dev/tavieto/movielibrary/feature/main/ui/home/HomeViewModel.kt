package dev.tavieto.movielibrary.feature.main.ui.home

import androidx.lifecycle.ViewModel
import dev.tavieto.movielibrary.core.delegate.useCase
import dev.tavieto.movielibrary.domain.auth.usecase.SignOutUseCase
import org.koin.core.component.KoinComponent

class HomeViewModel: ViewModel(), KoinComponent {

    private val signOutUseCase: SignOutUseCase by useCase()

    fun signOut() {
        signOutUseCase()
    }
}
