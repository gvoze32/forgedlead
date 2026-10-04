package com.example.forgedlead.mixin;

//? if <1.21.1 {
/*import net.minecraft.world.entity.PathfinderMob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
*///?} else if <1.21.6 {
import net.minecraft.world.entity.Leashable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
//?} else {
/*import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
*///?}

// Keeps leashes attached at any distance while leaving every other leash
// rule (holder death, shears, unloading, dimension changes) to vanilla.
//
// Before 1.21.1: PathfinderMob.tickLeash snaps past 10 blocks and pulls the
// mob between 6 and 10 blocks. Raising the snap distance keeps the pull.
//
// 1.21.1 to 1.21.5: Leashable.tickLeash has the same 10 / 6 block layout.
//
// 1.21.6 and later: the snap distance is per entity (12 blocks, 16 for
// ghasts) and snapping also plays the lead-break sound. The snap branch is
// replaced with the bounded pre-1.21.6 pull, so no sound plays every tick and
// distant mobs are pulled back instead of being launched by the spring model.
//? if <1.21.1 {
/*@Mixin(PathfinderMob.class)
public abstract class LeashableMixin {
    @ModifyConstant(method = "tickLeash", constant = @Constant(floatValue = 10.0F))
    private float forgedlead$neverSnap(float snapDistance) {
        return Float.MAX_VALUE;
    }
}
*///?} else if <1.21.6 {
@Mixin(Leashable.class)
public interface LeashableMixin {
    @ModifyConstant(method = "tickLeash", constant = @Constant(doubleValue = 10.0D))
    private static double forgedlead$neverSnap(double snapDistance) {
        return Double.MAX_VALUE;
    }
}
//?} else {
/*@Mixin(Leashable.class)
public interface LeashableMixin {
    @Inject(
            method = "tickLeash",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Leashable;leashSnapDistance()D"),
            cancellable = true
    )
    private static <E extends Entity & Leashable> void forgedlead$pullInsteadOfSnap(
            ServerLevel level, E entity, CallbackInfo ci) {
        Entity holder = entity.getLeashHolder();
        if (holder == null || entity.leashDistanceTo(holder) <= entity.leashSnapDistance()) {
            return;
        }

        Vec3 offset = holder.position().subtract(entity.position());
        double length = offset.length();
        if (length > 1.0E-4) {
            double x = offset.x / length;
            double y = offset.y / length;
            double z = offset.z / length;
            entity.setDeltaMovement(entity.getDeltaMovement().add(
                    Math.copySign(x * x * 0.4, x),
                    Math.copySign(y * y * 0.4, y),
                    Math.copySign(z * z * 0.4, z)));
        }
        entity.onElasticLeashPull();
        ci.cancel();
    }
}
*///?}
