@file:OptIn(ExperimentalMaterialApi::class)

package com.space.conquestofspace.presentation.agencies

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.space.conquestofspace.data.remote.responses.agencies.Agency
import com.space.conquestofspace.presentation.destinations.AgencyDetailActivityDestination
import com.space.conquestofspace.presentation.ui.theme.ConquestOfSpaceAppTheme

/**
 *
 * @author berka on 6/29/23
 */
@Composable
fun AgencyListItem(
    agency: Agency,
    navigator: DestinationsNavigator?
) {
    val painter: Painter = rememberImagePainter(agency.logo_url)
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier.fillMaxSize(),
        onClick = {
            navigator?.navigate(
                AgencyDetailActivityDestination(agencyId = agency.id)
            )
        }
    ) {
        Column() {
            Image(
                modifier = Modifier.padding(12.dp),
                painter = painter,
                contentDescription = null,
                alignment = Alignment.Center
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AgencyListItemPreview() {
    val agency = Agency(
        abbrev = "NASA",
        administrator = "Administrator: Bill Nelson",
        country_code = "USA",
        description = " is an independent agency of the executive branch of the United States federal government",
        featured = true,
        founding_year = "1958",
        id = 44,
        image_url = "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/agency_images/national2520aeronautics2520and2520space2520administration_image_20190207032448.jpeg",
        launchers = "Space Shuttle | SLS",
        logo_url = "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/media/logo/national2520aeronautics2520and2520space2520administration_logo_20190207032448.png",
        name = "National Aeronautics and Space Administration",
        spacecraft = "",
        type = "Government",
        url = "https://ll.thespacedevs.com/2.2.0/agencies/44/"
    )
    ConquestOfSpaceAppTheme() {
        AgencyListItem(agency = agency, navigator = null)
    }
}
