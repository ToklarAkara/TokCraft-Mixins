package com.toklar.tokcraftmixins.helpers;

import java.util.Arrays;

import net.minecraftforge.common.config.Configuration;

public class BlocklistConfig {
	  private static Configuration config;

	  public static void init(Configuration cfg) {
	    config = cfg;
	  }

	  public static boolean blockPulse(String key) {
	    return config.getBoolean(key, "pulses", false, "Block this TConstruct pulse");
	  }
	  public static boolean blockToolPart(String name) {
		  // Auto-block melee parts if melee pulse is blocked
		  if (isMeleePart(name) && blockPulse("tconstruct_melee_weapons")) return true;

		  // Auto-block ranged parts if ranged pulse is blocked
		  if (isRangedPart(name) && blockPulse("tconstruct_ranged_weapons")) return true;

		  // Optional: allow manual override
		  return config.getBoolean(name, "toolparts", false, "Block this tool part");
		}

		private static boolean isMeleePart(String name) {
		  return Arrays.asList(
		    "sword_blade", "large_sword_blade", "knife_blade",
		    "wide_guard", "hand_guard", "cross_guard"
		  ).contains(name);
		}

		private static boolean isRangedPart(String name) {
		  return Arrays.asList(
		    "bow_limb", "bow_string", "arrow_head",
		    "arrow_shaft", "fletching", "bolt_core"
		  ).contains(name);
		}
	}