//package com.toklar.tokcraftmixins.betterend;
//
//import mod.beethoven92.betterendforge.data.InfusionRecipes;
//import mod.beethoven92.betterendforge.common.recipes.InfusionRecipe;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.ResourceLocation;
//
//import java.lang.reflect.Field;
//import java.util.List;
//
//public class BetterEndInfusionInjector {
//
////    public static void inject() {
////        try {
////
////            Field recipesField = InfusionRecipes.class.getDeclaredField("recipes");
////            recipesField.setAccessible(true);
////
////            @SuppressWarnings("unchecked")
////            List<InfusionRecipe> recipes = (List<InfusionRecipe>) recipesField.get(null);
////
////            if (recipes == null) {
////                System.err.println("[TokCraftMixins] ERROR: BetterEndForge InfusionRecipes.recipes is null!");
////                return;
////            }
////
////            String[] colors = {
////                "red", "bronze", "green", "gray", "blue", "white",
////                "sapphire", "silver", "electric", "amethyst", "copper", "black"
////            };
////
////            // ender dragon helmet recipes
////            for (String color : colors) {
////                InfusionRecipe.Builder.create()
////                    .setInput(Item.getByNameOrId("iceandfire:armor_" + color + "_helmet"))
////                    .setOutput(new ItemStack(Item.getByNameOrId("kgears:ultimate_helmet")))
////                    .addCatalyst(0, Item.getByNameOrId("kgears:dragonscales_ender"))
////                    .addCatalyst(1, Item.getByNameOrId("kgears:dragonscales_ender"))
////                    .addCatalyst(2, Item.getByNameOrId("kgears:dragonscale_ender"))
////                    .addCatalyst(3, Item.getByNameOrId("kgears:dragonscale_ender"))
////                    .addCatalyst(4, Item.getByNameOrId("kgears:dragonscale_ender"))
////                    .addCatalyst(5, Item.getByNameOrId("minecraft:totem_of_undying"))
////                    .addCatalyst(6, Item.getByNameOrId("minecraft:totem_of_undying"))
////                    .addCatalyst(7, Item.getByNameOrId("minecraft:totem_of_undying"))
////                    .setTime(600)
////                    .build(recipes::add, new ResourceLocation("tokcraftmixins", "ultimate_helmet_" + color));
////            }
////
////            // ender dragon chestplate recipes
////            for (String color : colors) {
////                InfusionRecipe.Builder.create()
////                    .setInput(Item.getByNameOrId("iceandfire:armor_" + color + "_chestplate"))
////                    .setOutput(new ItemStack(Item.getByNameOrId("kgears:ultimate_chestplate")))
////                    .addCatalyst(0, Item.getByNameOrId("kgears:dragonscales_ender"))
////                    .addCatalyst(1, Item.getByNameOrId("kgears:dragonscales_ender"))
////                    .addCatalyst(2, Item.getByNameOrId("kgears:dragonscale_ender"))
////                    .addCatalyst(3, Item.getByNameOrId("kgears:dragonscale_ender"))
////                    .addCatalyst(4, Item.getByNameOrId("kgears:dragonscale_ender"))
////                    .addCatalyst(5, Item.getByNameOrId("kgears:dragonscale_ender"))
////                    .addCatalyst(6, Item.getByNameOrId("minecraft:end_crystal"))
////                    .addCatalyst(7, Item.getByNameOrId("minecraft:elytra"))
////                    .setTime(600)
////                    .build(recipes::add, new ResourceLocation("tokcraftmixins", "ultimate_chestplate_" + color));
////            }
////
////            // ghastly potion recipe
////            InfusionRecipe.Builder.create()
////                .setInput(Item.getByNameOrId("minecraft:dragon_breath"))
////                .setOutput(new ItemStack(Item.getByNameOrId("toklar:ghastly_potion")))
////                .addCatalyst(0, Item.getByNameOrId("lycanitesmobs:demon_lord_relic"))
////                .addCatalyst(1, Item.getByNameOrId("minecraft:nether_star"))
////                .addCatalyst(2, Item.getByNameOrId("defiledlands:essence_mourner"))
////                .addCatalyst(3, Item.getByNameOrId("mowziesmobs:wrought_helmet"))
////                .addCatalyst(4, Item.getByNameOrId("mowziesmobs:ice_crystal"))
////                .addCatalyst(5, Item.getByNameOrId("mowziesmobs:barako_mask"))
////                .addCatalyst(6, Item.getByNameOrId("contenttweaker:sentient_core"))
////                .addCatalyst(7, Item.getByNameOrId("defiledlands:essence_destroyer"))
////                .setTime(600)
////                .build(recipes::add, new ResourceLocation("tokcraftmixins", "ghastly_potion"));
////
////            // ender dragon leggings recipes
////            for (String color : colors) {
////                InfusionRecipe.Builder.create()
////                    .setInput(Item.getByNameOrId("iceandfire:armor_" + color + "_leggings"))
////                    .setOutput(new ItemStack(Item.getByNameOrId("kgears:ultimate_leggings")))
////                    .addCatalyst(0, Item.getByNameOrId("kgears:dragonscales_ender"))
////                    .addCatalyst(1, Item.getByNameOrId("kgears:dragonscales_ender"))
////                    .addCatalyst(2, Item.getByNameOrId("kgears:dragonscales_ender"))
////                    .addCatalyst(3, Item.getByNameOrId("kgears:dragonscales_ender"))
////                    .addCatalyst(4, Item.getByNameOrId("kgears:dragonscale_ender"))
////                    .addCatalyst(5, Item.getByNameOrId("kgears:dragonscale_ender"))
////                    .addCatalyst(6, Item.getByNameOrId("iceandfire:hippocampus_slapper"))
////                    .addCatalyst(7, Item.getByNameOrId("iceandfire:hippocampus_slapper"))
////                    .setTime(600)
////                    .build(recipes::add, new ResourceLocation("tokcraftmixins", "ultimate_leggings_" + color));
////            }
////
////            // ender dragon boots recipes
////            for (String color : colors) {
////                InfusionRecipe.Builder.create()
////                    .setInput(Item.getByNameOrId("iceandfire:armor_" + color + "_boots"))
////                    .setOutput(new ItemStack(Item.getByNameOrId("kgears:ultimate_boots")))
////                    .addCatalyst(0, Item.getByNameOrId("iceandfire:pixie_dust"))
////                    .addCatalyst(1, Item.getByNameOrId("iceandfire:pixie_dust"))
////                    .addCatalyst(2, Item.getByNameOrId("kgears:dragonscale_ender"))
////                    .addCatalyst(3, Item.getByNameOrId("kgears:dragonscale_ender"))
////                    .addCatalyst(4, Item.getByNameOrId("iceandfire:pixie_dust"))
////                    .addCatalyst(5, Item.getByNameOrId("iceandfire:pixie_dust"))
////                    .addCatalyst(6, Item.getByNameOrId("kgears:dragonscale_ender"))
////                    .addCatalyst(7, Item.getByNameOrId("kgears:dragonscale_ender"))
////                    .setTime(600)
////                    .build(recipes::add, new ResourceLocation("tokcraftmixins", "ultimate_boots_" + color));
////            }
////            
////         // ender dragon ultimate scythe recipe
////            InfusionRecipe.Builder.create()
////                .setInput(Item.getByNameOrId("spartanfire:scythe_dragonbone"))
////                .setOutput(new ItemStack(Item.getByNameOrId("kgears:ultimate_scythe")))
////                .addCatalyst(0, Item.getByNameOrId("kgears:dragonscale_ender"))
////                .addCatalyst(1, Item.getByNameOrId("kgears:dragonscale_ender"))
////                .addCatalyst(2, Item.getByNameOrId("kgears:dragonscale_ender"))
////                .addCatalyst(3, Item.getByNameOrId("kgears:dragonscale_ender"))
////                .addCatalyst(4, Item.getByNameOrId("minecraft:nether_star"))
////                .addCatalyst(5, Item.getByNameOrId("iceandfire:hydra_heart"))
////                .addCatalyst(6, Item.getByNameOrId("iceandfire:witherbone"))
////                .addCatalyst(7, Item.getByNameOrId("iceandfire:witherbone"))
////                .setTime(600)
////                .build(recipes::add, new ResourceLocation("tokcraftmixins", "ultimate_scythe"));
////
////
////            System.out.println("[TokCraftMixins] Successfully injected all infusion recipes!");
////
////        } catch (Exception e) {
////            System.err.println("[TokCraftMixins] Failed to inject infusion recipes:");
////            e.printStackTrace();
////        }
////    }
//}
