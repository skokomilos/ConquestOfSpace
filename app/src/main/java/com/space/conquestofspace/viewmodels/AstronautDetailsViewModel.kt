package com.space.conquestofspace.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.conquestofspace.domain.usecase.GetAstronautUseCase
import com.space.conquestofspace.presentation.iss.AstronautState
import com.space.core.util.Constants.PARAM_ASTRONAUT_ID
import com.space.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class AstronautDetailsViewModel @Inject constructor(
    private val astronautUseCase: GetAstronautUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AstronautState())
    val state: State<AstronautState> = _state

    init {
        savedStateHandle.get<Int>(PARAM_ASTRONAUT_ID)?.let { astronautId ->
            getAstronautById(astronautId)
        }
    }

    private fun getAstronautById(id: Int) {
        astronautUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AstronautState(astronaut = result.data)
                }
                is Resource.Error -> {
                    _state.value = AstronautState(error = result.message ?: "An error occurred")
                }
                is Resource.Loading -> {
                    _state.value = AstronautState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
