package com.toklar.tokcraftmixins.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.item.ItemStack;

public final class SocketBlacklist {
    // Load from your mixin config at startup
    private static final Set<String> BLACKLIST = new HashSet<>();

    public static void loadFromConfig(List<String> entries) {
        BLACKLIST.clear();
        BLACKLIST.addAll(entries);
    }

    public static boolean isBlacklisted(ItemStack stack) {
        if (stack == null || stack.isEmpty() || stack.getItem().getRegistryName() == null) {
            return false;
        }
        String id = stack.getItem().getRegistryName().toString();
        return BLACKLIST.contains(id);
    }
}