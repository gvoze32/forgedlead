import dev.kikugie.stonecutter.build.StonecutterBuildExtension
import net.fabricmc.loom.api.LoomGradleExtensionAPI
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.getByType

plugins {
    id("gg.meza.stonecraft")
}

val stonecutter = extensions.getByType<StonecutterBuildExtension>()
// Inactive versions compile the Stonecutter-processed copy. The active version
// keeps Stonecutter's default (root src/main/java, already in its state) so
// the IDE resolves Minecraft classes while editing.
if (!stonecutter.current.isActive) {
    val mainSources = extensions.getByType<SourceSetContainer>().named("main").get()
    mainSources.java.setSrcDirs(listOf(stonecutter.tasks.generatedSourcesDir.dir("main/java")))

    tasks.named("compileJava") {
        dependsOn("stonecutterGenerate")
    }
}

// Legacy Forge ignores [[mixins]] in mods.toml; it only loads configs listed
// in the jar manifest (MixinConfigs), which Loom writes from this setting.
val loom = extensions.getByType<LoomGradleExtensionAPI>()
if (loom.isForge) {
    loom.forge { mixinConfig("forgedlead.mixins.json") }
}

modSettings {
    variableReplacements.put("license", "MIT")
}
