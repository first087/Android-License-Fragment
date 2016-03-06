**[master](https://github.com/first087/Android-License-Fragment)** - [![Build Status](https://travis-ci.org/first087/Android-License-Fragment.svg?branch=master)](https://travis-ci.org/first087/Android-License-Fragment)
[![Download](https://api.bintray.com/packages/first087/maven/Android-License-Fragment/images/download.svg)](https://bintray.com/first087/maven/Android-License-Fragment/_latestVersion)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.artit-k/license-fragment/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.artit-k/license-fragment)

**[support-v4](https://github.com/first087/Android-License-Fragment/tree/support-v4)** - [![Build Status](https://travis-ci.org/first087/Android-License-Fragment.svg?branch=support-v4)](https://travis-ci.org/first087/Android-License-Fragment)
[![Download](https://api.bintray.com/packages/first087/maven/Android-License-Fragment/images/download.svg)](https://bintray.com/first087/maven/Android-License-Fragment/_latestVersion)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.artit-k/license-fragment-support-v4/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.artit-k/license-fragment-support-v4)

[![English](https://github.com/first087/Android-License-Fragment/blob/master/images/flag/United-States-of-Americ-icon.png)](https://github.com/first087/Android-License-Fragment/blob/master/README.md)
[![Thailand](https://github.com/first087/Android-License-Fragment/blob/master/images/flag/Thailand-Flag-icon.png)](https://github.com/first087/Android-License-Fragment/blob/master/translate/README.th.md)

# License Fragment
**License Fragment Library for Android**

This library for easy create fragment for display open-source licenses.

And library depend on fragment from `android.app.Fragment` and `android.support.v4.app.Fragment` (**[Support Library v4](http://developer.android.com/tools/support-library/features.html#v4)**) and use **[RecyclerView Library v7](http://developer.android.com/tools/support-library/features.html#v7-recyclerview)** on `RecyclerViewLicenseFragment` class.

#### License Fragment Goals
* **1 line** create **Fragment** for display **open-source licenses**
* Support `<fragment>` tag
* Auto generate license chain. (Ex - If you add Otto library. It will automatic add OkHttp library.)
* 3 Simple UI (`ScrollViewLicenseFragment`, `ListViewLicenseFragment` and `RecyclerViewLicenseFragment`)
* Add other licenses
* Customize UI

#### Build-in license type
* Apache License 2.0
* BSD 3-Clause
* BSD 2-Clause
* GPL-3.0
* MIT license
* Eclipse Public License 1.0

*Reference - http://opensource.org/licenses*

#### Build-in popular library
* [License Fragment](https://github.com/first087/Android-License-Fragment)
* [Gson](https://github.com/google/gson)
* [Otto](http://square.github.io/otto/)
* [OkHttp](http://square.github.io/okhttp/)
* [Retrofit](http://square.github.io/retrofit/)
* [Picasso](http://square.github.io/picasso/)
* [StatedFragment](https://github.com/nuuneoi/StatedFragment)

#### Branch
* **[master](https://github.com/first087/Android-License-Fragment)** branch for `android.app.Fragment`
* **[support-v4](https://github.com/first087/Android-License-Fragment/tree/support-v4)** branch for `android.support.v4.app.Fragment`

#### How to use

##### 1. Installation

To use this library in your android project, just simply add the following dependency into your **build.gradle**.

* Gradle

For `android.app.Fragment`
```groovy
dependencies {
    compile 'com.artit-k:license-fragment:1.2.0'
}
```
or for `android.support.v4.app.Fragment`
```groovy
dependencies {
    compile 'com.artit-k:license-fragment-support-v4:1.2.0'
}
```

* Maven

For `android.app.Fragment`
```xml
<dependency>
        <groupId>com.artit-k</groupId>
        <artifactId>license-fragment</artifactId>
        <version>1.2.0</version>
</dependency>
```
or for `android.support.v4.app.Fragment`
```xml
<dependency>
        <groupId>com.artit-k</groupId>
        <artifactId>license-fragment-support-v4</artifactId>
        <version>1.2.0</version>
</dependency>
```

##### 2. Create License Fragment

###### 2.1 Create License Fragment by java code

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

###### 2.2 Create License Fragment by xml layout

* **Define `whatever` namespace on root view in your layout.**

```xml
<YOUR_ROOT_VIEW
    ...
    xmlns:whatever="http://schemas.android.com/apk/res-auto"
    ...>
```

* **Add `fragment` tag with attibute `whatever:lfLicenseID`**.

*Replace `{PACKAGE_NAME}` with...*

`com.artitk.licensefragment` for `android.app.Fragment`

`com.artitk.licensefragment.support.v4` for `android.support.v4.app.Fragment`
```xml
    <!-- Ex1 - fragment tag with attribute whatever:lfLicenseID -->
    <fragment
        android:id="@+id/sv_license_fragment"
        android:name="{PACKAGE_NAME}.ScrollViewLicenseFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        whatever:lfLicenseID="GSON|RETROFIT" />

    <!-- Ex2 - fragment tag with attribute whatever:lfLicenseID -->
    <fragment
        android:id="@+id/lv_license_fragment"
        android:name="{PACKAGE_NAME}.ListViewLicenseFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        whatever:lfLicenseID="PICASSO"
        tools:layout="@layout/layout_item_license" />

    <!-- Ex3 - fragment tag without attribute whatever:lfLicenseID -->
    <fragment
        android:id="@+id/rv_license_fragment"
        android:name="{PACKAGE_NAME}.RecyclerViewLicenseFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:layout="@layout/layout_item_license" />
```

* **_Optional_** - Turn on/off License Chain by attibute `whatever:lfLicenseChain`.
```xml
    whatever:lfLicenseChain="false"                         <!-- Disable license chain (Default : Enable) -->
```

* **_Optional_** - Customize UI by attibutes `whatever:lfTitleBackgroundColor`, `whatever:lfTitleTextColor`, `whatever:lfLicenseBackgroundColor` and `whatever:lfLicenseTextColor`.
```xml
    whatever:lfTitleBackgroundColor="@color/title_bg_color"
    whatever:lfTitleTextColor="@color/title_text_color"
    whatever:lfLicenseBackgroundColor="@color/license_bg_color"
    whatever:lfLicenseTextColor="@color/license_text_color"
```

##### 3. *(Optional)* Customize after create License Fragment

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

* Add License with Your own license template.
```java
// Add License Template in /res/raw
ArrayList<License> customLicenses = new ArrayList<>();
licenses.add(new License(this, "Custom License 1", R.raw.wtfpl, "2004", "Test Owner 3"));
licenses.add(new License(this, "Custom License 2", R.raw.x11, "2005", "Test Owner 4"));
```

* License template variable (all are Optional)
```
`%1$s` Year
`%2$s` Owner Name
`%3$s` Project/Library Name
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

#### Screenshot
![LicenseFragment](https://github.com/first087/Android-License-Fragment/blob/master/images/screen/Demo-LicenseFragment.png)

#### Inspiration
##### Google Play Store
![Google Play Store](https://github.com/first087/Android-License-Fragment/blob/master/images/screen/Ex-GooglePlayStore.png)

##### YouTube
![YouTube](https://github.com/first087/Android-License-Fragment/blob/master/images/screen/Ex-YouTube.png)

##### Facebook
![Facebook](https://github.com/first087/Android-License-Fragment/blob/master/images/screen/Ex-Facebook.png)

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

#### Contributors
* [Blazei](https://github.com/Blazei)

## Welcome to Fork.

For contributor, check `TODO` list.

1. Develop on **your branch** start from lasted commit on **[master](https://github.com/first087/Android-License-Fragment)** branch.
2. Create pull request to **[dev](https://github.com/first087/Android-License-Fragment/tree/dev)** branch.
