package com.toklar.tokcraftmixins.baubles;

import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class MaskBaubleEventHandler {

    private static final ResourceLocation BAUBLE_KEY =
            new ResourceLocation("tokcraftmixins", "mask_bauble");

    // Item IDs for the five masks
    private static final String[] MASK_IDS = new String[] {
            "mowziesmobs:barakoa_mask_fury",
            "mowziesmobs:barakoa_mask_fear",
            "mowziesmobs:barakoa_mask_rage",
            "mowziesmobs:barakoa_mask_bliss",
            "mowziesmobs:barakoa_mask_misery"
    };

    @SubscribeEvent
    public void onAttachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        ItemStack stack = event.getObject();
        Item item = stack.getItem();

        String id = item.getRegistryName().toString();

        for (String maskId : MASK_IDS) {
            if (id.equals(maskId)) {
                event.addCapability(BAUBLE_KEY, new MaskBaubleProvider());
                break;
            }
        }
    }
}
