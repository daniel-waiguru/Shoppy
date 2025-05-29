
import com.android.build.gradle.LibraryExtension
import com.danielwaiguru.shoppy.convention.configureAndroidCompose
import com.danielwaiguru.shoppy.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")
            apply(plugin = libs.findPlugin("compose-compiler").get().get().pluginId)
            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)
        }
    }

}