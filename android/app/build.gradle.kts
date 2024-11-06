plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.devtoolsKSP)
    alias(libs.plugins.jetbrainsKotlinParcelize)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "dev.haqim.netplix"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.haqim.netplix"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/\"")
            buildConfigField("String", "API_VERSION", "3")
            buildConfigField("String", "BASE_IMAGE_URL" ,"\"https://image.tmdb.org/t/p/w500/\"")
            buildConfigField("String", "API_KEY", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5ZTdhN2Y3N2I0NmQ3YTY3ZTU4MmE3MjYzNGZhNGM3OCIsInN1YiI6IjY0MjdkZWNmOGE4OGIyMDBmNDZmNWZlNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.V82Ta2QIJyUg5W1zmssfTuvsnYezmdzSlAZOkDQXctk\"")
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            applicationIdSuffix = ".debug"
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/\"")
            buildConfigField("String", "API_VERSION", "\"3\"")
            buildConfigField("String", "BASE_IMAGE_URL" ,"\"https://image.tmdb.org/t/p/w500/\"")
            buildConfigField("String", "API_KEY", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5ZTdhN2Y3N2I0NmQ3YTY3ZTU4MmE3MjYzNGZhNGM3OCIsInN1YiI6IjY0MjdkZWNmOGE4OGIyMDBmNDZmNWZlNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.V82Ta2QIJyUg5W1zmssfTuvsnYezmdzSlAZOkDQXctk\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.ui.text.google.fonts)
    // ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.kotlinSerialization)
    implementation(libs.ktor.contentNegotiation)
    implementation(libs.ktor.cio)
    implementation(libs.ktor.client.logging)
    // datastore
    implementation(libs.datastore.preferences)
    // serialization
    implementation(libs.kotlinx.serialization.json)
    // Koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    //room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.room.testing)
    implementation(libs.androidx.room.paging)
    // paging
    implementation(libs.paging.runtime.ktx)
    implementation(libs.androidx.paging.compose)
    // datetime
    implementation(libs.kotlinx.datetime)
    // image
    implementation(libs.coil.compose)
    implementation(libs.lottie.compose)
    // exoplayer
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.exoplayer.dash)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidyoutubeplayer)
//    implementation(libs.androidx.media3.exoplayer.youtube)
}