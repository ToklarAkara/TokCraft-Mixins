//package com.toklar.tokcraftmixins.ctsetbonus;
//
//import com.mahghuuuls.ctsetbonus.zen.SetTweaks;
//import com.mahghuuuls.ctsetbonus.ScriptLoader;
//import com.mahghuuuls.ctsetbonus.util.ParseUtil;
//import com.mahghuuuls.ctsetbonus.util.ServerDataUtil;
//import com.mahghuuuls.ctsetbonus.SetTweaksCore;
//import crafttweaker.annotations.ZenRegister;
//import stanhebben.zenscript.annotations.ZenClass;
//import stanhebben.zenscript.annotations.ZenMethod;
//import stanhebben.zenscript.value.IAny;
//
//@ZenRegister
//@ZenClass("ctsetbonus.SetTweaks")
//public class SetTweaksExpansion {
//
//    // ADD EQUIP TO SET
//
//    @ZenMethod
//    public static void addEquipToSet(String setName, String slot, String equipRL) {
//        ScriptLoader.enqueue(() -> {
//            ServerDataUtil.addEquip(equipRL);
//            String equipId = ServerDataUtil.getEquip(equipRL).id;
//            SetTweaksCore.addEquipToSetCore(setName, slot, equipId);
//        });
//    }
//
//    @ZenMethod
//    public static void addEquipToSet(String setName, int slot, String equipRL) {
//        ScriptLoader.enqueue(() -> {
//            ServerDataUtil.addEquip(equipRL);
//            String equipId = ServerDataUtil.getEquip(equipRL).id;
//            SetTweaksCore.addEquipToSetCore(setName, Integer.toString(slot), equipId);
//        });
//    }
//
//    @ZenMethod
//    public static void addEquipToSet(String setName, String slot, String[] equipsRL) {
//        for (String equipRL : equipsRL) {
//            ScriptLoader.enqueue(() -> {
//                ServerDataUtil.addEquip(equipRL);
//                String equipId = ServerDataUtil.getEquip(equipRL).id;
//                SetTweaksCore.addEquipToSetCore(setName, slot, equipId);
//            });
//        }
//    }
//
//    @ZenMethod
//    public static void addEquipToSet(String setName, int slot, String[] equipsRL) {
//        for (String equipRL : equipsRL) {
//            ScriptLoader.enqueue(() -> {
//                ServerDataUtil.addEquip(equipRL);
//                String equipId = ServerDataUtil.getEquip(equipRL).id;
//                SetTweaksCore.addEquipToSetCore(setName, Integer.toString(slot), equipId);
//            });
//        }
//    }
//
//    // ADD BONUS TO SET
//
//    @ZenMethod
//    public static void addSetReqToBonus(String bonusName, String bonusDescription, String setName) {
//        ScriptLoader.enqueue(() -> SetTweaksCore.addSetReqToBonusCore(bonusName, bonusDescription, setName, -1, 1));
//    }
//
//    @ZenMethod
//    public static void addSetReqToBonus(String bonusName, String bonusDescription, String setName, int numberOfParts) {
//        ScriptLoader.enqueue(() -> SetTweaksCore.addSetReqToBonusCore(bonusName, bonusDescription, setName, numberOfParts, 1));
//    }
//
//    @ZenMethod
//    public static void addSetReqToBonus(String bonusName, String bonusDescription, String setName, int numberOfParts, int discoveryMode) {
//        ScriptLoader.enqueue(() -> SetTweaksCore.addSetReqToBonusCore(bonusName, bonusDescription, setName, numberOfParts, discoveryMode));
//    }
//
//    // ADD POTION EFFECT ELEMENT TO BONUS
//
//    @ZenMethod
//    public static void addPotionEffectToBonus(String bonusName, String effectRL, int level) {
//        ScriptLoader.enqueue(() -> SetTweaksCore.addPotionEffectToBonusCore(bonusName, effectRL, level, Integer.MAX_VALUE, 0));
//    }
//
//    @ZenMethod
//    public static void addPotionEffectToBonus(String bonusName, String effectRL, int level, int duration, int interval) {
//        ScriptLoader.enqueue(() -> SetTweaksCore.addPotionEffectToBonusCore(bonusName, effectRL, level, duration, interval));
//    }
//
//    // ADD ATTRIBUTE MOD ELEMENT TO BONUS
//
//    @ZenMethod
//    public static void addAttributeModToBonus(String bonusName, String attribute, double amount, String operation) {
//        int operationCode = ParseUtil.parseOperation(operation);
//        ScriptLoader.enqueue(() -> SetTweaksCore.addAttributeModToBonusCore(bonusName, attribute, amount, operationCode));
//    }
//
//    // ADD ENCHANTMENT ELEMENT TO BONUS
//
//    @ZenMethod
//    public static void addEnchantmentToBonus(String bonusName, String slot, String equipRL, String enchantRL, int level) {
//        ScriptLoader.enqueue(() -> SetTweaksCore.addEnchantmentToBonusCore(bonusName, slot, equipRL, enchantRL, level, 0));
//    }
//
//    @ZenMethod
//    public static void addEnchantmentToBonus(String bonusName, String slot, String equipRL, String enchantRL, int level, int mode) {
//        ScriptLoader.enqueue(() -> SetTweaksCore.addEnchantmentToBonusCore(bonusName, slot, equipRL, enchantRL, level, mode));
//    }
//
//    @ZenMethod
//    public static void addEnchantmentToBonus(String bonusName, String slot, String equipRL, String enchantRL, int level, String mode) {
//        int modeCode = ParseUtil.parseOperation(mode);
//        ScriptLoader.enqueue(() -> SetTweaksCore.addEnchantmentToBonusCore(bonusName, slot, equipRL, enchantRL, level, modeCode));
//    }
//}