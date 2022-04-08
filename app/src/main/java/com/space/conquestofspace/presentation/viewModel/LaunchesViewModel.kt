package com.space.conquestofspace.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.space.conquestofspace.domain.use_case.get_launches.GetLaunchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LaunchesViewModel @Inject constructor(
    private val getLaunches: GetLaunchesUseCase
) : ViewModel() {



}