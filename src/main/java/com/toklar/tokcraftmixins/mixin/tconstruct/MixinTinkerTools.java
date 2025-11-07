package com.toklar.tokcraftmixins.mixin.tconstruct;

import com.toklar.tokcraftmixins.helpers.BlocklistConfig;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import slimeknights.tconstruct.library.tools.ToolPart;
import slimeknights.tconstruct.tools.TinkerTools;
import com.toklar.tokcraftmixins.helpers.ItemDummy;


@Mixin(value = TinkerTools.class, remap = false)
public abstract class MixinTinkerTools {

	private static final Item dummyPart = new ItemDummy();
	@Redirect(
			  method = "registerToolParts",
			  at = @At(
			    value = "INVOKE",
			    target = "Lslimeknights/tconstruct/tools/TinkerTools;registerToolPart(Lnet/minecraftforge/fml/common/registry/IForgeRegistry;Lslimeknights/tconstruct/library/tools/ToolPart;Ljava/lang/String;)Lnet/minecraft/item/Item;"
			  )
			)
			private Item redirectRegisterToolPart(TinkerTools self, IForgeRegistry<Item> registry, ToolPart part, String name) {
			  if (BlocklistConfig.blockToolPart(name)) {
			    System.out.println("[TokCraftMixins] Blocking tool part: " + name);
			    return dummyPart;
			  }

			  
			  part.setRegistryName(name);
			  registry.register(part);
			  return part;
			}
}