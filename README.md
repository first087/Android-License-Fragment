[![Build Status](https://travis-ci.org/first087/Android-License-Fragment.svg?branch=master)](https://travis-ci.org/first087/Android-License-Fragment)
[![Download](https://api.bintray.com/packages/first087/maven/Android-License-Fragment/images/download.svg) ](https://bintray.com/first087/maven/Android-License-Fragment/_latestVersion)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.artit-k/license-fragment/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.artit-k/license-fragment)

# License Fragment 
**License Fragment Library for Android**

This library for easy create fragment for open-source licenses UI.

#### License Fragment Goals
* **1 line** create **Fragment** for **open-source licenses** UI.
* Support ```<fragment>```
* Auto generate license chain by default. (Ex. If you add Otto library. It will automatic add OkHttp library.)
* 3 Simple UI (```ScrollViewLicenseFragment```, ```ListViewLicenseFragment```, ```RecyclerViewLicenseFragment```)
* Add other licenses
* Customize UI

#### Support license type (Build-in)
* Apache License 2.0
* BSD 3-Clause
* BSD 2-Clause
* GPL-3.0
* MIT license

*Reference - http://opensource.org/licenses*

#### Support license for Library (Build-in)
* [License Fragment](https://github.com/first087/Android-License-Fragment)
* [Gson](https://github.com/google/gson)
* [Otto](http://square.github.io/otto/)
* [OkHttp](http://square.github.io/okhttp/)
* [Retrofit](http://square.github.io/retrofit/)
* [Picasso](http://square.github.io/picasso/)
* [StatedFragment](https://github.com/nuuneoi/StatedFragment)

#### How to use

#####1. Installation

To use this library in your android project, just simply add the following dependency into your **build.gradle**.

This library extends fragment from Support Library v4.

* Gradle
```groovy
dependencies {
    compile 'com.artit-k:license-fragment:1.0.0'
}
```

* Maven
```xml
<dependency>
        <groupId>com.artit-k</groupId>
        <artifactId>license-fragment</artifactId>
        <version>1.0.0</version>
</dependency>
```

#####2. Create License Fragment

######2.1 Create by java code

*Example data.*
```java
ArrayList<Integer> licenseIds = new ArrayList<>();
licenseIds.add(LicenseID.GSON);                             // Add License ID from LicenseID class
licenseIds.add(LicenseID.RETROFIT);                         // Add License ID from LicenseID class
```

* **Create License Fragment.**
```java
// Ex1 - Call newInstance() using ArrayList<Integer>
Fragment fragment = ScrollViewLicenseFragment.newInstance(licenseIds);

// Ex2 - Call newInstance() using array of int
Fragment fragment = ListViewLicenseFragment.newInstance(new int[] { LicenseID.PICASSO });

// Ex3 - Call newInstance() using without parameter
Fragment fragment = RecyclerViewLicenseFragment.newInstance();
```

######2.2 Create by xml layout

* **Define _whatever_ namespace on root view in your layout.**

```xml
<YOUR_ROOT_VIEW
    ...
    xmlns:whatever="http://schemas.android.com/apk/res-auto"
    ...>
```

* **Add ```fragment``` tag with attibute ```whatever:lfLicenseID```**.
```xml
    <!-- Ex1 - fragment tag with attribute whatever:lfLicenseID -->
    <fragment
        android:id="@+id/sv_license_fragment"
        android:name="com.artitk.licensefragment.ScrollViewLicenseFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        whatever:lfLicenseID="GSON|RETROFIT" />

    <!-- Ex2 - fragment tag with attribute whatever:lfLicenseID -->
    <fragment
        android:id="@+id/lv_license_fragment"
        android:name="com.artitk.licensefragment.ListViewLicenseFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        whatever:lfLicenseID="PICASSO"
        tools:layout="@layout/layout_item_license" />

    <!-- Ex3 - fragment tag without attribute whatever:lfLicenseID -->
    <fragment
        android:id="@+id/rv_license_fragment"
        android:name="com.artitk.licensefragment.RecyclerViewLicenseFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:layout="@layout/layout_item_license" />
```

* **_Optional_** - Turn on/off License Chain by attibute ```whatever:lfLicenseChain```.
```xml
    whatever:lfLicenseChain="false"                         <!-- Disable license chain (Default : Enable) -->
```

* **_Optional_** - Customize UI by attibutes ```whatever:lfTitleBackgroundColor```, ```whatever:lfTitleTextColor```, ```whatever:lfLicenseBackgroundColor```, ```whatever:lfLicenseTextColor```.
```xml
    whatever:lfTitleBackgroundColor="@color/title_bg_color"
    whatever:lfTitleTextColor="@color/title_text_color"
    whatever:lfLicenseBackgroundColor="@color/license_bg_color"
    whatever:lfLicenseTextColor="@color/license_text_color"
```

#####3. *(Optional)* Customize after create License Fragment
* Turn on/off License Chain feature.
```java
fragment.withLicenseChain(false);                           // Disable license chain (Default : Enable)
```

* Add More Licenses and Custom licenses.
```java
ArrayList<License> customLicenses = new ArrayList<>();
customLicenses.add(new License(this, "Test Library 1", LicenseType.MIT_LICENSE, "2000-2001", "Test Owner 1"));
customLicenses.add(new License(this, "Test Library 2", LicenseType.GPL_30,      "2002",      "Test Owner 2"));

fragment.addLicense(new int[] { LicenseID.PICASSO })        // Add More Licenses by array of int
fragment.addLicense(licenseIds)                             // Add More Licenses by ArrayList<Integer>
fragment.addCustomLicense(customLicenses);                  // Add Custom Licenses by ArrayList<License>
```

* Customize UI.
```java
CustomUI customUI = new CustomUI()                          // Create Customize UI from CustomUI class
            .setTitleBackgroundColor(Color.parseColor("#7fff7f"))
            .setTitleTextColor(getResources().getColor(android.R.color.holo_green_dark))
            .setLicenseBackgroundColor(Color.rgb(127, 223, 127))
            .setLicenseTextColor(Color.DKGRAY);

fragment.setCustomUI(customUI);                             // Set Customize UI
```

#### Screenshot Example
##### ScrollViewLicenseFragment
![ScrollViewLicenseFragment](https://github.com/first087/Android-License-Fragment/blob/master/screen/Demo-ScrollViewLicenseFragment.png)

##### ListViewLicenseFragment
![ListViewLicenseFragment](https://github.com/first087/Android-License-Fragment/blob/master/screen/Demo-ListViewLicenseFragment.png)

##### RecyclerViewLicenseFragment
![RecyclerViewLicenseFragment](https://github.com/first087/Android-License-Fragment/blob/master/screen/Demo-RecyclerViewLicenseFragment.png)

#### License
```
Copyright 2015 Artit Kiuwilai

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## Welcome to Fork.
