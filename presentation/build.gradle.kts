@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("shoppy.android.feature")
    id("shoppy.android.library.compose")
}

android {
    namespace = "com.danielwaiguru.shoppy.presentation"
}
dependencies {
    implementation(project(":designsystem"))
    implementation(libs.accompanist.systemuicontroller)
    testImplementation(project(":testing"))
    androidTestImplementation(project(":testing"))
}