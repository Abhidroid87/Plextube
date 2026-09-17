package com.plextube.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.plextube.app.data.local.PlayerPreferences
import com.plextube.app.data.repository.YouTubeRepository
import com.plextube.app.data.shorts.ChannelReelIndex
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideYouTubeRepository(
        playerPreferences: PlayerPreferences,
        channelReelIndex: ChannelReelIndex,
    ): YouTubeRepository = YouTubeRepository.getInstance(playerPreferences, channelReelIndex)

    @Provides
    @Singleton
    fun provideSubscriptionRepository(
        @ApplicationContext context: Context,
    ): com.plextube.app.data.local.SubscriptionRepository =
        com.plextube.app.data.local.SubscriptionRepository
            .getInstance(context)

    @Provides
    @Singleton
    fun provideLikedVideosRepository(
        @ApplicationContext context: Context,
    ): com.plextube.app.data.local.LikedVideosRepository =
        com.plextube.app.data.local.LikedVideosRepository
            .getInstance(context)

    @Provides
    @Singleton
    fun provideViewHistory(
        @ApplicationContext context: Context,
    ): com.plextube.app.data.local.ViewHistory =
        com.plextube.app.data.local.ViewHistory
            .getInstance(context)

    @Provides
    @Singleton
    fun provideHomeFeedCacheRepository(
        @ApplicationContext context: Context,
    ): com.plextube.app.data.local.HomeFeedCacheRepository =
        com.plextube.app.data.local
            .HomeFeedCacheRepository(context)

    @Provides
    @Singleton
    fun provideMusicPlaylistRepository(
        @ApplicationContext context: Context,
    ): com.plextube.app.data.music.PlaylistRepository =
        com.plextube.app.data.music
            .PlaylistRepository(context)

    // VideoDownloadManager is now @Singleton @Inject — Hilt provides it automatically
    @Provides
    @Singleton
    fun providePlayerPreferences(
        @ApplicationContext context: Context,
    ): com.plextube.app.data.local.PlayerPreferences =
        com.plextube.app.data.local
            .PlayerPreferences(context)

    @Provides
    @Singleton
    fun provideShortsRepository(
        @ApplicationContext context: Context,
    ): com.plextube.app.data.shorts.ShortsRepository =
        com.plextube.app.data.shorts.ShortsRepository
            .getInstance(context)
}
