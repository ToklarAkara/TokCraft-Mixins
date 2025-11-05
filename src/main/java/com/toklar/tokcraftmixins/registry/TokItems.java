package com.toklar.tokcraftmixins.registry;

import com.toklar.tokcraftmixins.mixin.eyeofdragons.items.ItemLightningDragonEye;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = "tokcraftmixins")
public final class TokItems {
    public static Item LIGHTNING_DRAGON_EYE;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        LIGHTNING_DRAGON_EYE = new ItemLightningDragonEye();
        event.getRegistry().register(LIGHTNING_DRAGON_EYE);
        System.out.println("[TokCraftMixins] Registered LIGHTNING_DRAGON_EYE");
    }

    private TokItems() {}
}