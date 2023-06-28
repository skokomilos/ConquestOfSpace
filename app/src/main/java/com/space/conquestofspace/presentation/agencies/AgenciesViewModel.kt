package com.space.conquestofspace.presentation.agencies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.conquestofspace.domain.usecase.GetAgenciesUseCase
import com.space.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 *
 * @author berka on 6/21/23
 */
@HiltViewModel
class AgenciesViewModel @Inject constructor(
    private val getAgenciesUseCase: GetAgenciesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AgenciesState())
    val state: State<AgenciesState> = _state

    init {
        getAgencies()
    }

    private fun getAgencies() {
        getAgenciesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AgenciesState(agencies = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = AgenciesState(error = result.message ?: "Undefined error")
                }
                is Resource.Loading -> {
                    _state.value = AgenciesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
