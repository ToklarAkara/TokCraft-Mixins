package com.toklar.tokcraftmixins.mixin.lycanitesmobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.lycanitesmobs.core.entity.BaseCreatureEntity;
import com.lycanitesmobs.core.entity.ExtendedEntity;
import com.lycanitesmobs.core.entity.TameableCreatureEntity;
import com.lycanitesmobs.GameEventListener;

@Mixin(GameEventListener.class)
public abstract class MixinGameEventListener {

	@Inject(
		    method = "onLivingHurt(Lnet/minecraftforge/event/entity/living/LivingHurtEvent;)V",
		    at = @At("HEAD"),
		    cancellable = true,
		    remap = false
		)

    private void patchAttribution(LivingHurtEvent event, CallbackInfo ci) {
        if (event.isCanceled() || event.getSource() == null || event.getEntityLiving() == null) {
            return;
        }

        EntityLivingBase damagedEntity = event.getEntityLiving();
        Entity immediate = event.getSource().getImmediateSource();

        if (immediate instanceof BaseCreatureEntity) {
            BaseCreatureEntity creature = (BaseCreatureEntity) immediate;
            EntityPlayer owner = null;

            // Layer 1: tameable creatures
            if (creature instanceof TameableCreatureEntity) {
                owner = ((TameableCreatureEntity) creature).getPlayerOwner();
            }

            // Layer 2: generic BaseCreatureEntity owner
            if (owner == null) {
                Entity possibleOwner = creature.getOwner();
                if (possibleOwner instanceof EntityPlayer) {
                    owner = (EntityPlayer) possibleOwner;
                }
            }

            // Layer 3: master target
            if (owner == null && creature.hasMaster()) {
                Entity master = creature.getMasterTarget();
                if (master instanceof EntityPlayer) {
                    owner = (EntityPlayer) master;
                }
            }

            // If we resolved a player owner, record them instead of the summon
            if (owner != null) {
                ExtendedEntity attackerExt = ExtendedEntity.getForEntity(owner);
                if (attackerExt != null) {
                    attackerExt.setLastAttackedEntity(damagedEntity);
                }
                ci.cancel(); // prevent Lycanites from recording the summon itself
            }
        }
    }
}