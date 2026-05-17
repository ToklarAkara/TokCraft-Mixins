package com.toklar.tokcraftmixins.betterend;

import mod.beethoven92.betterendforge.data.InfusionRecipes;
import mod.beethoven92.betterendforge.common.recipes.InfusionRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Field;
import java.util.List;

public class BetterEndInfusionInjector {

    public static void inject() {
        try {
            
            Field recipesField = InfusionRecipes.class.getDeclaredField("recipes");
            recipesField.setAccessible(true);

            @SuppressWarnings("unchecked")
            List<InfusionRecipe> recipes = (List<InfusionRecipe>) recipesField.get(null);

            if (recipes == null) {
                System.err.println("[TokCraftMixins] ERROR: BetterEndForge InfusionRecipes.recipes is null!");
                return;
            }

            InfusionRecipe.Builder.create()
            .setInput(Item.getByNameOrId("minecraft:dragon_breath"))
            .setOutput(new ItemStack(Item.getByNameOrId("toklar:ghastly_potion")))
            .addCatalyst(0, Item.getByNameOrId("lycanitesmobs:demon_lord_relic"))
            .addCatalyst(1, Item.getByNameOrId("minecraft:nether_star"))
            .addCatalyst(2, Item.getByNameOrId("defiledlands:essence_mourner"))
            .addCatalyst(3, Item.getByNameOrId("mowziesmobs:wrought_helmet"))
            .addCatalyst(4, Item.getByNameOrId("mowziesmobs:ice_crystal"))
            .addCatalyst(5, Item.getByNameOrId("mowziesmobs:barako_mask"))
            .addCatalyst(6, Item.getByNameOrId("contenttweaker:sentient_core"))
            .addCatalyst(7, Item.getByNameOrId("defiledlands:essence_destroyer"))
            .setTime(600)
            .build(recipes::add, new ResourceLocation("tokcraftmixins", "ghastly_potion"));



            System.out.println("[TokCraftMixins] Successfully injected Ghastly Potion infusion recipe!");

        } catch (Exception e) {
            System.err.println("[TokCraftMixins] Failed to inject Ghastly Potion infusion recipe:");
            e.printStackTrace();
        }
    }
}
