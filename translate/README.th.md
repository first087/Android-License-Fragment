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

Library นี้ ช่วยสร้าง Fragment สำหรับหน้าจอแสดงผลข้อมูล Open-source licenses ได้แบบง่าย ๆ

และ Library นี้ พัฒนาต่อยอดจาก `android.app.Fragment` และ `android.support.v4.app.Fragment` ใน **[Support Library v4](http://developer.android.com/tools/support-library/features.html#v4)** และใช้ **[RecyclerView Library v7](http://developer.android.com/tools/support-library/features.html#v7-recyclerview)** ในส่วนของคลาส `RecyclerViewLicenseFragment`

#### License Fragment Goals
* สร้าง **Fragment** สำหรับแสดงข้อมูล **Open-source licenses** ได้ง่าย ๆ ด้วยชุดคำสั่งเพียง **1 บรรทัด**
* รองรับการใช้งานด้วย Tag `<fragment>`
* แสดงข้อมูล License ของ Library ที่เกี่ยวข้องให้โดยอัตโนมัติ สามารถเปิด/ปิดความสามารถนี้ได้ (ตัวอย่าง – ถ้ากำหนดให้แสดงข้อมูลจาก Library Otto ก็จะแสดงข้อมูล Library OkHttp ให้อัตโนมัติ)
* มีหน้าจอพื้นฐานให้เลือก 3 ประเภท (`ScrollViewLicenseFragment`, `ListViewLicenseFragment` และ `RecyclerViewLicenseFragment`)
* สามารถเพิ่มข้อมูล License เองได้
* ปรับแต่งหน้าตาของหน้าจอได้

#### Build-in license type
* Apache License 2.0
* BSD 3-Clause
* BSD 2-Clause
* GPL-3.0
* MIT license

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
* **[master](https://github.com/first087/Android-License-Fragment)** branch สำหรับ `android.app.Fragment`
* **[support-v4](https://github.com/first087/Android-License-Fragment/tree/support-v4)** branch สำหรับ `android.support.v4.app.Fragment`

#### How to use

#####1. Installation

การใช้งาน Library ใน Android Project สามารถเพิ่ม Dependency ใน **build.gradle** ได้ ดังนี้

* Gradle

สำหรับ `android.app.Fragment`
```groovy
dependencies {
    compile 'com.artit-k:license-fragment:1.1.0'
}
```
หรือ สำหรับ `android.support.v4.app.Fragment`
```groovy
dependencies {
    compile 'com.artit-k:license-fragment-support-v4:1.1.0'
}
```

* Maven

สำหรับ `android.app.Fragment`
```xml
<dependency>
        <groupId>com.artit-k</groupId>
        <artifactId>license-fragment</artifactId>
        <version>1.1.0</version>
</dependency>
```
หรือ สำหรับ `android.support.v4.app.Fragment`
```xml
<dependency>
        <groupId>com.artit-k</groupId>
        <artifactId>license-fragment-support-v4</artifactId>
        <version>1.1.0</version>
</dependency>
```

#####2. Create License Fragment

######2.1 สร้าง License Fragment จาก java code

*ตัวอย่างข้อมูล*
```java
ArrayList<Integer> licenseIds = new ArrayList<>();
licenseIds.add(LicenseID.GSON);                             // Add License ID from LicenseID class
licenseIds.add(LicenseID.RETROFIT);                         // Add License ID from LicenseID class
```

* **สร้าง License Fragment**
```java
// Ex1 - Call newInstance() using ArrayList<Integer>
Fragment fragment = ScrollViewLicenseFragment.newInstance(licenseIds);

// Ex2 - Call newInstance() using array of int
Fragment fragment = ListViewLicenseFragment.newInstance(new int[] { LicenseID.PICASSO });

// Ex3 - Call newInstance() using without parameter
Fragment fragment = RecyclerViewLicenseFragment.newInstance();
```

######2.2 สร้าง License Fragment จาก xml layout

* **ประกาศ Namespace `whatever` (อะไรก็ได้) ใน root view ของ Layout**

```xml
<YOUR_ROOT_VIEW
    ...
    xmlns:whatever="http://schemas.android.com/apk/res-auto"
    ...>
```

* **เพิ่ม Tag `fragment` และกำหนดข้อมูล License ด้วยการกำหนด Attribute `whatever:lfLicenseID`**

*แทนที่ `{PACKAGE_NAME}` ด้วย...*

`com.artitk.licensefragment` สำหรับ `android.app.Fragment`

`com.artitk.licensefragment.support.v4` สำหรับ `android.support.v4.app.Fragment`
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

* **_Optional_** - เปิด/ปิด ความสามารถการแสดงผล Library ที่เกี่ยวข้อง โดยกำหนด Attribute `whatever:lfLicenseChain`.
```xml
    whatever:lfLicenseChain="false"                         <!-- Disable license chain (Default : Enable) -->
```

* **_Optional_** - ปรับแต่งหน้าตาของหน้าจอ โดยกำหนด Attribute `whatever:lfTitleBackgroundColor`, `whatever:lfTitleTextColor`, `whatever:lfLicenseBackgroundColor` และ `whatever:lfLicenseTextColor`.
```xml
    whatever:lfTitleBackgroundColor="@color/title_bg_color"
    whatever:lfTitleTextColor="@color/title_text_color"
    whatever:lfLicenseBackgroundColor="@color/license_bg_color"
    whatever:lfLicenseTextColor="@color/license_text_color"
```

#####3. *(Optional)* Customize after create License Fragment

* เปิด/ปิด ความสามารถการแสดงผล Library ที่เกี่ยวข้อง
```java
fragment.withLicenseChain(false);                           // Disable license chain (Default : Enable)
```

* เพิ่มข้อมูล License อื่น ๆ
```java
ArrayList<License> customLicenses = new ArrayList<>();
customLicenses.add(new License(this, "Test Library 1", LicenseType.MIT_LICENSE, "2000-2001", "Test Owner 1"));
customLicenses.add(new License(this, "Test Library 2", LicenseType.GPL_30,      "2002",      "Test Owner 2"));

fragment.addLicense(new int[] { LicenseID.PICASSO })        // Add More Licenses by array of int
fragment.addLicense(licenseIds)                             // Add More Licenses by ArrayList<Integer>
fragment.addCustomLicense(customLicenses);                  // Add Custom Licenses by ArrayList<License>
```

* ปรับแต่งหน้าตาของหน้าจอ
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

## Welcome to Fork.

สำหรับนักพัฒนาที่อยากร่วมพัฒนา ตรวจสอบได้จาก `TODO`

พัฒนาบน **[master](https://github.com/first087/Android-License-Fragment)** branch ก่อน แล้ว merge ไปยัง **[support-v4](https://github.com/first087/Android-License-Fragment/tree/support-v4)** branch.
