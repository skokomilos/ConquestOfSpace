package com.space.conquestofspace.presentation.agencies.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.ComposeView
import com.ramcosta.composedestinations.annotation.ActivityDestination
import com.space.conquestofspace.presentation.destinations.AgencyDetailActivityDestination
import com.space.core.util.Constants.PARAM_AGENCY_ID
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @author berka on 7/26/23
 */
data class AgencyDetailActivityNavArgs(
    val agencyId: Int
)

@ActivityDestination(
    navArgsDelegate = AgencyDetailActivityNavArgs::class
)
@AndroidEntryPoint
class AgencyDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = AgencyDetailActivityDestination.argsFrom(intent)
        savedInstanceState?.putInt(PARAM_AGENCY_ID, args.agencyId)
        println(":show me key id : $args")

        setContentView(
            ComposeView(this).apply {
                setContent {
                    // AgencyDetailScreen()
                }
            }
        )
    }
}
