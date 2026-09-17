package com.plextube.app.ui.components.library

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.plextube.app.data.music.model.MusicTrack
import com.plextube.app.ui.components.music.item.MusicTrackItem
import com.plextube.app.ui.components.shared.MediaRow
import com.plextube.app.ui.components.shared.MediaThumbnail

@Composable
internal fun LibraryMediaListRow(
    track: MusicTrack,
    isMusic: Boolean,
    title: String,
    onVideoClick: () -> Unit,
    onMusicClick: () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    thumbnailUrl: String? = null,
    durationSeconds: Int? = null,
    action: @Composable () -> Unit,
) {
    if (isMusic) {
        MusicTrackItem(
            track = track,
            onClick = onMusicClick,
            showMenu = false,
            modifier = modifier,
            trailingContent = { action() },
        )
    } else {
        MediaRow(
            title = title,
            modifier = modifier,
            subtitle = subtitle,
            onClick = onVideoClick,
            trailing = { action() },
        ) {
            MediaThumbnail(
                videoId = track.videoId,
                thumbnailUrl = thumbnailUrl,
                durationSeconds = durationSeconds,
                showWatchProgress = true,
            )
        }
    }
}
