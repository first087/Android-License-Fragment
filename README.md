[ ![Download](https://api.bintray.com/packages/first087/maven/Android-License-Fragment/images/download.svg) ](https://bintray.com/first087/maven/Android-License-Fragment/_latestVersion)
[![Build Status](https://travis-ci.org/first087/Android-License-Fragment.svg?branch=master)](https://travis-ci.org/first087/Android-License-Fragment)

# License Fragment 
**License Fragment Library for Android**

This library for easy create fragment for open-source licenses UI.

#### License Fragment Goals
* **1 line** create **Fragment** for **open-source licenses** UI.
* Support ```<fragment>```
* Auto generate license chain by default. (Ex. If you add Otto library. It will automatic add OkHttp library.)
* 3 Simple UI (```ScrollViewLicenseFragment```, ```ListViewLicenseFragment```, ```RecyclerViewLicenseFragment```)
* Add other licenses
* Customize UI *(Soon)*

#### Support license type (Build-in)
* Apache License 2.0
* BSD 3-Clause
* BSD 2-Clause
* GPL-3.0
* MIT license

*Reference - http://opensource.org/licenses*

#### Support license for Library (Build-in)
* [License Fragment](https://github.com/first087/Android-License-Fragment)
* [StatedFragment](https://github.com/nuuneoi/StatedFragment)
* [Gson](https://github.com/google/gson)
* [Otto](http://square.github.io/otto/)
* [OkHttp](http://square.github.io/okhttp/)
* [Retrofit](http://square.github.io/retrofit/)
* [Picasso](http://square.github.io/picasso/)

#### Gradle
```groovy
dependencies {
    compile 'com.artit-k:license-fragment:0.9.5'
}
```

#### Maven
```xml
<dependency>
        <groupId>com.artit-k</groupId>
        <artifactId>license-fragment</artifactId>
        <version>0.9.5</version>
</dependency>
```

#### Code Example
```java
// Example input.
ArrayList<Integer> licenseIds = new ArrayList<>();
licenseIds.add(LicenseID.GSON);
licenseIds.add(LicenseID.RETROFIT);

// Example for create fragment by 1 line.
// Ex1 - Call newInstance() using parameter ArrayList<Integer>
Fragment fragment = ScrollViewLicenseFragment.newInstance(licenseIds);

// Ex2 - Call newInstance() using parameter array + (Optional) Disable license chain
Fragment fragment = ListViewLicenseFragment.newInstance(new int[] { LicenseID.PICASSO }).withLicenseChain(false);

// Ex3 - Call newInstance() using without parameter + (Optional) Enable license chain (default)
Fragment fragment = RecyclerViewLicenseFragment.newInstance().withLicenseChain(true);

// Optional : Add more License and Custom licenses.
ArrayList<License> licenses = new ArrayList<>();
licenses.add(new License(this, "Test Library 1", LicenseType.MIT_LICENSE, "2000-2001", "Test Owner 1"));
licenses.add(new License(this, "Test Library 2", LicenseType.GPL_30, "2002", "Test Owner 2"));
Fragment fragment = RecyclerViewLicenseFragment.newInstance()
            .addLicense(new int[] { LicenseID.PICASSO })    // Add array (same call newInstance)
            .addLicense(licenseIds)                         // Add ArrayList<Integer> (same call newInstance)
            .addCustomLicense(licenses);                    // Add Custom License
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
