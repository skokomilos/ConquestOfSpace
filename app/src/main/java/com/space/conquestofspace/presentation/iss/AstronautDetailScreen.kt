package com.space.conquestofspace.presentation.iss

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.space.conquestofspace.R
import com.space.conquestofspace.data.remote.responses.astronaut.Agency
import com.space.conquestofspace.data.remote.responses.astronaut.AstronautResponse
import com.space.conquestofspace.data.remote.responses.astronaut.StatusXXX
import com.space.conquestofspace.data.remote.responses.astronaut.TypeX
import com.space.conquestofspace.presentation.ui.theme.ConquestOfSpaceAppTheme
import com.space.conquestofspace.viewmodels.AstronautDetailsViewModel

@Composable
fun AstronautDetailScreen(
    viewModel: AstronautDetailsViewModel = hiltViewModel(),
    astronautId: Int
) {
    val state = viewModel.state.value

    ConquestOfSpaceAppTheme {
        Surface() {
            AstronautDetails(state)
        }
    }
}

@Composable
fun AstronautDetails(state: AstronautState) {
    val scrollState = rememberScrollState()
    state.astronaut?.let { astronaut ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Magenta),
            // verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AstronautDetailsContent(
                astronaut = astronaut,
                scrollState = scrollState
            )
        }
    }
}

@Composable
fun AstronautDetailsContent(
    scrollState: ScrollState,
    astronaut: AstronautResponse
) {
    Box(modifier = Modifier.fillMaxSize()) {
        AstronautImage(
            imageUrl = astronaut.profile_image
        )
        Column(
            Modifier
                .verticalScroll(scrollState)
                .align(Alignment.BottomCenter)
        ) {
            Spacer(Modifier.weight(1f))
            ConstraintLayout(Modifier.fillMaxHeight()) {
                val (card) = createRefs()
                val attributeMap = createAstronautAttributeMap(astronaut)

                AstronautsData(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .constrainAs(card) {
                            bottom.linkTo(parent.bottom)
                        },
                    name = astronaut.name,
                    imageUrl = astronaut.profile_image_thumbnail,
                    bio = astronaut.bio,
                    twitter = astronaut.twitter,
                    instagram = astronaut.instagram,
                    wiki = astronaut.wiki,
                    birth = astronaut.date_of_birth,
                    nationality = astronaut.nationality,
                    agency = astronaut.agency,
                    astronautAttributeMap = attributeMap
                )
            }
        }
    }
}

@Composable
fun AstronautsData(
    modifier: Modifier = Modifier,
    name: String,
    imageUrl: String,
    bio: String,
    twitter: String?,
    instagram: String?,
    wiki: String?,
    birth: String,
    nationality: String,
    agency: Agency,
    astronautAttributeMap: Map<AstronautAttribute, Any>
) {
    Card(
        modifier =
        modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Box(
            modifier
                .background(Color.White)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f)
                    ) {
                        com.skydoves.landscapist.glide.GlideImage(
                            imageModel = { imageUrl },
                            modifier = Modifier
                                .size(88.dp)
                                .clip(CircleShape),
                            previewPlaceholder = R.drawable.astronaut
                        )
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(6.dp),
                        Arrangement.End
                    ) {
                        SocialMediaImage(image = R.drawable.twitter_icon, url = twitter)
                        SocialMediaImage(image = R.drawable.instagram_icon, url = instagram)
                        SocialMediaImage(image = R.drawable.wikipedia_icon, url = wiki)
                    }
                }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = name,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h4,
                    color = Color.Black,
                    fontSize = 20.sp
                )
                Text(
                    modifier =
                    Modifier.fillMaxWidth(),
                    color = Color.Black,
                    text = bio,
                    textAlign = TextAlign.Start
                )
                BirthdayNationalityAgency(birth, nationality, agency)
            }
        }

        // Content of the card goes here
    }
}

@Composable
fun AstronautImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxSize()
    ) {
        // SpaceImage(model = imageUrl, contentDescription = null, imageHeight = 200.dp)
        FullScreenImage(imageUrl)
    }
}

@Composable
fun SocialMediaImage(
    image: Int,
    url: String?
) {
    val uriHandler = LocalUriHandler.current
    Box(
        modifier = Modifier
            .clickable {
                if (!url.isNullOrBlank()) {
                    uriHandler.openUri(url)
                }
            }
            .size(48.dp)
    ) {
        com.skydoves.landscapist.glide.GlideImage(
            imageModel = { image },
            modifier = Modifier
                .padding(8.dp)
                .wrapContentSize()
                .clip(CircleShape),
            previewPlaceholder = R.drawable.twitter_icon
        )
    }
}

@Composable
fun BirthdayNationalityAgency(birth: String, nationality: String, agency: Agency) {
    Box(modifier = Modifier) {
        Row() {
            SmallCardWithHeadlineAndData("birth", birth, modifier = Modifier.weight(1f))
            SmallCardWithHeadlineAndData(
                "nationality",
                nationality,
                modifier = Modifier.weight(1f)
            )
            SmallCardWithHeadlineAndData("agency", agency.name, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun SmallCardWithHeadlineAndData(
    headline: String,
    personalData: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            fontSize = 16.sp,
            text = headline.uppercase(),
            color = Color.Gray,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            color = Color.Black,
            fontSize = 14.sp,
            text = personalData,
            textAlign = TextAlign.Center
        )
    }
}

@Preview()
@Composable
fun SmallCardWithHeadlineAndDataPreview() {
    SmallCardWithHeadlineAndData("headline", "12/12/2000")
}

@Preview
@Composable
fun BirthdayNationalityAgencyPreview() {
    Row(modifier = Modifier.wrapContentSize()) {
        SmallCardWithHeadlineAndData("headline", "12/12/2000", modifier = Modifier.weight(1f))
        SmallCardWithHeadlineAndData("headline", "12/12/2000", modifier = Modifier.weight(1f))
        SmallCardWithHeadlineAndData("headline", "12/12/2000", modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun AstronautDetailsPreview() {
    ConquestOfSpaceAppTheme {
        val astronaut = AstronautResponse(
            age = 44,
            agency = Agency(
                id = 1,
                name = "NASA",
                type = "Government",
                url = ""
            ),
            bio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "\n Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            date_of_birth = "1979-07-15",
            date_of_death = "null",
            first_flight = "2009-05-11",
            flights = listOf(),
            flights_count = 1,
            id = 546,
            in_space = true,
            instagram = "null",
            landings = listOf(),
            landings_count = 1,
            last_flight = "null",
            name = "Stephen Bowen",
            nationality = "American",
            profile_image = "",
            profile_image_thumbnail = "null",
            status = StatusXXX(
                id = 1,
                name = "Active"
            ),
            twitter = "null",
            type = TypeX(
                id = 1,
                name = "NASA Astronaut"
            ),
            url = "",
            wiki = "null"
        )
        val state = AstronautState(astronaut = astronaut)
        AstronautDetails(state = state)
    }
}
enum class AstronautAttribute(val attributeName: String) {
    BIRTH("birth"),
    NATIONALITY("nationality"),
    AGENCY("agency")
}

fun createAstronautAttributeMap(
    astronaut: AstronautResponse
): Map<AstronautAttribute, Any> {
    return mapOf(
        AstronautAttribute.BIRTH to astronaut.date_of_birth,
        AstronautAttribute.NATIONALITY to astronaut.nationality,
        AstronautAttribute.AGENCY to astronaut.agency
    )
}
