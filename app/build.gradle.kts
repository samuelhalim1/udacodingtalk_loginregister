plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
}

android {
    compileSdkVersion(AndroidSdk.target)
    buildToolsVersion(AndroidSdk.buildToolsVersion)

    buildFeatures{
        dataBinding = true
    }

    defaultConfig {
        applicationId = "com.codingtalk.udacoding"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + "-Xuse-experimental=kotlin.ExperimentalStdlibApi"
    }
    androidExtensions {
        isExperimental = true
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //Unit Test
    implementation(Libraries.test)
    implementation(Libraries.testExt)
    implementation(Libraries.testEspresso)

    //Kotlin
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.lifecycleLiveDataKtx)
    implementation(Libraries.lifecycleExt)
    implementation(Libraries.lifecycleViewModelKtx)
    implementation(Libraries.coroutinesAndroid)
    implementation(Libraries.coroutinesCore)

    //Support Libraries (framework and layout components)
    implementation(Libraries.appCompat)
    implementation(Libraries.coreKtx)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.material)

    implementation(Libraries.koinInjector)

    implementation(Libraries.preferenceManager)

    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitGson)
    implementation(Libraries.retrofitLogger)

    implementation(Libraries.adapterRetrofitRx)
    implementation(Libraries.rxAndroid)
    implementation(Libraries.rxKotlin)
}