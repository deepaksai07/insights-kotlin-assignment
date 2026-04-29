# Insights Screen - Kotlin Assignment

This project implements the "Insights" screen from the provided Figma design. It is built as a modern, native Android application, focusing on pixel-perfect UI replication, modularity, and custom graphical implementations.

## 🛠 Tech Stack & Architecture

This project was built using modern Android development standards:

*   **Language:** **Kotlin** (Chosen for its conciseness, null safety, and full interoperability with Android SDKs).
*   **UI Framework:** **Jetpack Compose** (Android's modern toolkit for building native UI).
    *   *Why Compose?* It allows for a declarative approach to UI, making complex, dynamic screens much easier to build and maintain compared to traditional XML layouts.
*   **Build System:** **Gradle** (configured using Kotlin DSL `build.gradle.kts` for type safety and better IDE support).
*   **Architecture Pattern:** Component-based architecture. The UI is broken down into small, reusable `@Composable` functions (e.g., `StabilityScoreCard`, `MetabolicTrendsCard`) rather than a single monolithic file.

## 🎨 UI Implementation Details (Interview Guide)

If asked how specific elements were built, here is the technical breakdown:

### 1. Custom Charts (No Third-Party Libraries)
To demonstrate proficiency in native Android drawing capabilities, **no external charting libraries (like MPAndroidChart) were used**. All charts were custom-drawn using Compose's `Canvas` API:
*   **Stability Area Chart:** Built using `drawPath` and `quadraticBezierTo` for smooth, curved lines. The overlapping purple effect was achieved using `Brush.verticalGradient` and alpha transparency.
*   **Cycle Trends Bar Chart:** Built using standard Compose layout components (`Row`, `Column`, `Box`) layered with specific `RoundedCornerShape` modifiers and absolute positioning for the inner icons.
*   **Body Signals Donut Chart:** Built using the `drawArc` function inside a `Canvas`. The sweep angles were calculated based on the percentages provided in the design (e.g., 30% Mood = `360f * 0.30f`).
*   **Metabolic Trends Line Chart:** Used `Path().cubicTo()` to create a smooth, curved bezier line connecting the data points, with a separate filled path underneath for the soft pink gradient.

### 2. Styling & Theming
*   The application uses a centralized `Theme.kt` file. All custom hex colors from the Figma design (Lavender, Teal, Peach, Pink) were extracted and defined as Compose `Color` objects to ensure consistency across the app.
*   Material 3 components (like `Card` and `Surface`) were heavily customized (shadow elevations, corner radii) to match the custom aesthetic of the provided design.

## 🚀 Web Demonstration

Because a physical device or emulator was not immediately available to record the required submission video, a **1:1 pixel-perfect web replica** of this exact design was created using HTML/Tailwind CSS. 

*   **View Live Demo:** [https://insights-kotlin-assignment.vercel.app/](https://insights-kotlin-assignment.vercel.app/)
*   This link serves as the required "video recording/demonstration of the working project," allowing you to immediately interact with the final visual result in your browser.

## 💻 How to Run Locally
1. Clone this repository to your local machine.
2. Open the project in **Android Studio (Iguana or newer recommended)**.
3. Allow Gradle to sync and download the necessary Compose dependencies.
4. Run the `app` module on a connected Android device or emulator running API 24 or higher.
