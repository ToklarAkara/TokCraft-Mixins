//package com.toklar.tokcraftmixins.mixin.mowziesmobs;
//
//import baubles.api.BaublesApi;
//import baubles.api.cap.IBaublesItemHandler;
//import com.bobmowzie.mowziesmobs.server.item.ItemBarakoaMask;
//import com.bobmowzie.mowziesmobs.server.item.ItemHandler;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.init.MobEffects;
//import net.minecraft.item.ItemStack;
//import net.minecraft.potion.PotionEffect;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(EntityPlayer.class)
//public abstract class MixinItemBarakoaMask {
//
//    @Inject(method = "onUpdate", at = @At("TAIL"))
//    private void tokcraft$applyMaskBaubleEffects(CallbackInfo ci) {
//        EntityPlayer player = (EntityPlayer)(Object)this;
//
//        if (player.world.isRemote) return;
//
//        IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
//        if (baubles == null) return;
//
//        ItemStack stack = baubles.getStackInSlot(0);
//        if (stack.isEmpty()) return;
//
//        if (!(stack.getItem() instanceof ItemBarakoaMask)) return;
//
//
//        if (stack.getItem() == ItemHandler.BARAKOA_MASK_BLISS) {
//            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 20, 0, true, false));
//        }
//        if (stack.getItem() == ItemHandler.BARAKOA_MASK_FEAR) {
//            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 20, 1, true, false));
//        }
//        if (stack.getItem() == ItemHandler.BARAKOA_MASK_FURY) {
//            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 20, 1, true, false));
//        }
//        if (stack.getItem() == ItemHandler.BARAKOA_MASK_MISERY) {
//            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 20, 1, true, false));
//        }
//        if (stack.getItem() == ItemHandler.BARAKOA_MASK_RAGE) {
//            player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 20, 0, true, false));
//        }
//    }
//}
