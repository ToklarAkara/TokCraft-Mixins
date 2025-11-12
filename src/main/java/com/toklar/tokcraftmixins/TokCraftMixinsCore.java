package com.toklar.tokcraftmixins;

import fermiumbooter.FermiumRegistryAPI;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import org.spongepowered.asm.launch.MixinBootstrap;

import com.toklar.tokcraftmixins.helpers.BlocklistConfig;

import java.io.File;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.Name("TokCraftMixinsCore")
public class TokCraftMixinsCore implements IFMLLoadingPlugin {

    public TokCraftMixinsCore() {
        MixinBootstrap.init();

        Configuration config = new Configuration(new File("config/tokcraftmixins.cfg"));
        config.load();

        // toncstruct settings
        config.getBoolean("tconstruct_melee_weapons", "pulses", true, "Block this TConstruct pulse");
        config.getBoolean("tconstruct_ranged_weapons", "pulses", true, "Block this TConstruct pulse");

        
        BlocklistConfig.init(config);



        final boolean enableDisenchanter = config.getBoolean("EnableDisenchanterMixins", Configuration.CATEGORY_GENERAL, true,
        	    "Reducible damage formula change to linear. WARNING: Linear damage scaling will always break items at reducibleDmg ≥ 4.0");

        	final boolean enableBOP = config.getBoolean("EnableBiomesOPlentyMixins", Configuration.CATEGORY_GENERAL, true,
        	    "Fixes healing bug in hot spring water");

        	final boolean enableSocketed = config.getBoolean("EnableSocketedMixins", Configuration.CATEGORY_GENERAL, true,
        	    "Resolves JER crashing when checking socketable gear");

        	//final boolean enableCTSetBonus = config.getBoolean("EnableCTSetBonusMixins", Configuration.CATEGORY_GENERAL, false,
//        	    "Enable CTSetBonus mixins.");
        	
        	final boolean enableTConstructToolBlocker = config.getBoolean("EnableTConstructToolBlockerMixins", Configuration.CATEGORY_GENERAL, true,
        		    "Blocks registration of specific TConstruct tools and parts based on config. Prevents GUI and registry clutter.");

//        	final boolean enableRLTweakerTransformerFixes = config.getBoolean("EnableRLTweakerTransformerFixes", Configuration.CATEGORY_GENERAL, true,
//        		    "Prevents a crash in RLTweaker when its enchantment blacklist logic fails due to missing bytecode.");
        	
        	final boolean enableChampionLootPatch = config.getBoolean("EnableChampionLootPatchMixin", Configuration.CATEGORY_GENERAL, true,
        	        "Overrides Champions loot attribution logic to allow summon-based drops when wearing bronze or toklar armor.");


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