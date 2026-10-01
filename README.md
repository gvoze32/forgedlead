Makes leads (leashes) unbreakable in Minecraft. No more leads snapping when your animals wander too far!

ForgedLead combines the best features from multiple lead improvement mods into a single, lightweight cross-loader mod.

## Features

- Leads will never snap due to distance, no matter how far the leashed entity wanders
- If the break condition is somehow triggered, it gets cancelled as a safety net
- The lead break sound effect is muted to avoid audio clutter
- Lightweight and performance-friendly, pure Mixin-based with no overhead

## Supported Versions

| Minecraft | Loader |
| --- | --- |
| 1.18.2 | Forge |
| 1.18.2 | Fabric |
| 1.19.2 | Forge |
| 1.19.2 | Fabric |
| 1.20.1 | Forge |
| 1.20.1 | Fabric |
| 1.21.1 | Fabric |
| 1.21.1 | NeoForge |
| 1.21.11 | Fabric |
| 1.21.11 | NeoForge |
| 26.1.2 | Fabric |
| 26.1.2 | NeoForge |
| 26.2 | Fabric |
| 26.2 | NeoForge |

The target matrix is maintained with Stonecraft and Stonecutter. Shared gameplay
and mixin logic live in `src/main`; loader entrypoint branches use Stonecutter
conditions, and loader metadata stays in shared resources with target
placeholders. Builds compile the version-processed Java output for each target.

## FAQ

**Can I use this in my modpack?** Yes! Feel free to include this mod in any Modrinth or CurseForge modpack.

**Will this work on all supported loaders?** Yes. Each target ships native
Fabric, Forge, or NeoForge metadata while sharing the same lead behavior.

**Does this affect lead behavior when the mob dies?** No. Leads still drop normally when the leashed mob dies or when you shear the lead off. This mod only prevents leads from snapping due to distance.
