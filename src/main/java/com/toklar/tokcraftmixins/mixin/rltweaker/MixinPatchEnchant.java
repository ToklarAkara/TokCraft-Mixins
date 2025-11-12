package com.toklar.tokcraftmixins.mixin.rltweaker;

import com.charles445.rltweaker.asm.patch.PatchEnchant;
import meldexun.asmutil2.ASMUtil;
import meldexun.asmutil2.IClassTransformerRegistry;
import net.minecraftforge.fml.common.Loader;

import java.util.NoSuchElementException;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PatchEnchant.class)
public class MixinPatchEnchant {
	static {
	    System.out.println("[TokCraftMixins] Mixin loaded: EnchantedBooks");
	}
	@Inject(method = "registerTransformers", at = @At("HEAD"), cancellable = true, remap = false)
	private static void disableOriginal(IClassTransformerRegistry registry, CallbackInfo ci) {
	    if (!Loader.isModLoaded("rltweaker")) return;

	    ci.cancel();

        // 1. Villager trades: replace getRandomObject with getRandomRestricted
        registry.add("net.minecraft.entity.passive.EntityVillager$ListEnchantedBookForEmeralds", 1, clazzNode -> {
            MethodNode m = ASMUtil.findObf(clazzNode, "func_190888_a", "addMerchantRecipe");
            if (m == null) throw new RuntimeException("Couldn't find addMerchantRecipe");

            MethodInsnNode target;
            try {
                target = (MethodInsnNode) ASMUtil.first(m)
                    .opcode(Opcodes.INVOKEVIRTUAL)
                    .methodInsnObf("func_186801_a", "getRandomObject")
                    .find();
            } catch (NoSuchElementException e) {
                throw new RuntimeException("getRandomObject not found in addMerchantRecipe", e);
            }

            target.setOpcode(Opcodes.INVOKESTATIC);
            target.owner = "com/charles445/rltweaker/hook/HookEnchant";
            target.name = "getRandomRestricted";
            target.desc = "(Ljava/lang/Object;Ljava/util/Random;)Lnet/minecraft/enchantment/Enchantment;";
        });

        // 2. EnchantmentHelper: inject restrictEnchantmentDatas before ARETURN
        registry.add("net.minecraft.enchantment.EnchantmentHelper", 1, clazzNode -> {
            MethodNode m = ASMUtil.findObf(clazzNode, "func_185291_a", "getEnchantmentDatas");
            if (m == null) throw new RuntimeException("Couldn't find getEnchantmentDatas");

            AbstractInsnNode aret;
            try {
                aret = ASMUtil.last(m).opcode(Opcodes.ARETURN).find();
            } catch (NoSuchElementException e) {
                throw new RuntimeException("Couldn't find ARETURN in getEnchantmentDatas", e);
            }

            m.instructions.insertBefore(aret, new MethodInsnNode(
                Opcodes.INVOKESTATIC,
                "com/charles445/rltweaker/hook/HookEnchant",
                "restrictEnchantmentDatas",
                "(Ljava/util/List;)Ljava/util/List;",
                false
            ));
        });

        // 3. Loot function: replace List.add with addEnchantmentRestricted
        registry.add("net.minecraft.world.storage.loot.functions.EnchantRandomly", 1, clazzNode -> {
            MethodNode m = ASMUtil.findObf(clazzNode, "func_186553_a", "apply");
            if (m == null) throw new RuntimeException("Couldn't find apply");

            AbstractInsnNode anchor;
            try {
                anchor = ASMUtil.first(m)
                    .opcode(Opcodes.INVOKEVIRTUAL)
                    .methodInsnObf("func_92089_a", "canApply")
                    .find();
            } catch (NoSuchElementException e) {
                throw new RuntimeException("Couldn't find canApply", e);
            }

            MethodInsnNode addNode;
            try {
                addNode = (MethodInsnNode) ASMUtil.next(m, anchor)
                    .opcode(Opcodes.INVOKEINTERFACE)
                    .methodInsn("add")
                    .find();
            } catch (NoSuchElementException e) {
                throw new RuntimeException("Couldn't find List.add", e);
            }

            if (!"java/util/List".equals(addNode.owner))
                throw new RuntimeException("Unexpected add target: " + addNode.owner);

            addNode.setOpcode(Opcodes.INVOKESTATIC);
            addNode.owner = "com/charles445/rltweaker/hook/HookEnchant";
            addNode.name = "addEnchantmentRestricted";
            addNode.desc = "(Ljava/util/List;Lnet/minecraft/enchantment/Enchantment;)Z";
            addNode.itf = false;
        });
    }
}