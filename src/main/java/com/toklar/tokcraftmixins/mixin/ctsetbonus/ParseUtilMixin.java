//package com.toklar.tokcraftmixins.mixin.ctsetbonus;
//
//import com.mahghuuuls.ctsetbonus.util.IdFormatter;
//import com.mahghuuuls.ctsetbonus.util.ParseUtil;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//
//@Mixin(ParseUtil.class)
//public class ParseUtilMixin {
//
//    @Overwrite
//    public static String getParseableEquip(String equipRL) {
//        String[] tokens = equipRL.split(" ", 2);
//        String registryName = tokens[0];
//        String nbtPart = tokens.length > 1 ? tokens[1] : "";
//
//        String equipId = IdFormatter.getEquipIdFromRL(registryName);
//        String filterString = registryName + (nbtPart.isEmpty() ? "" : " " + nbtPart);
//
//        return equipId + ", " + filterString;
//    }
//
//    @Overwrite
//    public static String getParseableSlotData(String equipRL, String slot) {
//        String[] tokens = equipRL.split(" ", 2);
//        String registryName = tokens[0];
//        String equipId = IdFormatter.getEquipIdFromRL(registryName);
//
//        return slot + "=" + equipId;
//    }
//}