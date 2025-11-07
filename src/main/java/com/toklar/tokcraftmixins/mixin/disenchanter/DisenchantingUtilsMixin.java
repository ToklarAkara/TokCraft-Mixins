package com.toklar.tokcraftmixins.mixin.disenchanter;

import de.impelon.disenchanter.DisenchantingUtils;
import de.impelon.disenchanter.DisenchanterConfig;
import de.impelon.disenchanter.DisenchantingProperties;
import de.impelon.disenchanter.block.TableVariant;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;

@Mixin(value = DisenchantingUtils.class, remap = false)
public class DisenchantingUtilsMixin {

	@Inject(
		    method = "disenchant(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;ZILde/impelon/disenchanter/DisenchantingProperties;FLjava/util/Random;)Z",
		    at = @At("HEAD"),
		    cancellable = true,
		    remap = false
		)
    private static void injectDisenchant(ItemStack source, ItemStack target, boolean ignoreEnchantmentLoss, int startIndex, DisenchantingProperties properties, float power, Random random, CallbackInfoReturnable<Boolean> cir) {
        int flatDmg = DisenchanterConfig.disenchanting.flatDamage;
        double durabilityDmg = DisenchanterConfig.disenchanting.maxDurabilityDamage;
        double reducibleDmg = DisenchanterConfig.disenchanting.maxDurabilityDamageReducible;
        double dmgMultiplier = properties.is(TableVariant.AUTOMATIC) ? DisenchanterConfig.disenchanting.machineDamageMultiplier : 1.0D;
        boolean hasTransferredEnchantments = false;
        List<Integer> indices;

        while (!(indices = DisenchantingUtils.getAvailableEnchantmentIndices(source)).isEmpty()) {
            int index = Math.abs(indices.get(startIndex % indices.size()));
            if (!DisenchantingUtils.transferEnchantment(source, target, index, ignoreEnchantmentLoss, power, random))
                break;

            hasTransferredEnchantments = true;

            source.attemptDamageItem(
            	    (int)(dmgMultiplier * (
            	        flatDmg +
            	        source.getMaxDamage() * durabilityDmg +
            	        source.getMaxDamage() * reducibleDmg * (1.0F - 0.75F * (power - 1) / 14.0F)
            	    )),
            	    random,
            	    null
            	);

            
            if (DisenchantingUtils.shouldBreakSource(source) || !properties.is(TableVariant.BULKDISENCHANTING))
                break;
        }

        cir.setReturnValue(hasTransferredEnchantments);
    }
    
	@Overwrite
	public static boolean isItemStackBroken(ItemStack itemstack) {
	    return itemstack.isItemStackDamageable() && itemstack.getItemDamage() >= itemstack.getMaxDamage();
	}
}