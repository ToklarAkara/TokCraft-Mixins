package com.toklar.tokcraftmixins.mixin.infernalmobs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import atomicstryker.infernalmobs.common.InfernalMobsCore;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.silentchaos512.scalinghealth.api.ScalingHealthAPI;

import com.toklar.tokcraftmixins.config.InfernalTierScaling;

@Mixin(InfernalMobsCore.class)
public abstract class MixinInfernalRarityScaling {

    @Shadow private int eliteRarity;
    @Shadow private int ultraRarity;
    @Shadow private int infernoRarity;

    @Inject(method = "processEntitySpawn", at = @At("HEAD"), remap = false)
    private void tokcraft$overwriteRarity(EntityLivingBase entity, CallbackInfo ci) {
        if (entity == null || entity.world.isRemote) return;

        double difficulty = Double.NaN;
        if (entity instanceof EntityPlayer) {
            difficulty = ScalingHealthAPI.getPlayerDifficulty((EntityPlayer) entity);
        }

        if (Double.isNaN(difficulty)) return; // no player difficulty available

        eliteRarity   = computeDivisor(InfernalTierScaling.increment("rare"), difficulty);
        ultraRarity   = computeDivisor(InfernalTierScaling.increment("ultra"), difficulty);
        infernoRarity = computeDivisor(InfernalTierScaling.increment("infernal"), difficulty);
    }

    private int computeDivisor(double incrementPerDifficulty, double difficulty) {
        final double p = incrementPerDifficulty * difficulty;
        if (p <= 0.0) return 100;
        return Math.max(1, (int) Math.round(1.0 / p));
    }
}