package com.example.forgedlead.mixin;

import net.minecraft.world.entity.Leashable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Makes leads unbreakable by modifying the Leashable interface.
 *
 * Two-layer protection:
 * 1. Extends the leash snap distance to effectively infinite,
 *    so the break condition never triggers.
 * 2. Cancels leashTooFarBehaviour() as a safety net,
 *    in case the break condition is somehow reached.
 */
@Mixin(Leashable.class)
public interface LeashableMixin {

    /**
     * Modifies the hardcoded leash break distance (10.0 blocks) in tickLeash
     * to Double.MAX_VALUE, making it impossible for the distance check to trigger.
     */
    @ModifyConstant(
            method = "tickLeash",
            constant = @Constant(doubleValue = 10.0)
    )
    private static double forgedlead$modifyLeashBreakDistance(double original) {
        return Double.MAX_VALUE;
    }

    /**
     * Safety net: if leashTooFarBehaviour is somehow called despite the
     * distance override, cancel it entirely to prevent the lead from dropping.
     */
    @Inject(
            method = "leashTooFarBehaviour",
            at = @At("HEAD"),
            cancellable = true
    )
    private void forgedlead$cancelLeashBreak(CallbackInfo ci) {
        ci.cancel();
    }
}
