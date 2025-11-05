package com.toklar.tokcraftmixins.registry;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = "tokcraftmixins", value = Side.CLIENT)
public final class TokModels {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(
            TokItems.LIGHTNING_DRAGON_EYE, 0,
            new ModelResourceLocation("tokcraftmixins:eye_of_lightningdragon", "inventory")
        );
        ModelLoader.setCustomModelResourceLocation(
            TokItems.LIGHTNING_DRAGON_EYE, 1,
            new ModelResourceLocation("tokcraftmixins:eye_of_lightningdragon", "inventory")
        );
    }

    private TokModels() {}
}