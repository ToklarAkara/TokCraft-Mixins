package com.toklar.tokcraftmixins.baubles;

import baubles.api.cap.IBaublesItemHandler;
import baubles.api.cap.BaublesCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MaskBaubleProvider implements ICapabilitySerializable<NBTTagCompound> {

    private final MaskBauble bauble = new MaskBauble();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable net.minecraft.util.EnumFacing facing) {
        return capability == BaublesCapabilities.CAPABILITY_ITEM_BAUBLE;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable net.minecraft.util.EnumFacing facing) {
        if (capability == BaublesCapabilities.CAPABILITY_ITEM_BAUBLE) {
            return BaublesCapabilities.CAPABILITY_ITEM_BAUBLE.cast(bauble);
        }
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return new NBTTagCompound();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
    }
}
