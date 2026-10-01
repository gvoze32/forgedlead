import dev.kikugie.stonecutter.build.StonecutterBuildExtension
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.getByType

plugins {
    id("gg.meza.stonecraft")
}

val stonecutter = extensions.getByType<StonecutterBuildExtension>()
val mainSources = extensions.getByType<SourceSetContainer>().named("main").get()
mainSources.java.setSrcDirs(listOf(stonecutter.tasks.generatedSourcesDir.dir("main/java")))

tasks.named("compileJava") {
    dependsOn("stonecutterGenerate")
}

modSettings {
    variableReplacements.put("license", "MIT")
}
