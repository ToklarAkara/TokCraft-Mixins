package com.toklar.tokcraftmixins;

import com.toklar.tokcraftmixins.helpers.BlocklistConfig;
import com.toklar.tokcraftmixins.helpers.ItemDummy;
import com.toklar.tokcraftmixins.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod(modid = TokCraftMixins.MODID, name = TokCraftMixins.NAME, version = TokCraftMixins.VERSION)
public class TokCraftMixins {
    public static final String MODID = "tokcraftmixins";
    public static final String NAME = "TokCraft Mixins";
    public static final String VERSION = "1.6.1";

    @SidedProxy(
        clientSide = "com.toklar.tokcraftmixins.proxy.ClientProxy",
        serverSide = "com.toklar.tokcraftmixins.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println("TokCraft Mixins loaded.");
        proxy.preInit(event);
        ForgeRegistries.ITEMS.register(ItemDummy.INSTANCE);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);

    }
}