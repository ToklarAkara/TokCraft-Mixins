//package com.toklar.tokcraftmixins.mixin.ctsetbonus;
//
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//import org.spongepowered.asm.mixin.injection.At;
//
//import com.fantasticsource.setbonus.SetBonusData;
//import com.fantasticsource.setbonus.common.bonusrequirements.setrequirement.Equip;
//import com.mahghuuuls.ctsetbonus.util.IdRegistry;
//import com.mahghuuuls.ctsetbonus.util.ParseUtil;
//import com.mahghuuuls.ctsetbonus.util.ServerDataUtil;
//import crafttweaker.CraftTweakerAPI;
//
//@Mixin(ServerDataUtil.class)
//public class ServerDataUtilMixin {
//
//    @Inject(method = "addEquip", at = @At("HEAD"), cancellable = true, remap = false)
//    private static void injectNBT(String equipRL, CallbackInfo ci) {
//        String parseableEquip = ParseUtil.getParseableEquip(equipRL);
//        Equip createdEquip = Equip.getInstance(parseableEquip);
//
//        if (createdEquip == null) {
//            CraftTweakerAPI.logError("CTSetBonus: Failed to parse equipRL: " + equipRL);
//            ci.cancel(); // still skip original method
//            return;
//        }
//
//        SetBonusData.SERVER_DATA.equipment.add(createdEquip);
//        IdRegistry.addEquip(equipRL);
//        CraftTweakerAPI.logInfo("CTSetBonus: Registered equip with NBT: " + parseableEquip);
//
//        ci.cancel(); // skip original method
//    }
//}