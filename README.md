# Plextube Starter

This repository has been repurposed as a custom starter for a Plextube-style streaming app.

The visible app name is Plextube, while the underlying project still contains the original upstream code structure so it can be used as a learning/reference base for your own streaming and extractor projects.

This document is intentionally a starter guide rather than a marketing page. Use it as a map for the architecture, the main folders, and the extension points you will need when building a custom scraping, playback, or media UI project.

---

## Why this project is useful

This repository is a practical Android app architecture example for:

- YouTube/streaming scrapers and metadata extraction
- Media playback with Media3 / ExoPlayer
- Compose Material 3 UI patterns
- Offline downloads and local media
- Recommendation logic that runs on device
- Feature split by flavor (`github` and `foss`)

It is not a finished product identity file; it is an app foundation you can adapt into your own product.

---

## Features

### Video
- High-quality playback via ExoPlayer (Media3) with resolution switching (1080p, 720p, 480p, 360p)
- SponsorBlock — automatically skips sponsors, intros, outros, and filler
- DeArrow — replaces clickbait thumbnails and titles with community-sourced alternatives
- Return Youtube Dislike (RYD)
- Background playback — listen to audio with the screen off
- Picture-in-Picture — keep watching while using other apps
- Casting to smart TVs and streaming devices
- Playback speed control (0.25x to 2x)
- Video chapters with seek jumping
- Gesture controls for brightness, volume, and seeking
- Subtitles with customizable font size, color, and background
- Downloads with VP9, AV1, and standard format support
- Resume playback from where you left off

### Music
- Dedicated music player with album art and audio visualizations
- Queue management with add, remove, and reorder
- Shuffle and repeat (single/all)
- Persistent mini player across the app
- Synchronized lyrics display
- Fetches tracks from YouTube Music

### Recommendations (PlexNeuro Engine)
- Runs 100% on-device — no server, no telemetry, no account needed
- Learns from what you watch, skip, like, dislike, search for, and how long you watch
- Distinguishes weekday and weekend patterns, morning and night preferences
- Detects when you're getting bored of a topic and mixes in new content
- Prevents your feed from collapsing into the same 2-3 topics
- Surfaces related videos from your recent watches to create natural topic transitions
- Uses engagement signals (like-to-view ratios) to filter out low-quality content
- Full transparency dashboard — see what the algorithm knows and why it recommended something
- Export/import your entire recommendation profile as a file

### Library
- Local watch history
- Favorites and custom playlists
- Shorts feed with bookmarking
- Continue watching shelf
- Subscription management with cached feeds

### Privacy
- No Google account required
- No ads, analytics, or tracking
- All data stored locally on your device
- Import subscriptions and history from NewPipe
- Export or delete everything at any time

### Appearance
- 11 themes: Light, Dark, OLED Black, Ocean Blue, Forest Green, Sunset Orange, Purple Nebula, Midnight Black, Rose Gold, Arctic Ice, Crimson Red
- Built entirely with Jetpack Compose and Material 3

---

### Requirements 
**Minimum Requirement:** Android 8.0+
<a id="cert"></a>
### Verifying Authenticity
To ensure the authenticity of the APK and verify it has not been tampered with, you can check the signing certificate fingerprint using tools like [AppVerifier](https://github.com/soupslurpr/AppVerifier).

**Release Certificate SHA-256 Fingerprint:**
`43:22:29:4E:D4:CA:A2:D4:29:41:40:09:58:18:08:0F:FE:8A:CC:1F:BE:3C:DC:76:10:7D:F4:5C:52:86:BE:40`

---


## 🙏 Acknowledgments

Plextube stands on the shoulders of giants. Special thanks to:

*   **[NewPipeExtractor](https://github.com/TeamNewPipe/NewPipeExtractor):** The backbone of our data extraction.
*   **[NewPipe](https://github.com/TeamNewPipe/NewPipe):** For inspiration from their solid foundation for YouTube data handling.
*   **[PipePipe](https://codeberg.org/NullPointerException/PipePipe):** For their SABR and InnerTube playback implementation, which guided Plex YouTube streaming pipeline.
*   **[PipePipe Developer Docs](https://priveetee.github.io/Docs-PipePipe/):** For their reference documentation on SABR, BotGuard/PoToken attestation, and InnerTube extraction internals.
*   **[MetroList](https://github.com/MetrolistGroup/Metrolist):** Inspiration for the Hybrid Music fetching approach, Lyrics handling and some icons design references.
*   **[LibreTube](https://github.com/LibreTube/LibreTube):** Inspiration for SponsorBlock and DeArrow handling and some icons design references.
*   **[ExoPlayer](https://github.com/google/ExoPlayer):** The gold standard for Android media playback.
*   **[Jetpack Compose](https://developer.android.com/jetpack/compose):** For enabling the beautiful, modern UI.
*   **[Material Design 3](https://m3.material.io/):** For the design system and guidelines.

---

<div align="center">

<a id="translate"></a>
## Translations
Help translate PlexTube into your language! 

[![Translation status](https://hosted.weblate.org/widget/flow/strings/287x66-grey.png)](https://hosted.weblate.org/engage/flow/)

[![Translation status](https://hosted.weblate.org/widget/flow/strings/horizontal-auto.svg)](https://hosted.weblate.org/engage/flow/)
</div>

---
