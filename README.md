# Shoppy
A mobile app that shows a list of products and their details in an ecommerce environment  written 100% in Kotlin and Jetpack Compose using Android Jetpack Components.
## Prerequisites
To inspect network requests, run the app, and [Chucker](https://github.com/ChuckerTeam/chucker) will post a notification with all intercepted requests.

## Background
* Create a page to list all products, in the order in which they appear in the API response
* Create a page to view a single product once a product has been selected from the list.

## Tech-stack
* Tech-stack
    * [Kotlin](https://kotlinlang.org/) - a modern, cross-platform, statically typed, general-purpose programming language with type inference.
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - lightweight threads to perform asynchronous tasks.
    * [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - a stream of data that emits multiple values sequentially.
    * [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#:~:text=StateFlow%20is%20a%20state%2Dholder,property%20of%20the%20MutableStateFlow%20class.) - Flow APIs that enable flows to emit updated state and emit values to multiple consumers optimally.
    * [Dagger Hilt](https://dagger.dev/hilt/) - a dependency injection library for Android built on top of [Dagger](https://dagger.dev/) that reduces the boilerplate of doing manual injection.
    * [Coil](https://coil-kt.github.io/coil/) - An image-loading library for Android backed by Koltin Coroutines.
    * [Moshi Converter](https://github.com/square/retrofit/blob/master/retrofit-converters/moshi/README.md) A JSON serialization converter which uses Moshi
    * [Jetpack](https://developer.android.com/jetpack)
        * [Jetpack Compose](https://developer.android.com/jetpack/compose) - A modern toolkit for building native Android UI
        * [Room](https://developer.android.com/topic/libraries/architecture/room) - a persistence library that provides an abstraction layer over SQLite.
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform actions in response to a change in the lifecycle state.
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data lifecycle conscious manner and survives configuration change.
    * [Chucker](https://github.com/ChuckerTeam/chucker) An on-device Http inspector for Android and OkHttp.
    * [Timber](https://github.com/JakeWharton/timber) - a highly extensible Android logger.

* Architecture
    * MVVM - Model View View Model
* Tests
    * [JUnit](https://junit.org/junit4/) - a simple framework for writing repeatable tests.
    * [MockK](https://github.com/mockk) - mocking library for Kotlin
    * [Turbine](https://github.com/cashapp/turbine) - A testing library for Kotlin Flows
    * [Truth](https://github.com/agoda-com/Kakao) - A fluent assertions library for Android and Java.
* Gradle
    * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - An alternative syntax for writing Gradle build scripts using Koltin.
    * [Version Catalogs](https://developer.android.com/build/migrate-to-catalogs) - A scalable way of maintaining dependencies and plugins in a multi-module project.
    * [Convention Plugins](https://docs.gradle.org/current/samples/sample_convention_plugins.html) - A way to encapsulate and reuse common build configuration in Gradle, see [here](https://github.com/daniel-waiguru/Tourist-News/tree/main/build-logic/convention/src/main/java)
    * Plugins
        * [Ktlint](https://github.com/JLLeitschuh/ktlint-gradle) - creates convenient tasks in your Gradle project that run ktlint checks or do code auto format.
        * [Spotless](https://github.com/diffplug/spotless) - format Java, groovy, markdown, and license headers using gradle.
* CI/CD
    * [GitHub Actions](https://github.com/features/actions)
 
## Dependencies

All the dependencies (external libraries) are managed using version catalogs and defined in a single place `gradle/libs.versions.toml` file. This is a scalable approach to manage dependencies and use the same dependency version across all modules.

## Code Analysis
This repo uses ktlint, a Kotlin linter, to analyze the codebase, identify potential code style violations, code quality issues, etc.
Before every commit, make sure you run the following bash script:

```shell script
./codeAnalysis.sh
```

## Testing
The screenshots below show test reports for tests done on this repo

#### Unit Tests
<img src="/screenshots/unit_tests.png"/>

#### UI Tests
<img src="/screenshots/ui_tests.png"/>

## App Architecture
A well-planned architecture is extremely important for any Android project; It makes it easier to maintain the app as the codebase grows and the team expands. This repo uses the MVVM pattern with clean architecture to have decoupled, testable, and maintainable code.
MVVM separates views (Activities, Fragments, or Composables) from the app's business logic. However, as the codebase grows, ViewModels start bloating, and separation of responsibilities becomes hard hence the need to use MVVM with clean architecture. 
#### Why Clean Architecture and Modularization?
  * Allows the app to scale easily
  * Easier onboarding of new team members
  * Easier to test code
  * Makes it easier to enforce coder ownership
This repo uses MVVM with Clean Architecture with the following modules:
#### Data
Contains repositories, data sources, and model classes. This layer hides the implementation details and data sources from the outside.
#### Domain
This module encapsulates complex business logic or simple logic that multiple ViewModels reuse. It contains all the use cases of the application and models independent of any framework-specific dependencies and represents the business logic.
#### Presentation
Contains views(in this app, Composable) and ViewModels. The views post events to the ViewModel and subscribe to the updated state.
#### Design System
Contains reusable UI components that can be reused across various modules
#### Testing
This module contains test code, test resources, and test dependencies.

## App Screenshots
<img src="/screenshots/products_screen.png" width="260"/>&emsp;<img src="/screenshots/product_info_screen.png" width="260"/>

## License
```
MIT License

Copyright (c) 2023 Daniel Waiguru

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF  TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
