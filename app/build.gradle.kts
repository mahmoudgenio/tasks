plugins {
    alias(libs.plugins.android.application)

}

android {
    namespace = "com.example.loginactivity"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.loginactivity"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.room.common)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)



    //maven
  //  implementation("org.apache.poi:poi:4.1.2")
    implementation(libs.poi.ooxml)

    implementation("org.apache.poi:poi:5.2.3")
// Room
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")


    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))

    // define any required OkHttp artifacts without version
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // AndroidX Core and AppCompat
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.0")

    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

    implementation ("com.zebra:emdk:8.0.0")


   //implementation("libs-zebra:zebra-scanner-library:1.0.0")

    compileOnly ("com.symbol:emdk:7.6.10")
    // Zebra Scanner SDK
    implementation (libs.scanner.sdk)
    implementation (libs.zebra.emdk)

    implementation (libs.printer.sdk)

    implementation (libs.device.utils)


    implementation (libs.key.management)



}