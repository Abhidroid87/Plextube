package com.plextube.app.ui.components.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.plextube.app.data.model.Video
import com.plextube.app.ui.components.CompactVideoCard
import com.plextube.app.ui.components.CompactVideoCardThumbnailWidth
import com.plextube.app.ui.components.VideoCardFullWidth

/**
 * One video result, in whichever of the app's two card shapes the row can carry.
 *
 * [asThumbnailRow] picks the thumbnail-left row, which is what a lone card on a wide window needs —
 * the full-width card puts a 16:9 image across the whole screen there. [thumbnailWidth] then sizes
 * that image to one grid column, so a row the grid could not fill still lines up with the cards
 * above it.
 */
@Composable
fun SearchVideoCard(
    video: Video,
    asThumbnailRow: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onChannelClick: ((String) -> Unit)? = null,
    thumbnailWidth: Dp = CompactVideoCardThumbnailWidth,
) {
    if (asThumbnailRow) {
        CompactVideoCard(
            video = video,
            onClick = onClick,
            onChannelClick = onChannelClick,
            thumbnailWidth = thumbnailWidth,
            modifier = modifier,
        )
    } else {
        VideoCardFullWidth(
            video = video,
            onClick = onClick,
            onChannelClick = onChannelClick,
            modifier = modifier,
        )
    }
}
