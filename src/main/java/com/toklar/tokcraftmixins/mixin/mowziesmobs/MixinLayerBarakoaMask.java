//package com.toklar.tokcraftmixins.mixin.mowziesmobs;
//
//import baubles.api.BaublesApi;
//import baubles.api.cap.IBaublesItemHandler;
//import com.bobmowzie.mowziesmobs.server.item.ItemBarakoaMask;
//import net.minecraft.client.renderer.entity.RenderLivingBase;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(targets = "com.bobmowzie.mowziesmobs.client.render.entity.layer.LayerBarakoaMask")
//public abstract class MixinLayerBarakoaMask {
//
//    @Inject(method = "doRenderLayer", at = @At("TAIL"))
//    private void tokcraft$renderBaubleMask(EntityLivingBase entity, float limbSwing, float limbSwingAmount,
//                                           float partialTicks, float ageInTicks, float netHeadYaw,
//                                           float headPitch, float scale, CallbackInfo ci) {
//
//        if (!(entity instanceof EntityPlayer)) return;
//        EntityPlayer player = (EntityPlayer) entity;
//
//        IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
//        if (baubles == null) return;
//
//        ItemStack baubleStack = baubles.getStackInSlot(0);
//        if (baubleStack.isEmpty()) return;
//
//        if (!(baubleStack.getItem() instanceof ItemBarakoaMask)) return;
//
//
//        Object selfObj = this;
//        RenderLivingBase<?> renderer;
//
//        try {
//            renderer = (RenderLivingBase<?>) selfObj.getClass()
//                    .getField("renderer")
//                    .get(selfObj);
//        } catch (Exception e) {
//            return; 
//        }
//
//        try {
//            selfObj.getClass()
//                    .getMethod("renderMask", renderer.getMainModel().getClass(), EntityLivingBase.class, float.class)
//                    .invoke(selfObj, renderer.getMainModel(), player, partialTicks);
//        } catch (Exception ignored) {}
//    }
//}
