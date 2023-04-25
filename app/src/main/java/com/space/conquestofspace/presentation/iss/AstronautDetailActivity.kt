package com.space.conquestofspace.presentation.iss

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.ramcosta.composedestinations.annotation.ActivityDestination

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
class AstronautDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
