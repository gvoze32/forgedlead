package com.example.forgedlead.mixin;

import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Keeps distance-based leash breaking disabled across the supported entity
 * implementations.
 *
 * <p>Legacy targets compare the leash distance in {@code tickLeash}; modern
 * targets delegate the break action to {@code leashTooFarBehaviour}.</p>
 */
@Mixin(Mob.class)
public abstract class LeashableMixin {

//? if <1.21.1 {
    // Include named, intermediary, and legacy official names for dev and remapped jars.
    @SuppressWarnings("target")
    @ModifyConstant(
            method = {
                    "tickLeash()V",
                    "method_5995()V",
                    "fp()V",
                    "fx()V",
                    "fN()V"
            },
            constant = @Constant(doubleValue = 10.0D),
            require = 0,
            remap = false
    )
    private static double forgedlead$modifyLeashBreakDistance(double original) {
        return Double.MAX_VALUE;
    }
//?}

//? if >=1.21.1 {
    // Include named, intermediary, and official names because no refmap is needed.
    @SuppressWarnings("target")
    @Inject(
            method = {"leashTooFarBehaviour()V", "method_60970()V", "z()V", "y()V"},
            at = @At("HEAD"),
            cancellable = true,
            require = 0,
            remap = false
    )
    private void forgedlead$cancelLeashBreak(CallbackInfo ci) {
        ci.cancel();
    }
//?}
}
