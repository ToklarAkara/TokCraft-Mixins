package com.toklar.tokcraftmixins.mixin.socketed;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.function.Predicate;

@Mixin(socketed.common.config.SocketableConfig.class)
public class MixinSocketableConfig {

    @Inject(method = "<clinit>", at = @At("TAIL"), remap = false)
    private static void patchDefaultItemTypes(CallbackInfo ci) {
        try {
            Field typesField = Class.forName("socketed.common.config.SocketableConfig")
                .getDeclaredField("defaultItemTypes");
            typesField.setAccessible(true);

            @SuppressWarnings("unchecked")
            Map<String, Object> defaultItemTypes = (Map<String, Object>) typesField.get(null);

            defaultItemTypes.put("HELMET", newSocketableType(item ->
                item instanceof ItemArmor && ((ItemArmor) item).getEquipmentSlot() == EntityEquipmentSlot.HEAD));
            defaultItemTypes.put("CHESTPLATE", newSocketableType(item ->
                item instanceof ItemArmor && ((ItemArmor) item).getEquipmentSlot() == EntityEquipmentSlot.CHEST));
            defaultItemTypes.put("LEGGINGS", newSocketableType(item ->
                item instanceof ItemArmor && ((ItemArmor) item).getEquipmentSlot() == EntityEquipmentSlot.LEGS));
            defaultItemTypes.put("BOOTS", newSocketableType(item ->
                item instanceof ItemArmor && ((ItemArmor) item).getEquipmentSlot() == EntityEquipmentSlot.FEET));

            defaultItemTypes.put("SWORD", newSocketableType(item -> item instanceof ItemSword));
            defaultItemTypes.put("FISHING_ROD", newSocketableType(item -> item instanceof ItemFishingRod));
            defaultItemTypes.put("BOW", newSocketableType(item -> item instanceof ItemBow));
            defaultItemTypes.put("AXE", newSocketableType(item -> item instanceof ItemAxe));
            defaultItemTypes.put("PICKAXE", newSocketableType(item -> item instanceof ItemPickaxe));
            defaultItemTypes.put("HOE", newSocketableType(item -> item instanceof ItemHoe));
            defaultItemTypes.put("SHOVEL", newSocketableType(item -> item instanceof ItemSpade));
            defaultItemTypes.put("SHIELD", newSocketableType(item -> item instanceof ItemShield));

        } catch (Exception e) {
            System.err.println("[tokcraftmixins] Failed to patch defaultItemTypes: " + e);
        }
    }

    private static Object newSocketableType(Predicate<Item> predicate) throws Exception {
        Class<?> socketableTypeClass = Class.forName("socketed.common.config.SocketableConfig$SocketableType");
        return socketableTypeClass.getConstructor(Predicate.class).newInstance(predicate);
    }
}