package com.plextube.app.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.plextube.app.data.local.dao.CacheDao
import com.plextube.app.data.local.dao.DownloadDao
import com.plextube.app.data.local.dao.HomeFeedCacheDao
import com.plextube.app.data.local.dao.MusicGraphDao
import com.plextube.app.data.local.dao.NoteDao
import com.plextube.app.data.local.dao.NotificationDao
import com.plextube.app.data.local.dao.PlaylistDao
import com.plextube.app.data.local.dao.RecognitionHistoryDao
import com.plextube.app.data.local.dao.SubscriptionGroupDao
import com.plextube.app.data.local.dao.SyncLogDao
import com.plextube.app.data.local.dao.SyncPeerDao
import com.plextube.app.data.local.dao.VideoDao
import com.plextube.app.data.local.dao.WatchHistoryDao
import com.plextube.app.data.local.entity.DownloadEntity
import com.plextube.app.data.local.entity.DownloadItemEntity
import com.plextube.app.data.local.entity.HomeFeedCacheEntity
import com.plextube.app.data.local.entity.MusicGraphAlbumEntity
import com.plextube.app.data.local.entity.MusicGraphArtistEntity
import com.plextube.app.data.local.entity.MusicGraphEdgeEntity
import com.plextube.app.data.local.entity.MusicGraphPlaylistEntity
import com.plextube.app.data.local.entity.MusicGraphTrackEntity
import com.plextube.app.data.local.entity.MusicHomeCacheEntity
import com.plextube.app.data.local.entity.MusicHomeChipEntity
import com.plextube.app.data.local.entity.NoteEntity
import com.plextube.app.data.local.entity.NotificationEntity
import com.plextube.app.data.local.entity.PlaylistEntity
import com.plextube.app.data.local.entity.PlaylistVideoCrossRef
import com.plextube.app.data.local.entity.RecognitionHistoryEntity
import com.plextube.app.data.local.entity.SubscriptionFeedEntity
import com.plextube.app.data.local.entity.SubscriptionGroupEntity
import com.plextube.app.data.local.entity.SyncLogEntity
import com.plextube.app.data.local.entity.SyncPeerEntity
import com.plextube.app.data.local.entity.VideoEntity
import com.plextube.app.data.local.entity.WatchHistoryEntity
import com.plextube.app.data.local.migrations.MIGRATIONS
import com.plextube.app.data.local.migrations.Migration24To25

@Database(
    entities = [
        VideoEntity::class,
        PlaylistEntity::class,
        PlaylistVideoCrossRef::class,
        NotificationEntity::class,
        SubscriptionFeedEntity::class,
        MusicHomeCacheEntity::class,
        MusicHomeChipEntity::class,
        DownloadEntity::class,
        DownloadItemEntity::class,
        WatchHistoryEntity::class,
        HomeFeedCacheEntity::class,
        SubscriptionGroupEntity::class,
        RecognitionHistoryEntity::class,
        SyncLogEntity::class,
        SyncPeerEntity::class,
        MusicGraphTrackEntity::class,
        MusicGraphArtistEntity::class,
        MusicGraphAlbumEntity::class,
        MusicGraphPlaylistEntity::class,
        MusicGraphEdgeEntity::class,
        NoteEntity::class,
    ],
    autoMigrations = [
        AutoMigration(from = 24, to = 25, spec = Migration24To25::class),
        AutoMigration(from = 25, to = 26),
        AutoMigration(from = 26, to = 27),
    ],
    version = 27,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao

    abstract fun playlistDao(): PlaylistDao

    abstract fun notificationDao(): NotificationDao

    abstract fun noteDao(): NoteDao

    abstract fun cacheDao(): CacheDao

    abstract fun downloadDao(): DownloadDao

    abstract fun watchHistoryDao(): WatchHistoryDao

    abstract fun homeFeedCacheDao(): HomeFeedCacheDao

    abstract fun subscriptionGroupDao(): SubscriptionGroupDao

    abstract fun recognitionHistoryDao(): RecognitionHistoryDao

    abstract fun syncLogDao(): SyncLogDao

    abstract fun syncPeerDao(): SyncPeerDao

    abstract fun musicGraphDao(): MusicGraphDao

    companion object {
        @Volatile
        @Suppress("ktlint:standard:property-naming")
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: android.content.Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                val instance =
                    databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "flow_database",
                    ).addMigrations(*MIGRATIONS)
                        .fallbackToDestructiveMigration(false)
                        .build()
                INSTANCE = instance
                instance
            }
    }
}
