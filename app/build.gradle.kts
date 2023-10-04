plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
}

android {
    namespace = "com.faroh.bwabanksandroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.faroh.bwabanksandroid"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


        buildConfigField("String", "BASE_URL", "\"https://bwabank.my.id/api/\"")
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui:1.5.2")
    implementation("androidx.compose.ui:ui-graphics:1.5.2")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation(platform("androidx.compose:compose-bom:2023.09.02"))
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.0-alpha06")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.0-alpha06")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    //compose
    implementation("androidx.ui:ui-tooling:1.0.0-alpha07")
    implementation("androidx.compose.runtime:runtime:1.5.2")
    implementation("androidx.compose.compiler:compiler:1.5.3")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.5.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.navigation:navigation-compose:2.7.3")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.2")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //Room
    val room_version = "2.5.2"
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-rxjava2:$room_version")
    testImplementation("androidx.room:room-testing:$room_version")

    //Retrofit
    val retrofit_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")
    val logging_interceptor_version = "4.9.0"
    implementation("com.squareup.okhttp3:logging-interceptor:$logging_interceptor_version")

    //Lifecycle KTX
    val lifecycle_ktx_version = "2.6.2"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_ktx_version")
    implementation("androidx.lifecycle:lifecycle-livedata-core-ktx:$lifecycle_ktx_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_ktx_version")
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:$lifecycle_ktx_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_ktx_version")
    //noinspection LifecycleAnnotationProcessorWithJava8
    ksp("androidx.lifecycle:lifecycle-compiler:$lifecycle_ktx_version")

    //RxJava
    val rxjava_version = "2.2.19"
    implementation("io.reactivex.rxjava2:rxjava:$rxjava_version")
    val rxandroid_version = "2.1.1"
    implementation("io.reactivex.rxjava2:rxandroid:$rxandroid_version")
    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofit_version")
    implementation("android.arch.lifecycle:reactivestreams:1.1.1")
    implementation("android.arch.lifecycle:extensions:1.1.1")

    //DataStore
    val data_store_version = "1.0.0"
    implementation("androidx.datastore:datastore-preferences:$data_store_version")
    implementation("androidx.datastore:datastore-preferences-rxjava2:$data_store_version")
    implementation("androidx.datastore:datastore-core:$data_store_version")

    //Hilt Injection
    val hilt_version = "2.47"
    implementation("com.google.dagger:hilt-android:$hilt_version")
    ksp("com.google.dagger:hilt-android-testing:$hilt_version")
    ksp("com.google.dagger:hilt-compiler:$hilt_version")

    //Activity and Fragment KTX
    val activity_ktx_version = "1.7.2"
    implementation("androidx.activity:activity-ktx:$activity_ktx_version")
    val fragment_ktx_version = "1.6.1"
    implementation("androidx.fragment:fragment-ktx:$fragment_ktx_version")

    //goolge fonts
    implementation("androidx.compose.ui:ui-text-google-fonts:1.5.2")

}