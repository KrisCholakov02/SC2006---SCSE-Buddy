# SC2006 - SCSEBuddy

SCSEBuddy is an innovative Android application designed specifically for SCSE students at NTU. Its primary purpose is to provide an immersive, 360° indoor navigation experience within the SCSE building by leveraging Dijkstra's algorithm to compute the shortest path between locations. In addition, the app offers complementary features such as course reviews and forum discussions to further assist the SCSE community.

## Table of Contents
- [Introduction](#introduction)
- [Purpose and Overview](#purpose-and-overview)
- [How It Works](#how-it-works)
- [Installation and Running](#installation-and-running)
- [Features](#features)
- [Testing](#testing)
- [Team](#team)

## Introduction
SCSEBuddy was conceived to address the challenges of navigating complex indoor environments such as the SCSE building. With a focus on precision and user experience, the app combines cutting-edge navigation technology with rich visual guides to help users find their way seamlessly.

## Purpose and Overview
The core purpose of SCSEBuddy is to provide a 360° indoor navigation solution for SCSE students. By integrating panoramic 360-degree images with a robust pathfinding algorithm (Dijkstra's algorithm), the application offers:
- **Efficient Navigation:** Calculate the shortest path between any two points within the building.
- **Immersive Guidance:** Display a sequence of 360° photos to help users visually follow the route.
- **User-Friendly Experience:** Ensure that users can quickly locate rooms and navigate through multiple floors with clear, step-by-step directions.

## How It Works
1. **User Input:** The user enters their current location and desired destination (room name/number).
2. **Path Computation:** The application applies Dijkstra's algorithm to compute the shortest route through the building's layout.
3. **360° Visual Guidance:** Once the path is computed, the app presents the route as a series of 360° panoramic images, complete with directional markers (arrows and stair instructions) that highlight the correct path.
4. **Interactive Navigation:** Users can follow the visual guide, ensuring they never lose their way even in unfamiliar parts of the building.

## Installation and Running
### Prerequisites
- **Device/Emulator:** An Android device or emulator running Android 10 (API level 29) or higher.
- **Internet Connection:** A stable internet connection for initial data retrieval and updates.

### Steps to Run the Application
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/SCSEBuddy.git
   ```
2. **Open in Android Studio:**
   - Launch Android Studio and select "Open an existing project."
   - Navigate to the cloned repository and open it.
3. **Build the Project:**
   - Allow Gradle to sync and build the project.
4. **Run the Application:**
   - Connect your Android device or start an emulator.
   - Click the "Run" button in Android Studio to deploy the app.
5. **Usage:**
   - On launch, the app displays a login screen (optional for full features). Unregistered users can still access the navigation feature.
   - Enter your current location and destination to start the 360° guided navigation.

## Features
- **360° Indoor Navigation:** Get visually guided directions using panoramic images.
- **Shortest Path Calculation:** Efficient route computation using Dijkstra's algorithm.
- **Multi-Floor Navigation:** Clear instructions for navigating across different floors, including stair directions.
- **Additional Tools:** Access course reviews and forum discussions to connect with fellow SCSE students.

## Testing
SCSEBuddy has undergone comprehensive testing to ensure a smooth user experience:
- **Navigation Accuracy:** Verifies correct pathfinding and error handling when entering invalid room names.
- **User Interface:** Ensures that the 360° photo guides are clear, and the touch targets are appropriately sized.
- **Performance:** Validates that the shortest path calculation and image rendering occur within acceptable timeframes.

## Team
**Team JKT (SSP1 Group 2)**  
- **Cholakov Kristiyan Kamenov (Team Leader)** – U2123543B  
- **Ang Jia Ze** – U2121818C  
- **Mo Jiayun** – U2123962F  
- **Theodore Tan Wan Li** – U2122077E  
- **Julian Wong Wei Sheng** – U2123563J  
- **Gupta Tushar Sandeep** – U2123144E
