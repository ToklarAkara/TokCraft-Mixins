package com.toklar.tokcraftmixins.registry;

import com.toklar.tokcraftmixins.mixin.eyeofdragons.entities.EntityLightningDragonEye;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class TokRenderers {

    public static void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(
            EntityLightningDragonEye.class,
            manager -> new RenderSnowball<>(manager, TokItems.LIGHTNING_DRAGON_EYE, Minecraft.getMinecraft().getRenderItem())
        );
    }

    private TokRenderers() {}
}