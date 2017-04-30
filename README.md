# BikeRun2
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

BikeRun2 is application for runners and cyclists. Optionally integrate with Strava. 

My goals:
- application should be esay to use by user
- application should be easy to modify and adding new features by developer
- user shoud have possibility to modify sport layout (9 indicators showing distance in one view? No problem!)
- user shoud have possibility to modify settings of preparing data (Min gps accurancy = 1m? No problem!)
- user shoud have possibility to modify format of displayed data (displaying data from ANT+ sensors more than 1 per secons? No problem!) 
# Usage (for developers)
If you want build this app you have to add keys:
- strava key in [api_keys.xml](../master/app/src/main/res/values/api_keys.xml) file

```
<string name="strava_secret">put_your_key_here</string>
<integer name="strava_app_id">0000</integer> 
```
- google maps key in [google_maps_api.xml](../master/app/src/main/res/values/google_maps_api.xml) file
```
<resources>
    <string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">
        add_here_key
    </string>
</resources>
```
# How it works?
Idea is simple. User clicks in sport activity button (running, cycling or cycling on stationary bike) in menu and automaticly background service is created and starts emmiting data (like time from GPS, actual speed and more) by RxJava to view( DataFragments) and "Training" object.

Training object have several tasks: 
- log training to database
- prepare some data (like average speed or distance of trip)
- send preapred data to view

Fragments are divided into 2 groups: 
- contaiers
- dataFragment

Containers only contains DataFragment so we can easily exchange them.

DataFragments connect with background service by Binder and subscribe data by RX. Every ButtonFragment, DataFragment or MapFragment is a separate fragment so they are independent of the container.

# Technologies used
- Retrofit
- Dagger2
- RxJava2
- Butterknife
- Realm
- Retrolambda, Streams (Android and Java8 :angry:)
-	No libs hardly based on ~~reflection~~ like EventBus!
- and more...

# Done
- skeleton of app
- base functionalities
- dependency injection
- logging user profile and sport activity to DB
- integration with Strava
# Todo
- ANT+ support
- finish Strava integration (segments, sending result)
- gpx support
- release in Google Play
