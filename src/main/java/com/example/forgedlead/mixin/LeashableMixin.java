package com.example.forgedlead.mixin;

//? if <1.21.1 {
import net.minecraft.world.entity.PathfinderMob;
//?} else {
/*import net.minecraft.world.entity.Leashable;
*///?}
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Prevents distance-based leash breaks while preserving normal leash behavior.
 *
 * <p>Before 1.21.1, the distance check lives in {@code PathfinderMob}. Modern
 * versions route it through {@code Leashable.leashTooFarBehaviour()}.</p>
 */
//? if <1.21.1 {
@Mixin(PathfinderMob.class)
public abstract class LeashableMixin {
//?} else {
/*@Mixin(Leashable.class)
public interface LeashableMixin {
*///?}

//? if <1.21.1 {
    @SuppressWarnings("target")
    @ModifyConstant(
            method = {
                    "tickLeash()V",
                    "method_5995()V",
                    "fp()V",
                    "fx()V",
                    "fN()V"
            },
            constant = @Constant(floatValue = 10.0F),
            require = 0,
            remap = false
    )
    private static float forgedlead$modifyLeashBreakDistance(float original) {
        return Float.MAX_VALUE;
    }
//?}

//? if >=1.21.1 {
    @Inject(
            method = "leashTooFarBehaviour",
            at = @At("HEAD"),
            cancellable = true
    )
    private void forgedlead$cancelLeashBreak(CallbackInfo ci) {
        ci.cancel();
    }
//?}
}
