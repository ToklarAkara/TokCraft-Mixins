package com.toklar.tokcraftmixins.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;


import net.mcreator.toklar.SummonDamageBuffHandler;

public final class SummonDamageBuffHandlerProxy {

    private SummonDamageBuffHandlerProxy() {}

    public static EntityPlayer resolveValidSummonOwner(DamageSource source) {
        
        return SummonDamageBuffHandler.resolveValidSummonOwner(source);
    }
}