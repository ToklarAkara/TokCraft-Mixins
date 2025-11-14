package com.toklar.tokcraftmixins.mixin.champions;

import c4.champions.common.EventHandlerCommon;
import c4.champions.common.capability.CapabilityChampionship;
import c4.champions.common.capability.IChampionship;
import c4.champions.common.config.ConfigHandler;
import c4.champions.common.util.ChampionHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.mcreator.toklar.SummonDamageBuffHandler;

@Mixin(value = EventHandlerCommon.class, remap = false)
public abstract class MixinEventHandlerCommon {

    @Inject(method = "livingDrops", at = @At("HEAD"), cancellable = true)
    private void rerouteSummonLoot(LivingDropsEvent evt, CallbackInfo ci) {
        DamageSource source = evt.getSource();
        EntityLivingBase entity = evt.getEntityLiving();

        // Skip if not a Champion
        if (!ChampionHelper.isValidChampion(entity)) return;

        // Skip if not WorldServer
        if (!(entity.world instanceof WorldServer)) return;

        // Skip if doMobLoot is false
        if (!entity.world.getGameRules().getBoolean("doMobLoot")) return;

        // Skip if fake player and config disallows
        if (!ConfigHandler.lootFake && source.getTrueSource() instanceof net.minecraftforge.common.util.FakePlayer) return;

        // Try to resolve summon owner
        EntityPlayer owner = SummonDamageBuffHandler.resolveValidSummonOwner(source);
        if (owner == null) return;

        // Valid summon kill — rerun Champions loot logic with owner as killer
        IChampionship chp = CapabilityChampionship.getChampionship((EntityLiving) entity);
        if (chp == null || !ChampionHelper.isElite(chp.getRank())) return;

        WorldServer world = (WorldServer) entity.world;

        if (ConfigHandler.lootSource != ConfigHandler.LootSource.CONFIG) {
            LootTable table = world.getLootTableManager().getLootTableFromLocation(new ResourceLocation("champions", "champion_loot"));
            LootContext.Builder builder = new LootContext.Builder(world)
                .withLootedEntity(entity)
                .withDamageSource(source)
                .withPlayer(owner)
                .withLuck(owner.getLuck());

            List<ItemStack> stacks = table.generateLootForPools(world.rand, builder.build());
            for (ItemStack stack : stacks) {
                EntityItem drop = new EntityItem(world, entity.posX, entity.posY, entity.posZ, stack);
                drop.setDefaultPickupDelay();
                evt.getDrops().add(drop);
            }
        }

        if (ConfigHandler.lootSource != ConfigHandler.LootSource.LOOT_TABLE) {
            List<ItemStack> loot = ChampionHelper.getLootDrops(chp.getRank().getTier());
            for (ItemStack stack : loot) {
                if (!stack.isEmpty()) {
                    EntityItem drop = new EntityItem(world, entity.posX, entity.posY, entity.posZ, stack);
                    drop.setDefaultPickupDelay();
                    evt.getDrops().add(drop);
                }
            }
        }

        System.out.println("[ChampionMixin] Rerouted loot attribution to owner: " + owner.getName());
        ci.cancel(); // Prevent original method from running
    }
}