package com.toklar.tokcraftmixins.mixin.eyeofdragons.entities;

import de.curlybracket.eyeofdragons.EntityEyeBase;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityLightningDragonEye extends EntityEyeBase {
    public EntityLightningDragonEye(World world) {
        super(world);
    }

    public EntityLightningDragonEye(World world, double x, double y, double z, int id) {
        super(world, x, y, z, id);
    }

    @Override
    protected Item getDropItem() {
        return com.toklar.tokcraftmixins.registry.TokItems.LIGHTNING_DRAGON_EYE;
    }

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}
}