//package com.toklar.tokcraftmixins.mixin.ctsetbonus;
//
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//import org.spongepowered.asm.mixin.injection.At;
//import com.mahghuuuls.ctsetbonus.util.IdFormatter;
//
//@Mixin(IdFormatter.class)
//public class IdFormatterMixin {
//
//    @Inject(method = "getEquipIdFromRL", at = @At("HEAD"), cancellable = true, remap = false)
//    private static void fixColonInNBT(String equipRL, CallbackInfoReturnable<String> cir) {
//        String[] parts = equipRL.split("\\|", 2);
//        String itemPart = parts[0].replace(":", "_").replace("@", "_");
//        String nbtPart = parts.length > 1 ? "|" + parts[1] : "";
//        cir.setReturnValue(itemPart + nbtPart);
//    }
//}