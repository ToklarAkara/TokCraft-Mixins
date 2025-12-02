package com.toklar.tokcraftmixins.mixin.ebwizardry;


import electroblob.wizardry.block.BlockImbuementAltar;
import net.minecraft.block.state.IBlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockImbuementAltar.class)
public abstract class MixinBlockImbuementAltar {

    // Run after the constructor to adjust hardness and harvest level
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        BlockImbuementAltar self = (BlockImbuementAltar)(Object)this;
        // Set hardness to something reasonable (e.g. stone)
        self.setHardness(3.0F);
        // Require iron pickaxe (harvest level 1)
        self.setHarvestLevel("pickaxe", 1);
    }
}