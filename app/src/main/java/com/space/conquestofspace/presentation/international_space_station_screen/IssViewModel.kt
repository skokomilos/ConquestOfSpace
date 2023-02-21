package com.space.conquestofspace.presentation.international_space_station_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.conquestofspace.domain.use_case.get_iss.GetIssUseCase
import com.space.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class IssViewModel @Inject constructor(
    private val getIssUseCase: GetIssUseCase
) : ViewModel() {

    private val _state = mutableStateOf(IssState())
    val state: State<IssState> = _state

    init {
        getIss()
    }

    private fun getIss() {
        getIssUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = IssState(iss = result.data)
                }
                is Resource.Error -> {
                    _state.value = IssState(error = result.message ?: "Undefined error")
                }
                is Resource.Loading -> {
                    _state.value = IssState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
