package com.toklar.tokcraftmixins.mixin.kgears;

import com.keletu.kgears.AbilityHandler;
import com.keletu.kgears.item.ItemArmorEnderDragonScale;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = AbilityHandler.class, remap = false)
public abstract class MixinAbilityHandler {

    @Inject(method = "onPlayerHurt", at = @At("HEAD"), cancellable = true)
    private void replaceDragonDamage(LivingHurtEvent event, CallbackInfo ci) {
        EntityLivingBase e = event.getEntityLiving();

        // Only run on server
        if (e.world.isRemote) {
            ci.cancel();
            return;
        }

        // Armor checks
        boolean head  = e.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem()  instanceof ItemArmorEnderDragonScale;
        boolean chest = e.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemArmorEnderDragonScale;
        boolean legs  = e.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem()  instanceof ItemArmorEnderDragonScale;
        boolean boots = e.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemArmorEnderDragonScale;

        String src = event.getSource().damageType;

        // Only apply nerfed dragon damage reduction
        if (src.equals("dragon_fire") || src.equals("dragon_ice") || src.equals("dragon_lightning")) {

            float mul = 1.0F;

            // 10% per piece, max 40%
            if (head)  mul -= 0.10F;
            if (chest) mul -= 0.10F;
            if (legs)  mul -= 0.10F;
            if (boots) mul -= 0.10F;

            // Cap at 40% reduction (60% damage taken)
            if (mul < 0.60F)
                mul = 0.60F;

            event.setAmount(event.getAmount() * mul);
        }

        // Fully replace original method (removes chestplate invincibility)
        ci.cancel();
    }
}

