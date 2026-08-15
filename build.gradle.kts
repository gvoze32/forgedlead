import dev.kikugie.stonecutter.build.StonecutterBuildExtension
import org.gradle.kotlin.dsl.getByType

plugins {
    id("gg.meza.stonecraft")
}

val stonecutter = extensions.getByType<StonecutterBuildExtension>()

modSettings {
    variableReplacements.put("license", "MIT")
}
