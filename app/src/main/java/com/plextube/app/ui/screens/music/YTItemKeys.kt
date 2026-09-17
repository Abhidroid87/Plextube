package com.plextube.app.ui.screens.music

import com.plextube.app.data.model.distinctByNonBlankKey
import com.plextube.app.innertube.models.AlbumItem
import com.plextube.app.innertube.models.ArtistItem
import com.plextube.app.innertube.models.PlaylistItem
import com.plextube.app.innertube.models.SongItem
import com.plextube.app.innertube.models.YTItem
import com.plextube.app.innertube.pages.SearchSummaryPage

internal fun YTItem.stableIdentityKey(): String {
    if (id.isBlank()) return ""
    return when (this) {
        is SongItem -> "song:$id"
        is AlbumItem -> "album:$id"
        is PlaylistItem -> "playlist:$id"
        is ArtistItem -> "artist:$id"
    }
}

internal fun YTItem.stableLazyKey(namespace: String): String =
    "$namespace:${stableIdentityKey()}"

internal fun Iterable<YTItem>.distinctByStableIdentity(): List<YTItem> =
    distinctByNonBlankKey(YTItem::stableIdentityKey)

internal fun SearchSummaryPage.distinctItemsForLazyKeys(): SearchSummaryPage {
    val seenByTitle = mutableMapOf<String, MutableSet<String>>()
    return copy(
        summaries = summaries.mapNotNull { summary ->
            val seenKeys = seenByTitle.getOrPut(summary.title) { HashSet() }
            summary.copy(
                items = summary.items.filter { item ->
                    val key = item.stableIdentityKey()
                    item.id.isNotBlank() && seenKeys.add(key)
                }
            ).takeIf { it.items.isNotEmpty() }
        }
    )
}
