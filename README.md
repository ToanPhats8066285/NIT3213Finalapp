NIT3213 Mobile Application Development - Final Assignment

This Android application demonstrates API integration, Clean Architecture, and modern Android development practices for the NIT3213 course.

Project Overview

The app implements a three-screen workflow:

Login Screen: Campus selection + user authentication

Dashboard Screen: Displays entity list from API

Details Screen: Shows detailed information of a selected entity

API Integration

Base URL: https://nit3213api.onrender.com/

Login Endpoint: POST /{campus}/auth

Dashboard Endpoint: GET /dashboard/{keypass}

Login Credentials

Username: Your first name

Password: Your Student ID without 's' (e.g., 8066285)

Campus: footscray, sydney, or br

Technical Stack

Language: Kotlin

Architecture: Clean Architecture (MVVM)

Dependency Injection: Hilt

Networking: Retrofit + Moshi

Navigation: Navigation Component + Safe Args

UI: Material Design 3 + ViewBinding

Data Storage: DataStore Preferences

Testing: JUnit + MockK + Coroutines Test

Project Setup
Prerequisites

Android Studio Jellyfish (2023.1.1+)

JDK 17+

Android SDK (API 24+)

Git

Installation Steps

Clone the repository:

git clone <your-repository-url>
cd FinalApp


Open in Android Studio:

Launch Android Studio

Select "Open an existing project"

Navigate to FinalApp/

Sync the project:

Gradle sync will start automatically

Accept SDK prompts if required

Set up an emulator or device:

Tools > AVD Manager

Create emulator (API 24+)

Run the app:

Press green ▶️ Run

Select emulator/connected device

Project Structure
FinalApp/
├── app/src/main/java/com/example/finalapp/
│   ├── MyApp.kt                  # Hilt entry point
│   ├── MainActivity.kt            # NavHost container
│   ├── data/
│   │   ├── api/ApiService.kt
│   │   ├── models/ (LoginRequest, LoginResponse, DashboardResponse, Entity)
│   │   └── repository/AppRepository.kt
│   ├── domain/
│   │   ├── repository/AppRepositoryInterface.kt
│   │   └── usecase/ (LoginUseCase, GetDashboardUseCase)
│   ├── di/ (NetworkModule, AppModule)
│   ├── presentation/
│   │   ├── login/ (LoginFragment, LoginViewModel)
│   │   ├── dashboard/ (DashboardFragment, DashboardAdapter, DashboardViewModel)
│   │   └── details/ (DetailsFragment)
│   └── utils/ (Constants, Resource)
├── res/layout/ (XML layouts)
├── res/navigation/nav_graph.xml
├── build.gradle (app-level)
├── settings.gradle
└── gradle.properties

Features
Login Screen

Campus dropdown + username + password

Input validation + error handling

Loading indicator

Dashboard Screen

RecyclerView entity list

Loading + error states

Navigate to details on item click

Details Screen

Display full entity info (field, concept, scientist, description)

Scrollable UI

Architecture

Domain Layer: Business logic & usecases

Data Layer: Repository + Retrofit API service

UI Layer: Fragments + ViewModels + Adapters

Dependency Injection

Hilt provides DI across app

@Singleton for repositories and network

@HiltViewModel for ViewModels

State Management

Resource<Success|Error|Loading> wrapper

LiveData observers in Fragments

Testing

JUnit for ViewModels & Repositories

MockK for mocking dependencies

Coroutines Test for async flows

Run:

./gradlew test

Assignment Requirements

✅ Three screens (Login, Dashboard, Details)
✅ API integration (Login + Dashboard)
✅ Navigation with Safe Args
✅ Clean Architecture + Hilt
✅ Error handling + loading states
✅ GitHub repo with README + License

Development Notes

Min SDK: 24

Target SDK: 35

Gradle: 8.1.4

Language: Kotlin

License

This project is for educational purposes as part of the NIT3213 Mobile Application Development course.
