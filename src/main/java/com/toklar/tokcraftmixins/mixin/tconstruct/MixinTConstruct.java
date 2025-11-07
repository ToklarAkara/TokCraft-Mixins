package com.toklar.tokcraftmixins.mixin.tconstruct;

import com.toklar.tokcraftmixins.helpers.BlocklistConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import slimeknights.tconstruct.TConstruct;
import slimeknights.mantle.pulsar.control.PulseManager;
import slimeknights.tconstruct.tools.melee.TinkerMeleeWeapons;
import slimeknights.tconstruct.tools.ranged.TinkerRangedWeapons;
@Mixin(value = TConstruct.class, remap = false)
public abstract class MixinTConstruct {

  @Redirect(
    method = "<clinit>",
    at = @At(
      value = "INVOKE",
      target = "Lslimeknights/mantle/pulsar/control/PulseManager;registerPulse(Ljava/lang/Object;)V"
    )
  )
  private static void redirectRegisterPulse(PulseManager manager, Object pulse) {
    if (pulse instanceof TinkerMeleeWeapons && BlocklistConfig.blockPulse("tconstruct_melee_weapons")) {
      System.out.println("[TokCraftMixins] Blocking TinkerMeleeWeapons pulse");
      return;
    }

    if (pulse instanceof TinkerRangedWeapons && BlocklistConfig.blockPulse("tconstruct_ranged_weapons")) {
      System.out.println("[TokCraftMixins] Blocking TinkerRangedWeapons pulse");
      return;
    }

    manager.registerPulse(pulse); // allow all others
  }
}