package com.DPN.ProjectS;

import com.DPN.ProjectS.capabilities.DefaultSizeCapability;
import com.DPN.ProjectS.capabilities.ISizeCapability;
import com.DPN.ProjectS.capabilities.SizeCapabilityStorage;
import com.DPN.ProjectS.network.PacketHandler;
import com.DPN.ProjectS.proxy.ClientProxy;
import com.DPN.ProjectS.proxy.CommonProxy;
import com.DPN.ProjectS.util.Reference;
import com.DPN.ProjectS.util.handlers.RegistryHandler;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.io.File;
import java.rmi.registry.Registry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, useMetadata = true)
public class Main {
    public static final String MODID = Reference.MOD_ID;

    @Instance
    public static Main instance;

    public static CommonProxy commonProxy = new CommonProxy();
    public static ClientProxy clientProxy = new ClientProxy();
    public static Configuration config;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event){
        CapabilityManager.INSTANCE.register(ISizeCapability.class, new SizeCapabilityStorage(), DefaultSizeCapability.class);
        PacketHandler.registerMessages();

        File directory = event.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "Main.cfg"));
        Config.readConfig();

        commonProxy.preInit(event);
    }

    @EventHandler
    public static void init(FMLInitializationEvent event){
        commonProxy.init(event);
    }

    @EventHandler
    public static void PostInit(FMLPostInitializationEvent event) {
        if(config.hasChanged()){
            config.save();
        }
        Config.postInit();
        commonProxy.postInit(event);
    }

    @EventHandler
    public static void serverInit(FMLServerStartingEvent event){
        RegistryHandler.serverRegistries(event);
    }

}
