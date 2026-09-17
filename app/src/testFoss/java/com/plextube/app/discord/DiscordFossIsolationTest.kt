package com.plextube.app.discord

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DiscordFossIsolationTest {
    @Test
    fun `foss classpath excludes functional Discord implementation`() {
        val forbiddenClasses = listOf(
            "com.plextube.app.discord.DiscordTokenStore",
            "com.plextube.app.discord.DiscordAuthTokens",
            "com.plextube.app.discord.DiscordPlaybackSource",
            "com.plextube.app.discord.DiscordPresenceCoordinator",
            "com.plextube.app.discord.KizzyDiscordPresenceTransport",
            "com.plextube.app.discord.KizzyGatewayProtocol",
        )

        forbiddenClasses.forEach { className ->
            assertThat(runCatching { Class.forName(className) }.isFailure).isTrue()
        }
    }

    @Test
    fun `foss runtime reports Discord unavailable`() {
        assertThat(DiscordPresenceRuntime.settingsState.value.isAvailable).isFalse()
        assertThat(DiscordPresenceRuntime.settingsState.value.isEnabled).isFalse()
        assertThat(DiscordPresenceRuntime.settingsState.value.summary)
            .isEqualTo(DiscordSettingsSummary.UNAVAILABLE)
    }
}
