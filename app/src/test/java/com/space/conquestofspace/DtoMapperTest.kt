package com.space.conquestofspace
import com.space.conquestofspace.data.local.entity.LaunchEntity
import com.space.conquestofspace.data.remote.dto.LaunchDTO
import com.space.conquestofspace.data.remote.dto.LocationDTO
import com.space.conquestofspace.data.remote.dto.PadDTO
import com.space.conquestofspace.data.remote.dto.RocketConfigurationDTO
import com.space.conquestofspace.data.remote.dto.RocketDTO
import com.space.conquestofspace.data.remote.dto.StatusDTO
import com.space.conquestofspace.domain.model.Rocket
import com.space.conquestofspace.domain.model.Status
import org.junit.Assert.assertEquals
import org.junit.Test

class DtoMapperTest {

    @Test
    fun `map LaunchDTO to LaunchEntity`() {
        val launchDTO = LaunchDTO(
            id = "1",
            url = "url",
            slug = "slug",
            name = "name",
            status = StatusDTO(id = 1, abbrev = "abbrev"),
            last_updated = "last_updated",
            net = "net",
            window_end = "window_end",
            window_start = "window_start",
            rocket = RocketDTO(
                configuration = RocketConfigurationDTO(
                    id = 1,
                    url = "url",
                    name = "name",
                    family = "family",
                    full_name = "full_name"
                ),
                id = 1
            ),
            pad = PadDTO(
                id = 1, url = "url", agency_id = Any(), name = "name", info_url = Any(), wiki_url = "wiki_url",
                location = LocationDTO(
                    id = 1,
                    url = "url",
                    name = "name",
                    country_code = "country_code",
                    map_image = "image"
                ),
                map_image = "map_image", map_url = "map_url"
            ),
            image = "image",
            webcast_live = false
        )

        val expected = LaunchEntity(
            name = "name",
            status = Status(id = 1, abbrev = "abbrev"),
            window_start = "window_start",
            rocket = Rocket(
                configuration = RocketConfigurationDTO(
                    id = 1,
                    url = "url",
                    name = "name",
                    family = "family",
                    full_name = "full_name"
                ),
                id = 1
            ),
            image = "image",
            webcast_live = false
        )

        val result = launchDTO.toLaunchEntity()

        assertEquals(expected, result)
    }

    @Test
    fun `Test RocketDTO toRocket()`() {
        val rocketDto = RocketDTO(
            configuration = RocketConfigurationDTO(
                id = 1,
                url = "url",
                name = "name",
                family = "family",
                full_name = "full_name"
            ),
            id = 2
        )

        val rocket = rocketDto.toRocket()

        assertEquals(rocketDto.configuration.id, rocket.configuration.id)
        assertEquals(rocketDto.configuration.url, rocket.configuration.url)
        assertEquals(rocketDto.configuration.name, rocket.configuration.name)
        assertEquals(rocketDto.configuration.family, rocket.configuration.family)
        assertEquals(rocketDto.configuration.full_name, rocket.configuration.full_name)
        assertEquals(rocketDto.id, rocket.id)
    }

    @Test
    fun `Test PadDTO toPad()`() {
        val padDto = PadDTO(
            id = 1,
            url = "url",
            agency_id = Any(),
            name = "name",
            info_url = Any(),
            wiki_url = "wiki_url",
            location = LocationDTO(
                id = 2,
                url = "url",
                name = "name",
                country_code = "code",
                map_image = "map_image"
            ),
            map_image = "map_image",
            map_url = "map_url"
        )

        val pad = padDto.toPad()

        assertEquals(padDto.id, pad.id)
        assertEquals(padDto.location.id, pad.location.id)
        assertEquals(padDto.location.name, pad.location.name)
    }

    @Test
    fun `Test StatusDTO toStatus()`() {
        val statusDto = StatusDTO(
            id = 1,
            abbrev = "abbrev"
        )

        val status = statusDto.toStatus()

        assertEquals(statusDto.id, status.id)
        assertEquals(statusDto.abbrev, status.abbrev)
    }

    @Test
    fun `Test RocketConfigurationDTO toRocketConfiguration()`() {
        val rocketConfigurationDto = RocketConfigurationDTO(
            id = 1,
            url = "url",
            name = "name",
            family = "family",
            full_name = "full_name"
        )

        val rocketConfiguration = rocketConfigurationDto.toRocketConfiguration()

        assertEquals(rocketConfigurationDto.id, rocketConfiguration.id)
        assertEquals(rocketConfigurationDto.url, rocketConfiguration.url)
        assertEquals(rocketConfigurationDto.name, rocketConfiguration.name)
        assertEquals(rocketConfigurationDto.family, rocketConfiguration.family)
    }
}
