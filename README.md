[ ![Download](https://api.bintray.com/packages/first087/maven/Android-License-Fragment/images/download.svg) ](https://bintray.com/first087/maven/Android-License-Fragment/_latestVersion)

# License Fragment 
**License Fragment Library for Android**

This library for easy create fragment for open-source licenses UI.

#### License Fragment Goals
* **1 line** create **Fragment** for **open-source licenses** UI.
* Auto generate license chain by default. (Ex. If you add Otto library. It will automatic add OkHttp library.)
* 3 Simple UI (**ScrollViewLicenseFragment**, **ListViewLicenseFragment**, **RecyclerViewLicenseFragment**)
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
```
dependencies {
    compile 'com.ethanf:license-fragment:0.9.1'
}
```

#### Code Example
```java
// Create fragment by 1 line.
Fragment fragment = ScrollViewLicenseFragment.newInstance(new int[] { LicenseID.GSON, LicenseID.RETROFIT });
Fragment fragment = ListViewLicenseFragment.newInstance(new int[] { LicenseID.RETROFIT }).withLicenseChain(false);
Fragment fragment = RecyclerViewLicenseFragment.newInstance(null).withLicenseChain(true);

// Manual add other licenses.
ArrayList<License> licenses = new ArrayList<>();
licenses.add(new License(this, "Test Library 1", LicenseType.MIT_LICENSE, "2001", "Test Owner 1"));
licenses.add(new License(this, "Test Library 2", LicenseType.GPL_30, "2002", "Test Owner 2"));
Fragment fragment = RecyclerViewLicenseFragment.newInstance(null).addLicense(licenses);
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

## Welcome to Fork. *I need you.*
