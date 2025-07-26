# TrendTrack - News App

A modern Android news application built with Jetpack Compose that provides real-time news updates from around the world.

## Features

- 📰 Real-time news from multiple countries
- 🔍 Search functionality
- 📱 Modern Material Design 3 UI
- 🌍 Country selection
- 📖 Article detail view with web view
- 💾 Local database for bookmarks
- 🎨 Dark/Light theme support

## Setup

### Prerequisites

- Android Studio Arctic Fox or later
- Android SDK 24 or higher
- Kotlin 1.9.0 or higher

### API Key Setup

1. Get a free API key from [NewsAPI.org](https://newsapi.org/)
2. Copy `local.properties.example` to `local.properties`:
   ```bash
   cp local.properties.example local.properties
   ```
3. Open the `local.properties` file and replace `your_api_key_here` with your actual API key:
   ```properties
   NEWS_API_KEY=your_actual_api_key_here
   ```
4. The `local.properties` file is already in `.gitignore`, so your API key won't be committed to version control

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/TrendTrack.git
   ```

2. Open the project in Android Studio

3. Set up your API key as described above

4. Build and run the project

## Architecture

- **UI Layer**: Jetpack Compose with Material Design 3
- **ViewModel Layer**: MVVM architecture with Hilt dependency injection
- **Repository Layer**: Single source of truth for data
- **Data Layer**: Room database for local storage, Retrofit for API calls
- **DI**: Hilt for dependency injection

## Libraries Used

- **Jetpack Compose**: Modern UI toolkit
- **Retrofit**: HTTP client for API calls
- **Room**: Local database
- **Coil**: Image loading
- **Navigation Compose**: Navigation between screens

## API

This app uses the [NewsAPI.org](https://newsapi.org/) service to fetch news articles. The free tier includes:

- 1,000 requests per day
- Basic news sources
- Limited country support

## Screenshots
![WhatsApp Image 2025-07-26 at 18 22 45_cc95022e](https://github.com/user-attachments/assets/05015cdc-682c-4005-8372-4288a1d437e2)
![WhatsApp Image 2025-07-26 at 18 22 46_5022d170](https://github.com/user-attachments/assets/159ff3c3-4e91-487a-b62f-3ed8b524419c)
![WhatsApp Image 2025-07-26 at 18 22 46_ea733829](https://github.com/user-attachments/assets/4bc9a02f-6050-4916-8a05-56e71cbc91dc)

## Note

Make sure to add your own API key to `local.properties` before running the app. The API key in the code is a placeholder and won't work.
