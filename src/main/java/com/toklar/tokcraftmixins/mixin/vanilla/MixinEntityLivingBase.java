
package com.toklar.tokcraftmixins.mixin.vanilla;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.toklar.tokcraftmixins.AttributionConfig; 
import com.toklar.tokcraftmixins.helpers.SummonDamageBuffHandlerProxy;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase {

    @Shadow private EntityPlayer attackingPlayer;
    @Shadow private int recentlyHit;

    @Inject(method = "onDeath(Lnet/minecraft/util/DamageSource;)V",
            at = @At("HEAD"), remap = true)
    private void injectOnDeath(DamageSource source, CallbackInfo ci) {
        
        if (!AttributionConfig.ENABLE_VANILLA_ATTRIBUTION_PATCH) {
            return;
        }

        EntityPlayer owner = SummonDamageBuffHandlerProxy.resolveValidSummonOwner(source);
        if (owner != null) {
            this.attackingPlayer = owner;
            this.recentlyHit = 100;
        }
    }
}