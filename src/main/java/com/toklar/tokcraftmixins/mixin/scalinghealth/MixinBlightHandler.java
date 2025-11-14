package com.toklar.tokcraftmixins.mixin.scalinghealth;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.silentchaos512.scalinghealth.event.BlightHandler;
import net.silentchaos512.scalinghealth.config.Config;
import net.silentchaos512.scalinghealth.init.ModItems;
import net.silentchaos512.lib.util.ChatHelper;
import net.silentchaos512.scalinghealth.ScalingHealth;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.mcreator.toklar.SummonDamageBuffHandler;

@Mixin(value = BlightHandler.class, remap = false)
public abstract class MixinBlightHandler {

    @Inject(method = "onBlightKilled", at = @At("HEAD"), cancellable = true)
    public void rerouteSummonBlightKill(LivingDeathEvent event, CallbackInfo ci) {
        DamageSource source = event.getSource();
        EntityLivingBase blight = event.getEntityLiving();

        if (source == null || blight == null || !blight.isEntityAlive() || !isBlightEntity(blight) || blight.world.isRemote) return;

        EntityPlayer owner = SummonDamageBuffHandler.resolveValidSummonOwner(source);
        if (owner == null) return;

        // Notify players
        if (Config.Mob.Blight.notifyOnDeath) {
            ScalingHealth.logHelper.info("Blight {} was killed by summon owned by {}", blight.getName(), owner.getName());
            for (EntityPlayer p : owner.world.getPlayers(EntityPlayer.class, e -> true)) {
                ChatHelper.translate(p, ScalingHealth.i18n.getKey("blight", "killedByPlayer"), blight.getName(), owner.getName());
            }
        }

        // Drop hearts
        boolean canGetHearts = (!(owner instanceof net.minecraftforge.common.util.FakePlayer) || Config.FakePlayer.generateHearts);
        int min = Config.Items.Heart.blightMin;
        int max = Config.Items.Heart.blightMax;
        int heartCount = ScalingHealth.random.nextInt(max - min + 1) + min;

        if (canGetHearts && heartCount > 0) {
            Item itemToDrop = Config.Items.Heart.dropShardsInstead ? ModItems.crystalShard : ModItems.heart;
            blight.entityDropItem(new ItemStack(itemToDrop, heartCount), 0.0F);
        }

        // Trigger vanilla kill credit
        owner.onKillEntity(blight);
        ci.cancel(); // Prevent original method from running
    }

    // You may need to reimplement this if it's private in Scaling Health
    private static boolean isBlightEntity(EntityLivingBase entity) {
        return entity.getEntityData().getBoolean("isBlight");
    }
}