package com.space.conquestofspace.presentation.agencies

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AgenciesScreen(
    viewModel: AgenciesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Log.d("TAG", "AgenciesScreen: number : ${state.agencies.size}")
        Text(text = "agencies ${state.agencies.size}")
    }
}
