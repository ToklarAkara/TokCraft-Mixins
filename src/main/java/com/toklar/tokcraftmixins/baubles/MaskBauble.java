package com.toklar.tokcraftmixins.baubles;

import baubles.api.IBauble;
import baubles.api.BaubleType;
import com.bobmowzie.mowziesmobs.server.item.ItemHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class MaskBauble implements IBauble {

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.HEAD;
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        if (player.world.isRemote) return;

        if (stack.getItem() == ItemHandler.BARAKOA_MASK_FURY) {
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 20, 0, true, false));
        }
        if (stack.getItem() == ItemHandler.BARAKOA_MASK_FEAR) {
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 20, 0, true, false));
        }
        if (stack.getItem() == ItemHandler.BARAKOA_MASK_RAGE) {
            player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 20, 0, true, false));
        }
        if (stack.getItem() == ItemHandler.BARAKOA_MASK_BLISS) {
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 20, 0, true, false));
        }
        if (stack.getItem() == ItemHandler.BARAKOA_MASK_MISERY) {
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 20, 0, true, false));
        }
    }


    @Override
    public void onEquipped(ItemStack stack, EntityLivingBase player) {}

    @Override
    public void onUnequipped(ItemStack stack, EntityLivingBase player) {}
}
