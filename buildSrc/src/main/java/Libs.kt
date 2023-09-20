

object Libs {

    // Android and Kotlin
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val room = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val roomCoroutines = "androidx.room:room-coroutines:${Versions.roomVersion}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val detektApi = "io.gitlab.arturbosch.detekt:detekt-api:${Versions.detektVersion}"
    const val detektCli = "io.gitlab.arturbosch.detekt:detekt-cli:${Versions.detektVersion}"
    const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detektVersion}"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"

    // Test
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val jUnitAndroid = "androidx.test.ext:junit:${Versions.jUnitAndroidVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"

    // Dependency injection
    const val koin = "io.insert-koin:koin-android:${Versions.koinVersion}"

    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
}