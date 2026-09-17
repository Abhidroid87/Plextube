# Plextube Starter Guide

This project is a custom starter architecture for a streaming/media app and a scraper-driven Android product. It contains a real working reference stack for:

- Kotlin + Jetpack Compose
- Material 3 UI
- Hilt dependency injection
- Media3 / ExoPlayer playback
- YouTube/streaming extraction using InnerTube and NewPipe fallback
- Room + DataStore local persistence
- recommendations and on-device personalization
- downloads and local media playback

The goal is to help you understand how to build similar projects without starting from zero.

---

## 1. Core project layout

### Root files

- [settings.gradle.kts](settings.gradle.kts)  
  Root Gradle project config and included modules.

- [build.gradle.kts](build.gradle.kts)  
  Top-level plugin and tooling settings.

- [gradle.properties](gradle.properties)  
  Gradle JVM and build behavior settings.

- [AGENTS.md](AGENTS.md)  
  Repo-specific workflow instructions and AI/project rules.

### Android app module

- [app/build.gradle.kts](app/build.gradle.kts)  
  App configuration, flavors, dependencies, signing, build types, package information.

- [app/src/main/AndroidManifest.xml](app/src/main/AndroidManifest.xml)  
  Permissions, activities, deep links, launcher configuration.

- [app/src/main/res/values/strings.xml](app/src/main/res/values/strings.xml)  
  All user-visible strings; this is the place for your app labels and UI text.

### Benchmark module

- [benchmark/build.gradle.kts](benchmark/build.gradle.kts)  
  Benchmark shell for startup/performance tests.

---

## 2. Main Java/Kotlin code areas

### App entry point

- [app/src/main/java/com/plextube/app/MainActivity.kt](app/src/main/java/com/plextube/app/MainActivity.kt)  
  App entry activity; loads the theme and main Compose app surface.

- [app/src/main/java/com/plextube/app/FlowApplication.kt](app/src/main/java/com/plextube/app/FlowApplication.kt)  
  Application-wide setup and shared initialization.

### UI and navigation

- [app/src/main/java/com/plextube/app/ui](app/src/main/java/com/plextube/app/ui)  
  Compose screens, navigation, screen states, shared UI surfaces.

- [app/src/main/java/com/plextube/app/ui/theme](app/src/main/java/com/plextube/app/ui/theme)  
  Colors, typography, theme and Material styling.

### Data and extraction

- [app/src/main/java/com/plextube/app/data](app/src/main/java/com/plextube/app/data)  
  Core data model layer, repository logic, provider access, extraction helpers.

- [app/src/main/java/com/plextube/app/innertube](app/src/main/java/com/plextube/app/innertube)  
  YouTube InnerTube request/response parsing and page extraction logic.

- [app/src/main/java/com/plextube/app/network](app/src/main/java/com/plextube/app/network)  
  Network plumbing, proxy support, HTTP config.

### Playback and services

- [app/src/main/java/com/plextube/app/player](app/src/main/java/com/plextube/app/player)  
  Player state, queue handling, format selection, playback policy.

- [app/src/main/java/com/plextube/app/service](app/src/main/java/com/plextube/app/service)  
  Background services, playback service, downloads service.

### Persistence and settings

- [app/src/main/java/com/plextube/app/di](app/src/main/java/com/plextube/app/di)  
  Hilt modules and dependency wiring.

- [app/src/main/java/com/plextube/app/data/local](app/src/main/java/com/plextube/app/data/local)  
  Room database and local persistence.

- [app/src/main/java/com/plextube/app/data/recommendation](app/src/main/java/com/plextube/app/data/recommendation)  
  Recommendation engine and recsys model logic.

### Flavors

- [app/src/github](app/src/github)  
  GitHub flavor additions such as updater and Discord presence support.

- [app/src/foss](app/src/foss)  
  FOSS flavor with disabled updater/owner-specific integrations.

---

## 3. What this app is doing externally

This app does connect to external services in some parts of the workflow, but it is not a generic backend app. The main connections are:

- YouTube / Google services for metadata and media endpoints
- NewPipe extractor fallback for extraction logic
- Lyrics providers when the user opens lyrics
- optional Discord integration only in the github flavor
- GitHub release/check endpoints in the github flavor only

The project explicitly documents the network hosts in [privacy.md](privacy.md), and that file is useful as a privacy and integration reference when you clone this architecture for your own product. Review and replace every provider endpoint before shipping a derived app.

The core app does not need an account with the original repository owner. It contacts third-party services for content and playback: YouTube/Google endpoints, NewPipe extraction libraries, lyrics providers, SponsorBlock/DeArrow where enabled, and optional Discord or release-update services in the `github` flavor. The `foss` flavor disables the updater and Discord-specific implementation. These are runtime integrations, not AI agents.

Important: this project is a resource to study, not a “clean-room” identity. You should keep the actual third-party endpoints and owners visible where they are required in the code or legal/privacy docs. The app is not a pure offline app; it is a real streaming client with external content retrieval.

---

## 4. Architecture pattern for building your own scraper project

Use this pattern:

1. Source fetch layer
   - fetch HTML, JSON, or API results from a site or service
   - normalize into a clean model

2. Repository layer
   - combine fetch results and cache them
   - provide a stable API for UI and services

3. Model layer
   - map external raw payloads to internal video/channel/song data objects

4. Service/player layer
   - attach stream URL resolution and playback control

5. UI layer
   - show cards, search, details, player, library views

6. Persistence
   - save subscriptions, history, playlists, user preferences

This app does that with a YouTube-centric stack; you can reuse the same architecture for TikTok, Twitch, Vimeo, SoundCloud, or other extracted streams.

---

## 5. Recommended starter implementation path for your own project

### A. If you want a scraper-driven app

Look at:

- [app/src/main/java/com/plextube/app/innertube](app/src/main/java/com/plextube/app/innertube)
- [app/src/main/java/com/plextube/app/data](app/src/main/java/com/plextube/app/data)
- [app/src/main/java/com/plextube/app/network](app/src/main/java/com/plextube/app/network)

Use these as your model for:

- request builders
- parser classes
- repository accessors
- item mapping logic

### B. If you want to build a UI-heavy app

Look at:

- [app/src/main/java/com/plextube/app/ui](app/src/main/java/com/plextube/app/ui)
- [app/src/main/java/com/plextube/app/ui/theme](app/src/main/java/com/plextube/app/ui/theme)
- [app/src/main/java/com/plextube/app/MainActivity.kt](app/src/main/java/com/plextube/app/MainActivity.kt)

Use these as your design and navigation reference for:

- screens
- cards and feed layouts
- Material 3 styling
- app flow and state composition

### C. If you want playback architecture

Look at:

- [app/src/main/java/com/plextube/app/player](app/src/main/java/com/plextube/app/player)
- [app/src/main/java/com/plextube/app/service](app/src/main/java/com/plextube/app/service)

Use these for:

- queue flow
- playback states
- background media service
- player UI integration

### D. If you want recommendation logic

Look at:

- [app/src/main/java/com/plextube/app/data/recommendation](app/src/main/java/com/plextube/app/data/recommendation)

Use this for on-device ranking, watch history learning, similarity, and personalization strategies.

---

## 6. Typical setup for a new project using this architecture

1. Keep your source domain model separate from UI model.
2. Put requests in a network or extractor layer.
3. Normalize everything into a single internal data model.
4. Keep repositories as the single point of truth.
5. Keep UI composables dumb and state-driven.
6. Use Hilt or manual DI to separate providers from screens.
7. Use Room for persistent things, DataStore for settings and preferences.
8. Keep streams and extraction code outside the Compose UI thread.
9. Use preview-friendly composables for UI iteration.
10. Add a dedicated local fallback when network/data is unavailable.

---

## 7. The main tech stack in this app

- Kotlin
- Jetpack Compose
- Material 3
- Hilt
- Coroutines + Flow
- Media3 / ExoPlayer
- Room
- DataStore
- OkHttp
- Kotlin Serialization
- NewPipe extractor + InnerTube
- Android permissions and local media APIs

These are the key building blocks you can reuse in your own streaming app projects.

---

## 8. Final note

This repository is not a project with a hidden framework or magic system. It is a strong example of how a real Android streaming app can be structured.

If you want to turn it into your own app, the most important files are:
- [app/src/main/java/com/plextube/app/service](app/src/main/java/com/plextube/app/service)

Those are the real “map” files for making your own custom project.
---

## 9. Beginner run guide

Open a terminal in the project folder:

```bash
cd /home/abhi/Downloads/Flow
```

This project uses a full JDK 21. On this machine, set it before running Gradle:

```bash
export JAVA_HOME="$HOME/.jdks/jdk-21.0.7+6"
export ANDROID_HOME="$HOME/Android/Sdk"
export PATH="$JAVA_HOME/bin:$ANDROID_HOME/platform-tools:$ANDROID_HOME/emulator:$PATH"
```

Start the existing emulator in a separate terminal. Keep that terminal open:

```bash
emulator -avd Pixel_9_Pro -netdelay none -netspeed full
```

In your project terminal, wait for Android to finish booting:

```bash
adb wait-for-device
adb shell 'while [[ "$(getprop sys.boot_completed)" != "1" ]]; do :; done'
adb devices
```

You should see `emulator-5554 device`.

Build and install the default Github debug flavor:

```bash
./gradlew installGithubDebug --no-daemon --no-configuration-cache \
  -Dorg.gradle.java.installations.auto-detect=false \
  -Dorg.gradle.java.installations.paths="$JAVA_HOME"
```

Launch Plextube manually:

```bash
adb shell am start -n com.plextube.app.debug/com.plextube.app.MainActivity
```

Stop only the app:

```bash
adb shell am force-stop com.plextube.app.debug
```

Start the app again without rebuilding:

```bash
adb shell monkey -p com.plextube.app.debug 1
```

Check whether the app is installed or running:

```bash
adb shell pm list packages | grep com.plextube.app
adb shell dumpsys activity activities | grep com.plextube.app
```

Uninstall the debug app from the emulator:

```bash
adb uninstall com.plextube.app.debug
```

Stop the emulator after you are finished. Run this from another terminal:

```bash
adb emu kill
```


### After changing the logo or UI

Logo edits normally affect these files:

- `app/src/main/res/drawable/ic_flow_logo.xml` for the shared badge logo
- `app/src/main/res/drawable/ic_launcher_foreground.xml` for the launcher and player fallback
- `app/src/main/res/drawable/ic_splash_logo.xml` for the Android startup splash
- `app/src/main/java/com/plextube/app/ui/screens/home/FlowHeaderLogoIcon.kt` for the home header logo

After editing them, repeat the install command. You do not need to uninstall the app:

```bash
./gradlew installGithubDebug --no-daemon --no-configuration-cache \
  -Dorg.gradle.java.installations.auto-detect=false \
  -Dorg.gradle.java.installations.paths="$JAVA_HOME"
adb shell am force-stop com.plextube.app.debug
adb shell am start -n com.plextube.app.debug/com.plextube.app.MainActivity
```

If Android still shows an old launcher icon, restart the launcher or uninstall and reinstall the debug app. The app itself can be refreshed with `adb shell am force-stop`; the emulator can be stopped with `adb emu kill`.
The emulator terminal may print graphics or RAM warnings while booting. Those are usually emulator warnings, not app failures. A successful install ends with `BUILD SUCCESSFUL` and `Installed on 1 device`.
