# Cordova GoogleMaps plugin for Android, iOS and Browser v2.8.0-dev

This is the continuation of the discontinued plugin [cordova-plugin-googlemaps](https://github.com/mapsplugin/cordova-plugin-googlemaps).

This plugin allows you to display a native Google Maps layer in your application and uses the following libraries:

- Android: [Google Maps Android API](https://developers.google.com/maps/documentation/android/)
- iOS: [Google Maps SDK for iOS](https://developers.google.com/maps/documentation/ios/)
- Browser: [Google Maps JavaScript API v3](https://developers.google.com/maps/documentation/javascript/)

## Guides

  - [How to generate API keys?](https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/blob/master/v2.6.0/api_key/README.md)
  - [Hello, World](https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/blob/master/v2.6.0/hello-world/README.md)
  - [Trouble shootings](https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/tree/master/troubleshootings/README.md)

## Installation

### GitHub
```bash
cordova plugin add https://github.com/GitToTheHub/cordova-plugin-googlemaps-2
```

Then set your Google Maps API keys into your `config.xml` (Android / iOS).

  ```xml
  <widget ...>
    <preference name="GOOGLE_MAPS_ANDROID_API_KEY" value="(api key)" />
    <preference name="GOOGLE_MAPS_IOS_API_KEY" value="(api key)" />
  </widget>
  ```

For browser platform,

  ```js
  // If your app runs this program on browser,
  // you need to set `API_KEY_FOR_BROWSER_RELEASE` and `API_KEY_FOR_BROWSER_DEBUG`
  // before `plugin.google.maps.Map.getMap()`
  //
  //   API_KEY_FOR_BROWSER_RELEASE for `https:` protocol
  //   API_KEY_FOR_BROWSER_DEBUG for `http:` protocol
  //
  plugin.google.maps.environment.setEnv({
    'API_KEY_FOR_BROWSER_RELEASE': '(YOUR_API_KEY_IS_HERE)',
    'API_KEY_FOR_BROWSER_DEBUG': ''  // optional
  });

  // Create a Google Maps native view under the map_canvas div.
  var map = plugin.google.maps.Map.getMap(div);

  ```

### iOS
This plugin uses Cocoapods since Version 2.8.0 to add the Google Maps SDK as a dependency. Before it was integrated by a [repository clone](https://github.com/mapsplugin/cordova-plugin-googlemaps-sdk) and was at least set to version 3.9.0.
This plugin requires to set iOS 15.5 as the minimum deployment target. This is no problem, because all phones which support iOS 13/14 support iOS 15 also. The latest phones which support maximum iOS 15 are the iPhone 6s & 6s Plus, first-generation iPhone SE, iPhone 7 & 7 Plus, and iPod Touch and these devices are already very old (from 2015/2016).

The plugin uses Google Maps SDK version 9.3.0, which is the latest version as of March 2025.

Since Google Maps SDK version 7.3.0 it's possible to run the plugin on a simulator on a Mac with a M CPU (Apple Silicon), but there are some problems with the Metal Renderer (see https://issuetracker.google.com/issues/338162114). As a workaround OpenGL will be used, but which is slower.  See [Problems with Google Maps SDK](#problems-with-google-maps-sdk) for more details. On a simulator with iOS 15, it can also crash with OpenGL, so it's recommended to test only from iOS 16 onwards in a simulator.

If you upgrade from plugin version 2.7.1 to Version 2.8.0 you have to remove the old GoolgeMaps dependency:

```bash
cordova plugin remove com.googlemaps.ios
```

Also you have to remove the old GoogleMaps dependency from your `package.json`:

```json
  "cordova-plugin-googlemaps-sdk": "github:mapsplugin/cordova-plugin-googlemaps-sdk",
```

If you used the the old repository, you have to remove it also:

```bash
cordova plugin remove cordova-plugin-googlemaps
```

After that, you can add this plugin.

You can see a changelog of all Google Maps SDK versions here:

https://developers.google.com/maps/documentation/ios-sdk/release-notes

#### Problems with Google Maps SDK

##### EXC_BAD_ACCESS (KERN_INVALID_ADDRESS) gmscore::vector::TextureAtlasElement::height() const
Since Google Maps SDK 7.4.0 an `EXC_BAD_ACCESS` can occur when using some time the map. This is a known bug and currently not fixed:
https://issuetracker.google.com/issues/338162114
This happens only on a simulator. The issue does say that the problem also occurs on a simulator, but after testing on a real device with iOS 18, this could not be confirmed. Otherwise this will happen on every iOS version on a simulator. As long the issue is not resolved, the OpenGL rednerer will be used for a smimulator instead of the Metal renderer.

##### EXC_BAD_ACCESS in glvmRasterOpDepthStencilTest (gmscore::renderer::GLEntity::Draw)
Happens only on a simulator with iOS 15 since Google Maps SDK 6.0.0 when using OpenGL:
https://issuetracker.google.com/issues/224584852

## Install optional variables (config.xml)

### Android
- `GOOGLE_MAPS_PLAY_SERVICES_VERSION`
  - Defaults to `19.0.0`
- `GOOGLE_MAPS_PLAY_SERVICES_LOCATION_VERSION`
  - Defaults to `21.3.0`

### iOS
- `LOCATION_WHEN_IN_USE_DESCRIPTION`
  - This message is displayed when your application requests **LOCATION PERMISSION for only necessary times**.
- `LOCATION_ALWAYS_USAGE_DESCRIPTION`
  - This message is displayed when your application requests **LOCATION PERMISSION for always**.

---------------------------------------------------------------------------------------------------------

## Release Notes
See [CHANGELOG.md](CHANGELOG.md)

## Demos
You can see a demo in your browser:

https://mapsplugin.github.io/HelloGoogleMap

![](https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/raw/master/v1.4.0/top/demo.gif)

## Documentation

You can find the documentation in its own repository:

[https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc](https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/blob/master/v2.6.0/README.md)

It's sourced out, so this repository gets not too big and takes less space when added to a project.

**Quick examples**
<table>
<tr>
  <td><a href="https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/blob/master/v2.6.0/class/Map/README.md"><img src="https://github.com/GitToTheHub/cordova-plugin-googlemaps-2/raw/master/images/map.png?raw=true"><br>Map</a></td>
  <td><pre>
var options = {
  camera: {
    target: {lat: ..., lng: ...},
    zoom: 19
  }
};
var map = plugin.google.maps.Map.getMap(mapDiv, options)</pre></td>
</tr>
<tr>
  <td><a href="https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/blob/master/v2.6.0/class/Marker/README.md"><img src="https://github.com/GitToTheHub/cordova-plugin-googlemaps-2/blob/master/images/marker.png?raw=true"><br>Marker</a></td>
  <td><pre>
var marker = map.addMarker({
  position: {lat: ..., lng: ...},
  title: "Hello Cordova Google Maps for iOS and Android",
  snippet: "This plugin is awesome!"
})</pre></td>
</tr>
<tr>
  <td><a href="https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/blob/master/v2.6.0/class/MarkerCluster/README.md"><img src="https://github.com/GitToTheHub/cordova-plugin-googlemaps-2/blob/master/images/markercluster.png?raw=true"><br>MarkerCluster</a></td>
  <td><pre>
var markerCluster = map.addMarkerCluster({
  //maxZoomLevel: 5,
  boundsDraw: true,
  markers: dummyData(),
  icons: [
      {min: 2, max: 100, url: "./img/blue.png", anchor: {x: 16, y: 16}},
      {min: 100, max: 1000, url: "./img/yellow.png", anchor: {x: 16, y: 16}},
      {min: 1000, max: 2000, url: "./img/purple.png", anchor: {x: 24, y: 24}},
      {min: 2000, url: "./img/red.png",anchor: {x: 32,y: 32}}
  ]
});</pre></td>
</tr>
<tr>
  <td><a href="https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/blob/master/v2.6.0/class/HtmlInfoWindow/README.md"><img src="https://github.com/GitToTheHub/cordova-plugin-googlemaps-2/blob/master/images/htmlInfoWindow.png?raw=true"><br>HtmlInfoWindow</a></td>
  <td><pre>
var html = "&lt;img src='./House-icon.png' width='64' height='64' &gt;" +
           "&lt;br&gt;" +
           "This is an example";
htmlInfoWindow.setContent(html);
htmlInfoWindow.open(marker);
</pre></td>
</tr>
<tr>
  <td><a href="https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/blob/master/v2.6.0/class/Circle/README.md"><img src="https://github.com/GitToTheHub/cordova-plugin-googlemaps-2/blob/master/images/circle.png?raw=true"><br>Circle</a></td>
  <td><pre>
var circle = map.addCircle({
  'center': {lat: ..., lng: ...},
  'radius': 300,
  'strokeColor' : '#AA00FF',
  'strokeWidth': 5,
  'fillColor' : '#880000'
});</pre></td>
</tr>
<tr>
  <td><a href="https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/blob/master/v2.6.0/class/Polyline/README.md"><img src="https://github.com/GitToTheHub/cordova-plugin-googlemaps-2/blob/master/images/polyline.png?raw=true"><br>Polyline</a></td>
  <td><pre>
var polyline = map.addPolyline({
  points: AIR_PORTS,
  'color' : '#AA00FF',
  'width': 10,
  'geodesic': true
});</pre></td>
</tr>
<tr>
  <td><a href="https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/blob/master/v2.6.0/class/Polygon/README.md"><img src="https://github.com/GitToTheHub/cordova-plugin-googlemaps-2/blob/master/images/polygon.png?raw=true"><br>Polygon</a></td>
  <td><pre>
var polygon = map.addPolygon({
  'points': GORYOKAKU_POINTS,
  'strokeColor' : '#AA00FF',
  'strokeWidth': 5,
  'fillColor' : '#880000'
});</pre></td>
</tr>
<tr>
  <td><a href="https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/blob/master/v2.6.0/class/GroundOverlay/README.md"><img src="https://github.com/GitToTheHub/cordova-plugin-googlemaps-2/blob/master/images/groundoverlay.png?raw=true"><br>GroundOverlay</a></td>
  <td><pre>
var groundOverlay = map.addGroundOverlay({
  'url': "./newark_nj_1922.jpg",
  'bounds': [
    {"lat": 40.712216, "lng": -74.22655},
    {"lat": 40.773941, "lng": -74.12544}
  ],
  'opacity': 0.5
});
</pre></td>
</tr>
<tr>
  <td><a href="https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/blob/master/v2.6.0/class/TileOverlay/README.md"><img src="https://github.com/GitToTheHub/cordova-plugin-googlemaps-2/blob/master/images/tileoverlay.png?raw=true"><br>TileOverlay</a></td>
  <td><pre>
var tileOverlay = map.addTileOverlay({
  debug: true,
  opacity: 0.75,
  getTile: function(x, y, zoom) {
    return "../images/map-for-free/" + zoom + "_" + x + "-" + y + ".gif"
  }
});</pre></td>
</tr>
<tr>
  <td><a href="https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/blob/master/v2.6.0/class/KmlOverlay/README.md"><img src="https://github.com/GitToTheHub/cordova-plugin-googlemaps-2/blob/multiple_maps/images/kmloverlay.png?raw=true"><br>KmlOverlay</a></td>
  <td><pre>
map.addKmlOverlay({
  'url': 'polygon.kml'
}, function(kmlOverlay) { ... });</pre></td>
</tr>
<tr>
  <td><a href="https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/blob/master/v2.6.0/class/Geocoder/README.md"><img src="https://github.com/GitToTheHub/cordova-plugin-googlemaps-2/blob/master/images/geocoder.png?raw=true"><br>Geocoder</a></td>
  <td><pre>
plugin.google.maps.Geocoder.geocode({
  // US Capital cities
  "address": [
    "Montgomery, AL, USA", ... "Cheyenne, Wyoming, USA"
  ]
}, function(mvcArray) { ... });</pre></td>
</tr>
<tr>
  <td><a href="https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/blob/master/v2.6.0/class/utilities/geometry/poly/README.md"><img src="https://github.com/GitToTheHub/cordova-plugin-googlemaps-2/blob/master/images/poly.png?raw=true"><br>poly utility</a></td>
  <td><pre>
var GORYOKAKU_POINTS = [
  {lat: 41.79883, lng: 140.75675},
  ...
  {lat: 41.79883, lng: 140.75673}
]
var contain = plugin.google.maps.geometry.poly.containsLocation(
                    position, GORYOKAKU_POINTS);
marker.setIcon(contain ? "blue" : "red");
</pre></td>
</tr>
<tr>
  <td><a href="https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/tree/master/v2.6.0/class/utilities/geometry/encoding/README.md"><img src="https://github.com/GitToTheHub/cordova-plugin-googlemaps-2/blob/master/images/encode.png?raw=true"><br>encode utility</a></td>
  <td><pre>
var GORYOKAKU_POINTS = [
  {lat: 41.79883, lng: 140.75675},
  ...
  {lat: 41.79883, lng: 140.75673}
]
var encodedPath = plugin.google.maps.geometry.
                       encoding.encodePath(GORYOKAKU_POINTS);
</pre></td>
</tr>
<tr>
  <td><a href="https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/blob/master/v2.6.0/class/utilities/geometry/spherical/README.md"><img src="https://github.com/GitToTheHub/cordova-plugin-googlemaps-2/blob/master/images/spherical.png?raw=true"><br>spherical utility</a></td>
  <td><pre>
var heading = plugin.google.maps.geometry.spherical.computeHeading(
                        markerA.getPosition(), markerB.getPosition());
label.innerText = "heading : " + heading.toFixed(0) + "&deg;";
</pre></td>
</tr>
<tr>
  <td><a href="https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/blob/master/v2.6.0/class/locationservice/README.md"><img src="https://github.com/GitToTheHub/cordova-plugin-googlemaps-2/blob/master/images/locationService.png?raw=true"><br>Location service</a></td>
  <td><pre>
plugin.google.maps.LocationService.getMyLocation(function(result) {
  alert(["Your current location:\n",
      "latitude:" + location.latLng.lat.toFixed(3),
      "longitude:" + location.latLng.lng.toFixed(3),
      "speed:" + location.speed,
      "time:" + location.time,
      "bearing:" + location.bearing].join("\n"));
});
</pre></td>
</tr>

<tr>
  <td><a href="https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/blob/master/v2.6.0/class/StreetView/README.md"><img src="https://github.com/GitToTheHub/cordova-plugin-googlemaps-2/raw/master/images/streetview.png?raw=true"><br>StreetView</a></td>
  <td><pre>
var div = document.getElementById("pano_canvas1");
var panorama = plugin.google.maps.StreetView.getPanorama(div, {
  camera: {
    target: {lat: 42.345573, lng: -71.098326}
  }
});</pre></td>
</tr>
</table>


---------------------------------------------------------------------------------------------------------

### What is the difference between this plugin and Google Maps JavaScript API v3?

Google Maps JavaScript API v3 works on any platforms,
but it does not work if device is **offline**.

This plugin uses three different APIs:
- Android : [Google Maps Android API](https://developers.google.com/maps/documentation/android/)
- iOS : [Google Maps SDK for iOS](https://developers.google.com/maps/documentation/ios/)
- Browser : [Google Maps JavaScript API v3](https://developers.google.com/maps/documentation/javascript/)

In Android and iOS applications, this plugin displays native Google Maps views, which is **faster** than Google Maps JavaScript API v3.
And it even works if the device is **offline**.

In Browser platform, this plugin displays JS map views (Google Maps JavaScript API v3).
It should work as PWA (progressive web application), but the device has to be **online**.

In order to work for all platforms, this plugin provides **own API** instead of each original APIs.
You can write your code `similar to` the Google Maps JavaScript API v3.

**Feature comparison table**

|                | Google Maps JavaScript API v3     | Cordova-Plugin-GoogleMaps(Android,iOS)| Cordova-Plugin-GoogleMaps(Browser)    |
|----------------|-----------------------------------|---------------------------------------|---------------------------------------|
|Rendering system| JavaScript + HTML                 | JavaScript + Native API's             | JavaScript                            |
|Offline map     | Not possible                      | Possible (only your displayed area)   | Not possible                          |
|3D View         | Not possible                      | Possible                              | Not possible                          |
|Platform        | All browsers                      | Android and iOS applications only     | All browsers                          |
|Tile image      | Bitmap                            | Vector                                | Bitmap                                |

**Class comparison table**

| Google Maps JavaScript API v3     | Cordova-Plugin-GoogleMaps             |
|-----------------------------------|---------------------------------------|
| google.maps.Map                   | Map                                   |
| google.maps.Marker                | Marker                                |
| google.maps.InfoWindow            | Default InfoWindow, and HtmlInfoWindow|
| google.maps.Circle                | Circle                                |
| google.maps.Rectangle             | Polygon                               |
| google.maps.Polyline              | Polyline                              |
| google.maps.Polygon               | Polygon                               |
| google.maps.GroundOverlay         | GroundOverlay                         |
| google.maps.ImageMapType          | TileOverlay                           |
| google.maps.MVCObject             | BaseClass                             |
| google.maps.MVCArray              | BaseArrayClass                        |
| google.maps.Geocoder              | plugin.google.maps.geocoder           |
| google.maps.geometry.spherical    | plugin.google.maps.geometry.spherical |
| google.maps.geometry.encoding     | plugin.google.maps.geometry.encoding  |
| google.maps.geometry.poly         | plugin.google.maps.geometry.poly      |
| (not available)                   | MarkerCluster                         |
| google.maps.KmlLayer              | KmlOverlay                            |
| (not available)                   | LocationService                       |
| google.maps.StreetView            | StreetView :sparkles:                 |
| google.maps.Data                  | (not available)                       |
| google.maps.DirectionsService     | (not available)                       |
| google.maps.DistanceMatrixService | (not available)                       |
| google.maps.TransitLayer          | (not available)                       |
| google.maps.places.*              | (not available)                       |
| google.maps.visualization.*       | (not available)                       |

### How does this plugin work (Android, iOS)?

This plugin generates native map views, and puts them **under the browser**.

The map views are not HTML elements. This means that they are not a `<div>` or anything HTML related.
But you can specify the size and position of the map view using its containing `<div>`.

This plugin changes the background to `transparent` in your application.
Then the plugin detects your touch position, which is either meant for the `native map` or an `html element`
(which can be on top of your map, or anywhere else on the screen).

![](https://github.com/GitToTheHub/cordova-plugin-googlemaps-doc/raw/master/v1.4.0/class/Map/mechanism.png)

The benefit of this plugin is the ability to automatically detect which HTML elements are over the map or not.

For instance, in the image below, say you tap on the header div (which is over the map view).
The plugin will detect whether your tap is for the header div or for the map view and then pass the touch event appropriately.

This means **you can use the native Google Maps views similar to HTML elements**.

![](https://raw.githubusercontent.com/GitToTheHub/cordova-plugin-googlemaps-2/master/images/touch.png)
