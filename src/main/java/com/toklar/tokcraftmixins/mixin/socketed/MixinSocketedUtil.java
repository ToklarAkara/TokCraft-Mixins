package com.toklar.tokcraftmixins.mixin.socketed;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.item.ItemStack;
import socketed.Socketed;
import socketed.common.config.ForgeConfig;
import socketed.api.util.SocketedUtil;

@Mixin(SocketedUtil.class)
public class MixinSocketedUtil {

    @Inject(method = "canStackHaveSockets", at = @At("HEAD"), cancellable = true, remap = false)
    private static void injectCanStackHaveSockets(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack == null || stack.isEmpty()) {
            cir.setReturnValue(false);
            return;
        }

        if (!SocketedUtil.hasCompletedLoading(true)) {
            cir.setReturnValue(false);
            return;
        }

        try {
            cir.setReturnValue(ForgeConfig.SOCKETABLES.canSocket(stack));
        } catch (Exception e) {
            Socketed.LOGGER.warn("Capability check failed for item: " + stack.getItem(), e);
            cir.setReturnValue(false);
        }
    }
}