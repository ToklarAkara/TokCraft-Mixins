package com.toklar.tokcraftmixins;

import fermiumbooter.FermiumRegistryAPI;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import org.spongepowered.asm.launch.MixinBootstrap;

import java.util.Map;

@MCVersion("1.12.2")
@IFMLLoadingPlugin.Name("TokCraftMixinsCore")
public class TokCraftMixinsCore implements IFMLLoadingPlugin {

	public TokCraftMixinsCore() {
	    MixinBootstrap.init();

	    // Conditionally load Biomes O' Plenty mixins
	    FermiumRegistryAPI.enqueueMixin(true, "mixins.tokcraftmixins.biomesoplenty.json", () -> Loader.isModLoaded("biomesoplenty"));

	    // Conditionally load Socketed mixins
	    FermiumRegistryAPI.enqueueMixin(true, "mixins.tokcraftmixins.socketed.json", () -> Loader.isModLoaded("socketed"));

	    // Conditionally load CTSetBonus mixins
	    //FermiumRegistryAPI.enqueueMixin(true, "mixins.tokcraftmixins.ctsetbonus.json", () -> Loader.isModLoaded("ctsetbonus"));
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