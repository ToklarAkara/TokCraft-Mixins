package com.toklar.tokcraftmixins.mixin.vanilla;

import com.toklar.tokcraftmixins.config.NightVisionConfig;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {

    // ------------------------------------------------------------
    // 1. NIGHT VISION BRIGHTNESS NERF 
    // ------------------------------------------------------------
    @Inject(method = "getNightVisionBrightness", at = @At("RETURN"), cancellable = true)
    private void nerfNightVisionBrightness(EntityLivingBase entity, float partialTicks, CallbackInfoReturnable<Float> cir) {

        if (!NightVisionConfig.ENABLE_NIGHT_VISION_PATCH) {
            return;
        }

        float original = cir.getReturnValue();

    
        float factor = NightVisionConfig.NIGHT_VISION_FACTOR;

        cir.setReturnValue(original * factor);
    }

    // ------------------------------------------------------------
    // 2. RESTORE FOG REMOVED BY NIGHT VISION
    // ------------------------------------------------------------
    @Inject(method = "setupFog", at = @At("HEAD"), cancellable = true)
    private void restoreFog(int startCoords, float partialTicks, CallbackInfo ci) {

        if (!NightVisionConfig.ENABLE_NIGHT_VISION_PATCH) {
            return;
        }

        ci.cancel();
    }
}
