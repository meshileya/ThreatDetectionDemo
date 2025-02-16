plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.navigation.safeargs.kotlin)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.techies.threatdetectiondemo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.techies.threatdetectiondemo"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        signingConfig = signingConfigs.getByName("debug")
        buildConfigField(
            "String", "BASE_URL", "\"https://urlhaus-api.abuse.ch/v1/\""
        )
        buildConfigField(
            "int", "TIMEOUT_SECONDS", "10"
        )

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    hilt {
        enableAggregatingTask = false
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
        compose = true
    }

    dataBinding {
        addKtx = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    //UI [compose]
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.paging.compose)
    implementation (libs.material.icons.extended)
    //DI
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.dagger.hilt.android)
    testImplementation(libs.androidx.runner)
    kapt(libs.hilt.compiler)


    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.fragment.testing)
    implementation(libs.androidx.runtime.livedata)
    debugImplementation(libs.androidx.ui.tooling)
    kapt(libs.dagger.hilt.android.compiler)
    implementation (libs.androidx.datastore.preferences)
    //Network
    implementation(libs.adapter.rxjava)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.converter.scalars)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)
    //Test
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation (libs.junit.jupiter.api)
    testImplementation (libs.junit.jupiter.engine)
    testImplementation (libs.mockk)
    testImplementation (libs.mockito.core.v3112)
    testImplementation(libs.hilt.android.testing)
    kaptTest(libs.hilt.compiler.v244)
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.compiler)
    testImplementation (libs.androidx.hilt.lifecycle.viewmodel)
    testImplementation (libs.hilt.android.testing.v2391)
    testImplementation (libs.org.jetbrains.kotlinx.kotlinx.coroutines.test)
    testImplementation (libs.org.jetbrains.kotlinx.kotlinx.coroutines.test)
    testImplementation (libs.kotlinx.coroutines.core)
    testImplementation (libs.kotlinx.coroutines.test.v173)


}