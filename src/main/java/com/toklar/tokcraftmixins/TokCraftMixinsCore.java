package com.toklar.tokcraftmixins;

import fermiumbooter.FermiumRegistryAPI;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import org.spongepowered.asm.launch.MixinBootstrap;

import com.toklar.tokcraftmixins.helpers.BlocklistConfig;
import com.toklar.tokcraftmixins.mixin.vanilla.MixinEntityLivingBase;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import com.toklar.tokcraftmixins.AttributionConfig;
import com.toklar.tokcraftmixins.config.InfernalTierScaling;
import com.toklar.tokcraftmixins.config.SocketBlacklist;

@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.Name("TokCraftMixinsCore")
public class TokCraftMixinsCore implements IFMLLoadingPlugin {

    public TokCraftMixinsCore() {
        MixinBootstrap.init();

        Configuration config = new Configuration(new File("config/tokcraftmixins.cfg"));
        config.load();
        org.spongepowered.asm.mixin.Mixins.addConfiguration("mixins.tokcraftmixins.vanilla.json");
        // toncstruct settings
        config.getBoolean("tconstruct_melee_weapons", "pulses", true, "Block this TConstruct pulse");
        config.getBoolean("tconstruct_ranged_weapons", "pulses", true, "Block this TConstruct pulse");

        
        BlocklistConfig.init(config);



        final boolean enableDisenchanter = config.getBoolean("EnableDisenchanterMixins", Configuration.CATEGORY_GENERAL, true,
        	    "Reducible damage formula change to linear. WARNING: Linear damage scaling will always break items at reducibleDmg ≥ 4.0");

        	final boolean enableBOP = config.getBoolean("EnableBiomesOPlentyMixins", Configuration.CATEGORY_GENERAL, true,
        	    "Fixes healing bug in hot spring water");

        	final boolean enableSocketed = config.getBoolean("EnableSocketedMixins", Configuration.CATEGORY_GENERAL, true,
        	    "Resolves JER crashing when checking socketable gear. Adds socket blacklist");

        	//final boolean enableCTSetBonus = config.getBoolean("EnableCTSetBonusMixins", Configuration.CATEGORY_GENERAL, false,
//        	    "Enable CTSetBonus mixins.");
        	
        	final boolean enableTConstructToolBlocker = config.getBoolean("EnableTConstructToolBlockerMixins", Configuration.CATEGORY_GENERAL, true,
        		    "Blocks registration of specific TConstruct tools and parts based on config. Prevents GUI and registry clutter.");

//        	final boolean enableRLTweakerTransformerFixes = config.getBoolean("EnableRLTweakerTransformerFixes", Configuration.CATEGORY_GENERAL, true,
//        		    "Prevents a crash in RLTweaker when its enchantment blacklist logic fails due to missing bytecode.");
        	
        	final boolean enableChampionLootPatch = config.getBoolean("EnableChampionLootPatchMixin", Configuration.CATEGORY_GENERAL, true,
        	        "Overrides Champions loot attribution logic to allow summon-based drops when wearing bronze or toklar armor.");

        	final boolean enableBlightKillPatch = config.getBoolean("EnableBlightKillPatchMixin", Configuration.CATEGORY_GENERAL, true,
        		    "Overrides Scaling Health Blight kill logic to allow summon-based heart drops when wearing bronze or toklar armor.");
        	
      //  	final boolean enableLycanitesAttributionPatch = config.getBoolean("EnableLycanitesAttributionPatchMixin", Configuration.CATEGORY_GENERAL, true,
      //  		    "Overrides Lycanites GameEventListener attribution so summon kills are credited to the player owner.");

        	final boolean enableVanillaAttributionPatch = config.getBoolean("EnableVanillaAttributionPatchMixin",Configuration.CATEGORY_GENERAL,true,
        		    "Injects into EntityLivingBase.onDeath so summon kills are credited to the player owner.");

        	final boolean enableInfernalMobsScalingPatch = config.getBoolean("EnableInfernalMobsScalingPatchMixin", Configuration.CATEGORY_GENERAL, true,
        		    "Injects into InfernalMobsCore.processEntitySpawn so rarity divisors scale with ScalingHealth difficulty.(configurable)");

        	final boolean enableImbuementAltarBreakableMixin = config.getBoolean("EnableImbuementAltarBreakableMixin", Configuration.CATEGORY_GENERAL, true,
        	        "Allow Breakable Ebwizardry Imbuement Altar.");

        	AttributionConfig.ENABLE_VANILLA_ATTRIBUTION_PATCH = enableVanillaAttributionPatch;

        	final String[] infernalTierSpawnModifiers = config.getStringList(
        		    "InfernalTierSpawnModifiers",
        		    "infernalmobs",
        		    new String[] {
        		    	    "rare;0.0048",   // 0.48% per difficulty → 9.6% at 20
        		    	    "ultra;0.0013",  // 0.13% per difficulty → 2.6% at 20
        		    	    "infernal;0.0005"// 0.05% per difficulty → 1% at 20
        		    	},
        		    "Per-difficulty increments for InfernalMobs tiers (tier;increment). Tiers: rare, ultra, infernal."
        		);
        	for (String entry : infernalTierSpawnModifiers) {
        	    String[] parts = entry.split(";");
        	    if (parts.length == 2) {
        	        double val = Double.parseDouble(parts[1]);
        	        switch (parts[0].toLowerCase()) {
        	            case "rare":     InfernalTierScaling.rareIncrement = val; break;
        	            case "ultra":    InfernalTierScaling.ultraIncrement = val; break;
        	            case "infernal": InfernalTierScaling.infernalIncrement = val; break;
        	        }
        	    }
        	}
        	
        	final String[] socketBlacklistEntries = config.getStringList(
        		    "SocketBlacklist",
        		    "socketed",
        		    new String[] {}, // empty default
        		    "List of item registry IDs that should never be socketed."
        		);
        		SocketBlacklist.loadFromConfig(Arrays.asList(socketBlacklistEntries));


        	
        	SocketBlacklist.loadFromConfig(Arrays.asList(socketBlacklistEntries));
        	
        config.save();

        if (enableDisenchanter) {
            FermiumRegistryAPI.enqueueMixin(true, "mixins.tokcraftmixins.disenchanter.json",
                () -> Loader.isModLoaded("disenchanter"));
        }

        if (enableBOP) {
            FermiumRegistryAPI.enqueueMixin(true, "mixins.tokcraftmixins.biomesoplenty.json",
                () -> Loader.isModLoaded("biomesoplenty"));
        }

        if (enableSocketed) {
            FermiumRegistryAPI.enqueueMixin(true, "mixins.tokcraftmixins.socketed.json",
                () -> Loader.isModLoaded("socketed"));
        }

//        if (enableCTSetBonus) {
//            FermiumRegistryAPI.enqueueMixin(true, "mixins.tokcraftmixins.ctsetbonus.json",
//                () -> Loader.isModLoaded("ctsetbonus"));
//        }
        
        if (enableTConstructToolBlocker) {
            FermiumRegistryAPI.enqueueMixin(true, "mixins.tokcraftmixins.tconstruct.json",
                () -> Loader.isModLoaded("tconstruct"));
        }
//        if (enableRLTweakerTransformerFixes) {
//            FermiumRegistryAPI.enqueueMixin(true, "mixins.tokcraftmixins.rltweaker.json",
//                () -> Loader.isModLoaded("rltweaker"));
//        }      
        if (enableChampionLootPatch) {
            FermiumRegistryAPI.enqueueMixin(true, "mixins.tokcraftmixins.champions.json",
                () -> Loader.isModLoaded("champions"));
        }
        if (enableBlightKillPatch) {
            FermiumRegistryAPI.enqueueMixin(true, "mixins.tokcraftmixins.scalinghealth.json",
                () -> Loader.isModLoaded("scalinghealth"));
        }
        
//        if (enableLycanitesAttributionPatch) {
//            FermiumRegistryAPI.enqueueMixin(true, "mixins.tokcraftmixins.lycanitesmobs.json",
//                () -> Loader.isModLoaded("lycanitesmobs"));
//        }
        
        if (enableInfernalMobsScalingPatch) {
            FermiumRegistryAPI.enqueueMixin(true, "mixins.tokcraftmixins.infernalmobs.json",
                () -> Loader.isModLoaded("infernalmobs"));
        }
        
        if (enableImbuementAltarBreakableMixin) {
            FermiumRegistryAPI.enqueueMixin(true, "mixins.tokcraftmixins.ebwizardry.json",
                () -> Loader.isModLoaded("ebwizardry"));
        }

    }


    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}