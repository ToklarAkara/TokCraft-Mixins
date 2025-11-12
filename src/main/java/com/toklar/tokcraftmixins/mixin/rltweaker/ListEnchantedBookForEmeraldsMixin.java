package com.toklar.tokcraftmixins.mixin.rltweaker;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.entity.IMerchant;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(targets = "net.minecraft.entity.passive.EntityVillager$ListEnchantedBookForEmeralds")
public class ListEnchantedBookForEmeraldsMixin {
    @Inject(method = "addMerchantRecipe", at = @At("HEAD"), cancellable = true)
    private void guardNullRandom(IMerchant merchant, MerchantRecipeList recipeList, Random random, CallbackInfo ci) {
        if (random == null) {
            System.out.println("[TokCraftMixins] Prevented crash: random was null in ListEnchantedBookForEmeralds");
            ci.cancel(); // Skip the method to avoid crash
        }
    }
}