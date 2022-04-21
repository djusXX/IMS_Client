/**
 * All dependencies for project
 * */

object ApplicationId {
    val id = "com.example.ims_mobile_client"
}

object Modules {
    val data = ":data"
    val domain = ":domain"

    // presentation modules
    val login = ":presentation:login"
    val calls = ":presentation:calls"
    val conversations = ":presentation:conversations"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

// Versions
object Ver {



    // Sdk and tools
    val minSdkVersion = 29
    val targetSdkVersion = 31
    val compileSdkVersion = 31
    val buildToolsVersion = "30.0.3"

    // App dependencies
    val androidTestRunner = "1.2.0"
    val annotation = "1.3.0"
    val appcompat = "1.4.1"
    val constraintlayout = "2.1.3"
    val constraintlayoutCompose = "1.0.0"
    val dagger = "2.41"
    val espressoCore = "3.4.0"
    val googleMaterial = "1.5.0"
    val gradle = "7.1.3"
    val gson = "2.9.0"
    val hiltAndroid = "2.38.1"
    val hiltAndroidGradlePlugin = "2.38.1"
    val hiltJetpack = "1.0.0-alpha02"
    val junit = "4.13.2"
    val kotlinVersion = "1.5.21"
    val lifecycleExtensions = "2.2.0"
    val lifecycle = "2.4.1"
    val mavenGradlePlugin = "1.4.1"
    val recyclerview = "1.2.1"
    val room = "2.4.2"
    val rxJava = "2.2.21"
    val rxAndroid = "2.1.1"
    val testExtJunit = "1.1.3"
    val okhttp3 = "4.9.3"
    val timber = "5.0.1"
    val voismartCrypto = "0.1.0"
    val workManager = "2.7.1"
}


object Libs {

    val dagger = "com.google.dagger:dagger:${Ver.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Ver.dagger}"
    val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Ver.dagger}"
    val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Ver.dagger}"

    val timber = "com.jakewharton.timber:timber:${Ver.timber}"

    // HILT
    val hilt = "com.google.dagger:hilt-android:${Ver.hiltAndroid}"
    val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Ver.hiltAndroid}"
    val hiltJetpack = "androidx.hilt:hilt-lifecycle-viewmodel:${Ver.hiltJetpack}"
    val hiltJetpackCompiler = "androidx.hilt:hilt-compiler:${Ver.hiltJetpack}"

    // For instrumentation tests
    val hiltAndroidTest = "com.google.dagger:hilt-android-testing:${Ver.hiltAndroid}"
    val hiltAndroidTestCompiler = "com.google.dagger:hilt-android-compiler:${Ver.hiltAndroid}"

    // For local unit tests
    val hiltAndroidUnitTest = "com.google.dagger:hilt-android-testing:${Ver.hiltAndroid}"
    val hiltAndroidUnitTestCompiler = "com.google.dagger:hilt-android-compiler:${Ver.hiltAndroid}"
    ////////////////////////////

    ////////////////////////////
    // ROOM
    val room = "androidx.room:room-runtime:${Ver.room}"
    val roomCompiler = "androidx.room:room-compiler:${Ver.room}"
    val roomCommon = "androidx.room:room-common:${Ver.room}"
    val roomRxJava2 = "androidx.room:room-rxjava2:${Ver.room}"
    ////////////////////////////

    ////////////////////////////
    // ViewModel and LiveData
    val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Ver.lifecycle}"
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Ver.lifecycle}"
    val lifecycleCompile = "androidx.lifecycle:lifecycle-compiler:${Ver.lifecycle}"
    val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Ver.lifecycle}"
    ////////////////////////////


    val materialDesign = "com.google.android.material:material:${Ver.googleMaterial}"

    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Ver.constraintlayout}"

    val appcompat = "androidx.appcompat:appcompat:${Ver.appcompat}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Ver.recyclerview}"
    val annotation = "androidx.annotation:annotation:${Ver.annotation}"


    val gson = "com.google.code.gson:gson:${Ver.gson}"
    val voismartCrypto = "com.github.voismart:crypto:${Ver.voismartCrypto}"


    // RxJava
    val rxJava = "io.reactivex.rxjava2:rxjava:${Ver.rxJava}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Ver.rxAndroid}"
    val workRuntime = "androidx.work:work-runtime:${Ver.workManager}"
    val workRxJava = "androidx.work:work-rxjava2:${Ver.workManager}"
    val workMultiProc = "androidx.work:work-multiprocess:${Ver.workManager}"
}

object TestLibs {

    val androidTestRunner = "androidx.test:runner:${Ver.androidTestRunner}"
    val espresso = "androidx.test.espresso:espresso-core:${Ver.espressoCore}"
    val espressoContrib = "androidx.test.espresso:espresso-contrib:${Ver.espressoCore}"
    val junit = "androidx.test.ext:junit:${Ver.testExtJunit}"
    val workTesting = "androidx.work:work-testing:${Ver.workManager}"

}


















