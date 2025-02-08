# ChangeLog

## Version 2.8.0-dev

### Android
- Gradle: Remove obsolete repository jcenter
  - JCenter sunset was on August 15th, 2024. mavenCentral will be used instead.
  - The appeareance of jcenter as a repository, produced the gradle warning "Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0."
- Use pure Android X and latest Google Maps SDK
  - Change Imports for Android Support Library to Android X
  - Remove Plugin dependency cordova-androidx-build from plugin.xml. This is not needed anymore.
  - Raise android-minSdkVersion from 19 to 21. This was needed for this PR.
  - Remove 'com.android.support:multidex:1.0.3' from pgm-custom.gradle. This was needed, when the minSdkVersion was lower then 21.
  - Use original Google Maps SDK dependencies again and not any longer a deprecated and decommissioned beta version
  - Google Maps SDK dependencies versions are configurable again with GOOGLE_MAPS_PLAY_SERVICES_VERSION and GOOGLE_MAPS_PLAY_SERVICES_LOCATION_VERSION
  - Cleanup of pgm-custom.gradle
- Fixes Build Error: Could not find com.android.volley:volley:1.1.1.
  - Use implementation 'com.android.volley:volley:1.2.1'
- Fix: Invalid <color> for given resource value.
  - For the deprecated Crosswalk WebView, the preference key "BackgroundColor" was wrongly set with "0". It would have to be set with "#000000", but this would also set the splashscreen backround color to black. So the preference is removed.
- Fix: uses-sdk:minSdkVersion 19 cannot be smaller than version 21 declared in library [androidx.core:core-splashscreen:1.0.0]
  - In plugin.xml "android-minSdkVersion" ist set to 19 for the config.xml and must be set to 21, because the referenced Library "androidx.core:core-splashscreen:1.0.0" defines minSdkVersion to 21. Both cannot live side by side.
- Resolve blank map when running compiled with latest cordova-android (which uses AGP 4.1.3)

## iOS
- Fix misplaced or white splash screen
  - The splash screen on iOS was misplaced or was not appearing. To fix this, the views will not be removed anymore from the view hierarchy and the plugin layer will be send only to the front, when the splash screen is dismissed.
- Remove reference of CDVCommandDelegateImpl
  - Since cordova-ios 7.x. CDVCommandDelegateImpl is private and no longer public

## Common
- Add: (Android/iOS/Browser) `mapOptions.preferences.restriction` which is able to set the camera bounds.
- Add: (Android/iOS/Browser) `mapOptions.preferences.clickableIcons` which is able to be disable clicking on POI icons.
- Bug fix: (Android/iOS/Browser) `mapOptions.preferences.building` does not work.
- Add: (Android/iOS/Browser) ElevationService
- Add: (Android/iOS/Browser) DirectionsService and `map.addDirectionsRenderer()`
- Change: (Android/iOS/Browser) `map.setDiv()`, `map.setOptions()` returns `Promise`.
- Change: (Android/iOS/Browser) Hides `__pluginDomId` and `__pluginMapId` properties.