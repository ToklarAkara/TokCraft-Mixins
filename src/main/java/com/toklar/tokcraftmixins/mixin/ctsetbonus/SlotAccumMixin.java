//package com.toklar.tokcraftmixins.mixin.ctsetbonus;
//
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//
//import com.fantasticsource.setbonus.common.bonusrequirements.setrequirement.Equip;
//import com.mahghuuuls.ctsetbonus.slotaccumulator.SlotAccum;
//import com.mahghuuuls.ctsetbonus.util.ServerDataUtil;
//
//@Mixin(SlotAccum.class)
//public class SlotAccumMixin {
//    @Inject(method = "addEquip", at = @At("HEAD"), cancellable = true, remap = false)
//    private void injectAddEquip(String equipRL, CallbackInfoReturnable<Boolean> cir) {
//        Equip equip = ServerDataUtil.getEquip(equipRL);
//        if (equip == null) {
//            ServerDataUtil.addEquip(equipRL);
//            equip = ServerDataUtil.getEquip(equipRL);
//        }
//
//        if (equip == null) {
//            // Log or fail gracefully
//            cir.setReturnValue(false);
//            return;
//        }
//
//        SlotAccum self = (SlotAccum) (Object) this;
//        boolean added = self.equipIds.add(equip.id);
//        cir.setReturnValue(added);
//    }
//}