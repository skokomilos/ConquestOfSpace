package com.space.conquestofspace.presentation.launch_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.space.conquestofspace.domain.model.Launch

/**
 *
 * @author berka on 2/3/23
 */
@Composable
fun LaunchListItem(
    launch: Launch,
    onItemClick: (Launch) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .clickable { onItemClick(launch) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${launch.name}",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )

    }
}