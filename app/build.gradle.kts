plugins {
  kotlin("kapt")
  id("com.google.dagger.hilt.android")
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
}

tasks.withType<Test> {
  useJUnitPlatform()
}

android {
  namespace = "com.seweryn.piotr.codingchallenge"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.seweryn.piotr.codingchallenge"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.1"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  implementation(project(":domain"))
  implementation(project(":data"))

  implementation("androidx.core:core-ktx:1.12.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
  implementation("androidx.activity:activity-compose:1.8.2")
  implementation(platform("androidx.compose:compose-bom:2023.08.00"))
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-graphics")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("androidx.compose.material3:material3")
  implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
  implementation("com.google.dagger:hilt-android:2.48")
  kapt("com.google.dagger:hilt-android-compiler:2.48")
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.1")
  testImplementation("io.mockk:mockk:1.13.10")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
  debugImplementation("androidx.compose.ui:ui-tooling")
  debugImplementation("androidx.compose.ui:ui-test-manifest")
}