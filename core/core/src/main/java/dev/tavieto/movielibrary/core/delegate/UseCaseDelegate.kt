package dev.tavieto.movielibrary.core.delegate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raptor.sports.commons.base.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.plus
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

inline fun <V, reified U : UseCase<*, *>> V.useCase() where V : ViewModel, V : KoinComponent =
    inject<U> { parametersOf(viewModelScope + Dispatchers.IO) }
