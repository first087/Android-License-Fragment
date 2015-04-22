# License Fragment
License Fragment for Android

#### License Fragment Goals
* Easy create open-source licenses UI with Fragment on **1 line**.
* Auto generate license chain by default. (Ex. If you add Otto library. It will automatic add OkHttp library.)
* 3 Simples UI (**ScrollView**, **ListView**, **RecyclerView**)
* Add other license *(Soon)*
* Customize UI *(Soon)*

#### Code Example
```java
Fragment fragment = ScrollViewLicenseFragment.newInstance(new int[] { LicenseID.GSON, LicenseID.RETROFIT });
Fragment fragment = ListViewLicenseFragment.newInstance(new int[] { LicenseID.RETROFIT }).withLicenseChain(false);
Fragment fragment = RecyclerViewLicenseFragment.newInstance(null).withLicenseChain(true);
```

#### Support license for Library
* [License Fragment](https://github.com/first087/Android-License-Fragment)
* [StatedFragment](https://github.com/nuuneoi/StatedFragment)
* [Gson](https://github.com/google/gson)
* [Otto](http://square.github.io/otto/)
* [OkHttp](http://square.github.io/okhttp/)
* [Retrofit](http://square.github.io/retrofit/)
* [Picasso](http://square.github.io/picasso/)

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
