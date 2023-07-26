package com.space.conquestofspace.presentation.agencies.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.ComposeView
import com.ramcosta.composedestinations.annotation.ActivityDestination
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @author berka on 7/26/23
 */
@ActivityDestination
@AndroidEntryPoint
class AgencyDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            ComposeView(this).apply {
                setContent {
                    AgencyDetailScreen()
                }
            }
        )
    }
}
