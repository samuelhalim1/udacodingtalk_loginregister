const val kotlinVersion = "1.4.30"

object BuildPlugins {

  object Versions {
    const val gradleVersion = "4.1.2"
  }

  const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradleVersion}"
  const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
  const val kotlinAndroidExt = "org.jetbrains.kotlin:kotlin-android-extensions:$kotlinVersion"

  const val androidApplication = "com.android.application"
  const val kotlinAndroid = "kotlin-android"
  const val kotlinKapt = "kotlin-kapt"
  const val kotlinAndroidExtensions = "kotlin-android-extensions"
}

object AndroidSdk {
  const val min = 24
  private const val compile = 30
  const val buildToolsVersion = "30.0.3"
  const val target = compile
}

object Libraries {
  private object Versions {
    const val test = "4.13"
    const val testExt = "1.1.2"
    const val testEspresso = "3.3.0"

    const val liveData = "2.2.0"
    const val viewModel = "2.3.0-alpha07"
    const val coroutines = "1.4.3"

    const val appCompat = "1.2.0"
    const val coreKtx = "1.3.1"
    const val constraintLayout = "2.0.1"
    const val material = "1.2.1"

    const val koin = "2.2.0-rc-2"

    const val preference = "1.1.1"

    const val retrofit = "2.9.0"
    const val retrofitGson = "2.9.0"
    const val retrofitLogger = "4.8.1"

    const val rx = "3.0.0"
  }

  const val test = "junit:junit:${Versions.test}"
  const val testExt = "androidx.test.ext:junit:${Versions.testExt}"
  const val testEspresso = "androidx.test.espresso:espresso-core:${Versions.testEspresso}"

  const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
  const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveData}"
  const val lifecycleExt = "androidx.lifecycle:lifecycle-extensions:${Versions.liveData}"
  const val lifecycleViewModelKtx= "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}"
  const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
  const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

  const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
  const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
  const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
  const val material = "com.google.android.material:material:${Versions.material}"

  const val koinInjector = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

  const val preferenceManager = "androidx.preference:preference-ktx:${Versions.preference}"

  const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
  const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitGson}"
  const val retrofitLogger = "com.squareup.okhttp3:logging-interceptor:${Versions.retrofitLogger}"

  const val adapterRetrofitRx = "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofit}"
  const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rx}"
  const val rxKotlin = "io.reactivex.rxjava3:rxkotlin:${Versions.rx}"

}

