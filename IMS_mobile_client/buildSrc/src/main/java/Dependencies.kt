/**
 * All dependencies for project
 * */

object ApplicationId {
    val id = "ims_mobile_client"
}

object Modules {
    val data = ":data"
    val domain = ":domain"
    val presentation = ":presentation"
    val localStorage = ":localStorage"
    val pjsua = ":pjsua2IMS"
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
    val androidxSavedState = "1.1.0"
    val androidxActivity = "1.4.0"
    val androidxFragment = "1.4.1"
    val lifecycle = "2.4.1"

    val androidTestRunner = "1.2.0"
    val appcompat = "1.4.1"
    val constraintlayout = "2.1.3"
    val coreSplashScreen = "1.0.0-beta02"
    val dagger = "2.42"
    val espressoCore = "3.4.0"
    val gradle = "7.2.0"
    val hiltAndroid = "2.42"
    val hiltAndroidGradlePlugin = "2.42"
    val hiltCore = "2.42"
    val javaxInject = "1"
    val kotlinVersion = "1.5.31"
    val navigation = "2.5.0"
    val material = "1.6.1"
    val recyclerview = "1.2.1"
    val room = "2.4.2"
    val rxJava = "2.2.21"
    val rxAndroid = "2.1.1"
    val safeArgs = "2.5.1"
    val testExtJunit = "1.1.3"
    val viewPager2 = "1.0.0"

}


object Libs {

    val androidxSavedState = "androidx.savedstate:savedstate:${Ver.androidxSavedState}"
    val androidxActivity = "androidx.activity:activity:${Ver.androidxActivity}"
    val androidxFragment = "androidx.fragment:fragment:${Ver.androidxFragment}"
    val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel:${Ver.lifecycle}"
    val lifecycleSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Ver.lifecycle}"

    val javaxInject = "javax.inject:javax.inject:${Ver.javaxInject}"
    val dagger = "com.google.dagger:dagger:${Ver.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Ver.dagger}"
    val hilt = "com.google.dagger:hilt-android:${Ver.hiltAndroid}"
    val hiltCore = "com.google.dagger:hilt-core:${Ver.hiltCore}"
    val hiltCompiler = "com.google.dagger:hilt-compiler:${Ver.hiltAndroid}"
    val hiltAndroidTest = "com.google.dagger:hilt-android-testing:${Ver.hiltAndroid}"
    val hiltAndroidUnitTest = "com.google.dagger:hilt-android-testing:${Ver.hiltAndroid}"
    val room = "androidx.room:room-runtime:${Ver.room}"
    val roomCompiler = "androidx.room:room-compiler:${Ver.room}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Ver.constraintlayout}"
    val appcompat = "androidx.appcompat:appcompat:${Ver.appcompat}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Ver.recyclerview}"
    val rxJava = "io.reactivex.rxjava2:rxjava:${Ver.rxJava}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Ver.rxAndroid}"
    val coreSplashScreen = "androidx.core:core-splashscreen:${Ver.coreSplashScreen}"
    val navigationFragment = "androidx.navigation:navigation-fragment:${Ver.navigation}"
    val navigationUi = "androidx.navigation:navigation-ui:${Ver.navigation}"
    val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Ver.navigation}"
    val material = "androidx.compose.material:material:${Ver.material}"
    val viewPager2 = "androidx.viewpager2:viewpager2:${Ver.viewPager2}";

}

object TestLibs {

    val androidTestRunner = "androidx.test:runner:${Ver.androidTestRunner}"
    val espresso = "androidx.test.espresso:espresso-core:${Ver.espressoCore}"
    val espressoContrib = "androidx.test.espresso:espresso-contrib:${Ver.espressoCore}"
    val junit = "androidx.test.ext:junit:${Ver.testExtJunit}"

}


















