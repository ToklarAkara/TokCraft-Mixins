package com.toklar.tokcraftmixins.mixin.nyx;

import net.darkhax.eplus.block.BlockAdvancedTable;
import de.impelon.disenchanter.block.BlockDisenchantmentTable;
import de.ellpeck.nyx.events.Events;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Events.class)
public class MixinNyxEnchantingRestriction {

    @Inject(
        method = "onInteract",
        at = @At("TAIL"),
        remap = false
    )
    private static void tokcraft_extendNyxEnchanting(PlayerInteractEvent.RightClickBlock event, CallbackInfo ci) {

        Object block = event.getWorld().getBlockState(event.getPos()).getBlock();

        if (block instanceof BlockAdvancedTable
         || block instanceof BlockDisenchantmentTable) {

            long time = event.getWorld().getWorldTime() % 24000L;
            boolean isDay = (time <= 13000L || time >= 23000L);

            if (isDay) {
                event.setUseBlock(PlayerInteractEvent.Result.DENY);

                EntityPlayer player = event.getEntityPlayer();
                player.sendStatusMessage(
                    new TextComponentTranslation("info.nyx.day_enchanting"),
                    true
                );
            }
        }
    }
}
