package com.space.conquestofspace.presentation.iss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import com.ramcosta.composedestinations.annotation.ActivityDestination
import com.space.conquestofspace.presentation.destinations.AstronautDetailActivityDestination

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

        val args = AstronautDetailActivityDestination.argsFrom(intent)
        println("OtherActivity args = $args")

        setContentView(
            ComposeView(this).apply {
                setContent {
                    Column(
                        modifier = Modifier.background(Color.Cyan)
                    ) {
                        Text("ARGS : astronaut id: /n$args")
                    }
                }
            }
        )
    }
}
