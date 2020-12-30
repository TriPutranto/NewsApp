object ApplicationId {
    const val id = "com.triputranto.newsapp"
}

object Dependencies {
    const val buildGradle = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
}

object Release {
    const val versionName = "1.0"
}

object TestInstrumentation {
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}


object Versions {
    //Android
    const val kotlinVersion = "1.4.21"
    const val gradleVersion = "4.1.1"
    const val minSdk = 21
    const val compiledSdk = 29
    const val targetSdk = 29
    const val versionCode = 1


    const val appCompat = "1.2.0"
    const val coreKtx = "1.3.2"
    const val contraintLayout = "2.0.4"
    const val legacy = "1.0.0"

    //Testing
    const val jUnit = "4.12"
    const val extJUnit = "1.1.2"
    const val espressoCore = "3.3.0"

    //Network
    const val retrofit = "2.9.0"
    const val gson = "2.8.6"
    const val okHttp = "4.5.0"

    //ReactiveX
    const val rxAndroid = "2.1.1"
    const val rxJava = "2.2.15"
    const val rxAdapter = "1.0.0"

    //View
    const val materialDesign = "1.2.1"
    const val glide = "4.11.0"
    const val preference = "1.1.0"
    const val ahBottomNav = "2.3.4"
    const val shimmer = "0.5.0"

    //Architecture
    const val lifecycle = "2.2.0"

    //Anko
    const val anko = "0.10.8"

    //Koin
    const val koinVersion = "2.0.1"

    //Paging
    const val pagingVersion = "2.1.2"

    //Room
    const val roomVersion = "2.2.5"
}

object AndroidLibraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.contraintLayout}"
    const val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacy}"
    const val anko = "org.jetbrains.anko:anko:${Versions.anko}"
    const val preference = "androidx.preference:preference-ktx:${Versions.preference}"
}

object Testing {
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val extJUnit = "androidx.test.ext:junit:${Versions.extJUnit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object Network {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val okHttp = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
}

object ReactiveX {
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxAdapter = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.rxAdapter}"
}

object View {
    const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val ahBottomNav = "com.aurelhubert:ahbottomnavigation:${Versions.ahBottomNav}"
    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
}

object Architectures {
    const val lifeCycleExtension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifeCycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
}

object Koin {
    const val koinAndroid = "org.koin:koin-android:${Versions.koinVersion}"
    const val koinAndroidScope = "org.koin:koin-android-scope:${Versions.koinVersion}"
    const val koinAndroidViewModel = "org.koin:koin-android-viewmodel:${Versions.koinVersion}"
}

object Paging {
    const val pagingRuntime = "androidx.paging:paging-runtime-ktx:${Versions.pagingVersion}"
}

object Room {
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
}
