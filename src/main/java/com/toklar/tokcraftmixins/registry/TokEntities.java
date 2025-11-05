package com.toklar.tokcraftmixins.registry;

import com.toklar.tokcraftmixins.mixin.eyeofdragons.entities.EntityLightningDragonEye;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

@Mod.EventBusSubscriber(modid = "tokcraftmixins")
public final class TokEntities {

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        // compiler can now infer T = EntityLightningDragonEye
        registerUnspawnable(event, EntityLightningDragonEye.class, "eye_of_lightningdragon", 49);
    }

    private static <T extends Entity> void registerUnspawnable(RegistryEvent.Register<EntityEntry> event,
                                                               Class<T> cls,
                                                               String name,
                                                               int id) {
        id += 1500; // keep offset if you want consistency
        EntityEntry entry = EntityEntryBuilder.<T>create()
                .entity(cls)
                .id(new ResourceLocation("tokcraftmixins", name), id)
                .name(name)
                .tracker(64, 1, true)
                .build();
        event.getRegistry().register(entry);
    }

    private TokEntities() {}
}