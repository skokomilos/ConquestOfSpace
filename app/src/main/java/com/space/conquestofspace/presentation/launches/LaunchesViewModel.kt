package com.space.conquestofspace.presentation.launches

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.conquestofspace.domain.usecase.getlaunches.GetLaunchesUseCase
import com.space.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class LaunchesViewModel @Inject constructor(
    private val getLaunchesUseCase: GetLaunchesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(LaunchesState())
    val state: State<LaunchesState> = _state

    init {
        getLaunches()
    }

    private fun getLaunches() {
        getLaunchesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = LaunchesState(launches = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = LaunchesState(error = result.message ?: "Undefined error")
                }
                is Resource.Loading -> {
                    _state.value = LaunchesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
