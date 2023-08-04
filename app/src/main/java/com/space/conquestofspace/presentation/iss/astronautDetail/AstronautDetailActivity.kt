package com.space.conquestofspace.presentation.iss.astronautDetail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.ComposeView
import com.ramcosta.composedestinations.annotation.ActivityDestination
import com.space.conquestofspace.presentation.destinations.AstronautDetailActivityDestination
import com.space.core.util.Constants.PARAM_ASTRONAUT_ID
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @author berka on 4/25/23
 */
data class AstronautDetailActivityNavArgs(
    val astronautID: Int
)

@ActivityDestination(
    navArgsDelegate = AstronautDetailActivityNavArgs::class
)
@AndroidEntryPoint
class AstronautDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = AstronautDetailActivityDestination.argsFrom(intent)
        savedInstanceState?.putInt(PARAM_ASTRONAUT_ID, args.astronautID)

        setContentView(
            ComposeView(this).apply {
                setContent {
                    AstronautDetailScreen()
                }
            }
        )
    }
}
