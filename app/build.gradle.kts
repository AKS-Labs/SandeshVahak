import java.util.Properties

plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.application)
    id("org.jetbrains.kotlin.kapt")
    id("kotlin-parcelize")
}

android {
    val localProps = Properties()
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localProps.load(localPropertiesFile.inputStream())
    } else {
        println("local.properties file not found. Release signing may fail.")
    }

    // Keep namespace to match existing R imports
    namespace = "com.akslabs.SandeshVahak"
    compileSdk = 35

    signingConfigs {
        create("release") {
            val keystoreFileProp = localProps.getProperty("KEYSTORE_FILE")
            val keystorePasswordProp = localProps.getProperty("KEYSTORE_PASSWORD")
            val keyAliasProp = localProps.getProperty("KEY_ALIAS")
            val keyPasswordProp = localProps.getProperty("KEY_PASSWORD")

            if (keystoreFileProp != null && keystorePasswordProp != null && keyAliasProp != null && keyPasswordProp != null) {
                storeFile = file(keystoreFileProp)
                storePassword = keystorePasswordProp
                keyAlias = keyAliasProp
                keyPassword = keyPasswordProp
            } else {
                println("Release signing configuration is incomplete. Check KEYSTORE_FILE, KEYSTORE_PASSWORD, KEY_ALIAS, KEY_PASSWORD in local.properties.")
            }
        }
    }

    defaultConfig {
        // Use the new app ID to match your renamed package
        applicationId = "com.akslabs.SandeshVahak"
        minSdk = 29
        targetSdk = 36
        versionCode = 3
        versionName = "0.3"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // This properties loading might be redundant now if not used for other purposes in defaultConfig
        // val properties = Properties()
        // properties.load(rootProject.file("local.properties").inputStream())


    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        create("debugMini") {
            initWith(getByName("debug"))
            isDebuggable = true
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            setMatchingFallbacks(listOf("debug"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-Xcontext-receivers"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    dependenciesInfo {
        // Disables dependency metadata when building APKs.
        includeInApk = false
        // Disables dependency metadata when building Android App Bundles.
        includeInBundle = false
    }
    
    lint {
        // Completely disable lint checks
        abortOnError = false
        checkReleaseBuilds = false
        ignoreWarnings = true
        quiet = true
        disable.addAll(listOf(
            "ExtraTranslation",
            "MissingTranslation",
            "InvalidPackage",
            "VectorPath",
            "IconExpectedSize",
            "GoogleAppIndexingWarning",
            "UnusedResources"
        ))
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KaptGenerateStubs> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

// Disable all lint tasks
tasks.whenTaskAdded {
    if (name.contains("lint", ignoreCase = true)) {
        enabled = false
    }
}



dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.animation)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.security.crypto)

    // Explicitly exclude LeakCanary from all configurations
    configurations.all {
        exclude(group = "com.squareup.leakcanary", module = "leakcanary-android")
        exclude(group = "com.squareup.leakcanary", module = "leakcanary-android-core")
        exclude(group = "com.squareup.leakcanary", module = "leakcanary-android-instrumentation")
        exclude(group = "com.squareup.leakcanary", module = "leakcanary-object-watcher-android")
        exclude(group = "com.squareup.leakcanary", module = "leakcanary-object-watcher")
        exclude(group = "com.squareup.leakcanary", module = "shark-android")
        exclude(group = "com.squareup.leakcanary", module = "shark")
        exclude(group = "com.squareup.leakcanary", module = "shark-hprof")
        exclude(group = "com.squareup.leakcanary", module = "shark-log")
    }

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.contentpager)

    // Telegram bot
    implementation(libs.telegram)

    // Compose navigation
    implementation(libs.androidx.navigation.compose)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.converter.jackson)
    implementation(libs.okhttp)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    //noinspection KaptUsageInsteadOfKsp
    kapt(libs.androidx.room.compiler)

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)



    // Material icons
    implementation(libs.androidx.material.icons.extended)

    // New 3-Way navigation
    implementation(libs.androidx.compose.material3.adaptive.navigation)
    implementation(libs.androidx.compose.material3.adaptive.navigation.suite)

    // Work Manager
    implementation(libs.androidx.work.runtime)

    // Paging
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)


}