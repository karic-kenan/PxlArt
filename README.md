![banner](https://user-images.githubusercontent.com/30006970/102007428-51303780-3d29-11eb-862f-fd462015fd66.png)

# PxlArt

 Simple android client 📱 that uses **Pexels API** to fetch curated photos but also provides option to search for them. Idea behind the app is to demonstrate usage of *Modern Android development* tools. Dedicated to all Android Developers with ❤️.
  - Supports dark theme 🌗
  - Supports paralax scrolling ⛓

 ## Install

 Clone the repository and in _gradle_properties_ file add your Pexels API key which you can get [here](https://www.pexels.com/api/).
 
 After you add the key, rebuild the projects so changes can have effect and simply run the app. It's that simple.

 **Note**: Your API key needs to be more secure than this, however this is for learning purposes.

 ## Built with

 - [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
 - [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
 - [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
 - [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and  maintainable apps.
   - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
   - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
   - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
   - [Paging](https://developer.android.com/topic/libraries/architecture/paging/) - Allows you to perfom paging by loading small data chuncks at the time.
 - [Dependency Injection](https://developer.android.com/training/dependency-injection) - 
   - [Koin](https://insert-koin.io/) - A pragmatic lightweight dependency injection framework for Kotlin developers.
 - [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
 - [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
 - [Moshi Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi) - A Converter which uses Moshi for serialization to and from JSON.
 - [Coil-kt](https://coil-kt.github.io/coil/) - An image loading library for Android backed by Kotlin Coroutines.
 - [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.

 # Package Structure

    io.aethibo.pxlart           # Root Package
    .
    ├── core                   # Core functionality of the application
    │   ├── data               # For data handling
    |   │   ├── remote         # Remote data handlers
    │   │   └── repositories   # Single source of data
    │   ├── di                 # Dependency injection
    │   ├── entities           # Model classes  
    │   └── utils              # Utility classes tied to core of the application
    |
    |
    ├── domain                 # Use cases per feature or request
    │   ├── curated            # Provides use cases to fetch curated photos 
    │   └── search             # Provides use cases to search photos
    |
    ├── features               # Features in the application
    │   ├── curated            # Flow to fetch and display curated photos
    |   ├── search             # Flow to search and display photos based on query
    │   ├── profile            # Profile - currently only serves as placeholder
    │   ├── shared             # Common items used in both curated and search flows
    └──  ── utils              # Utility Classes / Kotlin extensions

## Architecture

This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)


## Contribute

If you want to contribute to this library, you're always welcome!

## Contact

If you need any help, feel free to contact me: kenan.karic@outlook.com.

## License
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
