package com.toklar.tokcraftmixins.mixin.infernalmobs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import atomicstryker.infernalmobs.common.InfernalMobsCore;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.silentchaos512.scalinghealth.api.ScalingHealthAPI;

import com.toklar.tokcraftmixins.config.InfernalTierScaling;

@Mixin(InfernalMobsCore.class)
public abstract class MixinInfernalRarityScaling {

    // InfernalMobsCore singleton instance (InfernalMobsCore.instance)
    @Shadow private static InfernalMobsCore instance;

    // These are *instance* fields on InfernalMobsCore
    @Shadow private int eliteRarity;
    @Shadow private int ultraRarity;
    @Shadow private int infernoRarity;

    @Inject(method = "getMobModifiers", at = @At("HEAD"), remap = false)
    private static void tokcraft$applyDifficultyScaling(EntityLivingBase entity,
            CallbackInfoReturnable<?> cir) {

        if (entity == null || entity.world == null || entity.world.isRemote)
            return;

        // Find nearest player (Scaling Health uses nearest-player difficulty)
        EntityPlayer nearest = entity.world.getClosestPlayerToEntity(entity, 128);
        if (nearest == null)
            return;

        double difficulty = ScalingHealthAPI.getPlayerDifficulty(nearest);
        if (difficulty <= 0)
            return;

        // Compute new rarity divisors from  config
        int elite   = computeDivisorStatic(InfernalTierScaling.increment("rare"), difficulty);
        int ultra   = computeDivisorStatic(InfernalTierScaling.increment("ultra"), difficulty);
        int inferno = computeDivisorStatic(InfernalTierScaling.increment("infernal"), difficulty);

        // Apply to the InfernalMobsCore *instance* 
        InfernalMobsCore core = instance;
        if (core == null)
            return;

        ((MixinInfernalRarityScaling) (Object) core).eliteRarity   = elite;
        ((MixinInfernalRarityScaling) (Object) core).ultraRarity   = ultra;
        ((MixinInfernalRarityScaling) (Object) core).infernoRarity = inferno;
    }

    // Static helper because injector is static
    private static int computeDivisorStatic(double incrementPerDifficulty, double difficulty) {
        final double p = incrementPerDifficulty * difficulty;
        if (p <= 0.0) return 100;
        return Math.max(1, (int) Math.round(1.0 / p));
    }
}
