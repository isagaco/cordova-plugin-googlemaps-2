# ChangeLog

## Version 2.8.1-dev

## Version 2.8.0 (24.03.2025)

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
- Add Google Maps SDK Version 9.3.0 with Cocoapods
  - Google Maps can now be tested in the simulator
- Fix misplaced or white splash screen
  - The splash screen on iOS was misplaced or was not appearing. To fix this, the views will not be removed anymore from the view hierarchy and the plugin layer will be send only to the front, when the splash screen is dismissed.
- Fix all warnings when using `cordova-ios` 7
  - Remove reference of `CDVCommandDelegateImpl`
    - Since `cordova-ios` 7.x. `CDVCommandDelegateImpl` is private and no longer public
  - Remove deprecated `extra.address` from `Geocoder.geocode` result
    - `CLPlacemark.addressDictionary` is deprecated and was used for this field
  - PluginLocationService.m: Support for iOS 14 
    - On iOS 14 [CLLocationManager authorizationStatus] is deprecated and instead [CLLocationManager new].authorizationStatus should be called
  - Remove unused methods from PluginDirectionsService.h. Seems to be a leftover from copy & paste of PluginElevationService.h
  - Use GMSMapViewOptions for init GMSMapView since iOS 14 and Google Maps SDK 8.3.0
    - [GMSMapView mapWithFrame] is deprecated, use only on iOS 13 and Google Maps SDK older then 8.3.0
  - Fix warnings "A block declaration without a prototype is deprecated"
    - void had to be added to all these statements (proposed fix by XCode)
  - Remove unused method PluginUtil.urlencode
  - Add PluginCAAnimationDelegate for markers: The animationDelegate for markers was not set to a real delegate and was giving a warning. The CAAnimationGroup was overwritten and set as delegate, but was not implementing the CAAnimationDelegate. Now a  custom delegate is created which calls the completion block.
  - Define nullability for property and parameters in PluginStreetViewPanoramaController.h
    - XCode has issued a warning, that the nullability was not defined for the panoramaView property and parameters

## Common
- Add: (Android/iOS/Browser) `mapOptions.preferences.restriction` which is able to set the camera bounds.
- Add: (Android/iOS/Browser) `mapOptions.preferences.clickableIcons` which is able to be disable clicking on POI icons.
- Bug fix: (Android/iOS/Browser) `mapOptions.preferences.building` does not work.
- Add: (Android/iOS/Browser) ElevationService
- Add: (Android/iOS/Browser) DirectionsService and `map.addDirectionsRenderer()`
- Change: (Android/iOS/Browser) `map.setDiv()`, `map.setOptions()` returns `Promise`.
- Change: (Android/iOS/Browser) Hides `__pluginDomId` and `__pluginMapId` properties.

## 2.7.1

- Fix: (iOS) UiWebView references present in v2.7.0

## 2.7.0

- Re-adoption: cordova-plugin-googlemaps-sdk dependency
- Important update: No longer support UIWebView on iOS. WKWebView only.
- Fix: (iOS) Can't load image files from local host on ionic 4 / 5
- Update: (Android) prevent null pointer error in AsyncLoadImage.java
- Fix: Css animation interference when call setDiv and there is a push/pop page
- Fix: (Android/iOS/Browser) KML parser crash
- Fix: flickering and wrong rendering of some DOM elements
- Add: map.stopAnimation()
- Fix: can't remove map while map.animateCamera() is running
- Update: (Android) Increase cache memory size
- Update: (Android/iOS) Danish localization
- Fix: (Android) Prevent resize event after map.setDiv(null)
- Fix: (Android/iOS) Can not interactive with the map inside
- Fix: jslint errors
- Fix: marker.setIcon crashes
- Update: Set default value range to heading and tilt
- Fix: (Android/iOS) touch detection is wrong after clicking on back button very soon.
- Fix: An error occurs when you click a marker of marker cluster #2660
- Remove promise-7.0.4-min.js.map
- Fix: (iOS) bug fix: App crashes if "bearing" property is ""
- Fix: HTMLColor2RGBA() converts to incorrect value
- Fix: (Android) Can't load marker image from the Internet
- many bug fixes...