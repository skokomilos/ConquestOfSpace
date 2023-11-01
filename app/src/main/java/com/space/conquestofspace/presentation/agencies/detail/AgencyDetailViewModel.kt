package com.space.conquestofspace.presentation.agencies.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.conquestofspace.domain.usecase.agency.GetAgencyUseCase
import com.space.core.util.Constants.PARAM_AGENCY_ID
import com.space.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 *
 * @author berka on 6/21/23
 */
@HiltViewModel
class AgencyDetailViewModel @Inject constructor(
    private val getAgencyUseCase: GetAgencyUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AgencyDetailState())
    val state: State<AgencyDetailState> = _state

    init {
        savedStateHandle.get<Int>(PARAM_AGENCY_ID)?.let { id ->
            println("handle into")
            getAgencies(id)
        }
    }

    private fun getAgencies(id: Int) {
        getAgencyUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AgencyDetailState(agency = result.data)
                }
                is Resource.Error -> {
                    _state.value = AgencyDetailState(error = result.message ?: "Undefined error")
                }
                is Resource.Loading -> {
                    _state.value = AgencyDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
