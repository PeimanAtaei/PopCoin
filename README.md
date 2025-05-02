
# PopCoin

*A modern, lightweight cryptocurrency tracker built with Kotlin and Clean Architecture.*


## 📱 Overview

PopCoin is a sleek and intuitive Android application that allows users to monitor cryptocurrency data.  
Designed with a focus on clean architecture and modularity, it ensures maintainability and scalability.  
The app fetches data from the CoinPaprika API and presents it in a user-friendly interface.

![PopCoin](https://github.com/user-attachments/assets/efe46ef6-bf12-49b6-bc6a-a197531fe3cb)




## ✨ Features

- 🔄 Cryptocurrency listings
- 📊 Detailed coin information
- 📱 Responsive UI with XML layouts
- 🧩 MVVM architecture with Clean Architecture principles
- 💉 Dependency Injection using Hilt
- ⚡ Asynchronous data handling with Coroutines and Flow


## 🧱 Architecture

The application is structured following the Clean Architecture paradigm:

- **Presentation Layer**: Activities and ViewModels handle UI and user interactions.
- **Domain Layer**: Contains use cases and business logic.
- **Data Layer**: Manages data sources, including network services and models.

This separation of concerns facilitates easier testing, maintenance, and scalability.


## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI**: XML Layouts
- **Architecture**: MVVM with Clean Architecture
- **Networking**: Retrofit
- **Dependency Injection**: Hilt
- **Asynchronous Programming**: Kotlin Coroutines and Flow
- **Image Loading**: Glide
- **Navigation**: Jetpack Navigation Component
- **Chart**: MPAndroidChart
- **Animation**: lottie


## 🚀 Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- Gradle 7.0 or higher
- Android SDK 21+


### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/PeimanAtaei/PopCoin.git
   cd PopCoin

1. **Open the project in Android Studio**
  - Launch Android Studio
  - Click "Open" or "Open an existing project"
  - Navigate to the cloned PopCoin directory and select it
  - Let Gradle finish syncing dependencies
  - Build and run the app
