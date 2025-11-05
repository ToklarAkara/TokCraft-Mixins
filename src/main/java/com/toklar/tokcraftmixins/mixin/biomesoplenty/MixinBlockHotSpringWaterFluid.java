package com.toklar.tokcraftmixins.mixin.biomesoplenty;

import biomesoplenty.common.fluids.blocks.BlockHotSpringWaterFluid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockHotSpringWaterFluid.class)
public class MixinBlockHotSpringWaterFluid {

    @Inject(method = "func_180634_a", at = @At("HEAD"), cancellable = true, remap = false)
    private void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity, CallbackInfo ci) {
        //System.out.println("[TokCraftMixins] Mixin triggered: BlockHotSpringWaterFluid.func_180634_a");

        if (!(entity instanceof EntityLivingBase)) {
           // System.out.println("[TokCraftMixins] Entity is not living: " + entity.getClass().getName());
            return;
        }

        EntityLivingBase living = (EntityLivingBase) entity;
        //System.out.println("[TokCraftMixins] Entity is living: " + living.getName());

        // Cancel original healing logic
        ci.cancel();
       // System.out.println("[TokCraftMixins] Original method cancelled");

        // Apply Regeneration only if not already active
        if (!living.isPotionActive(MobEffects.REGENERATION)) {
            living.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 0));
           // System.out.println("[TokCraftMixins] Regeneration applied to: " + living.getName());
        } else {
           // System.out.println("[TokCraftMixins] Regeneration already active on: " + living.getName());
        }
    }
}