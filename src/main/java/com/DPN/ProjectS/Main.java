package com.DPN.ProjectS;

import com.DPN.ProjectS.proxy.ClientProxy;
import com.DPN.ProjectS.proxy.CommonProxy;
import com.DPN.ProjectS.util.Reference;
import com.DPN.ProjectS.util.handlers.RegistryHandler;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

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
    public static void PreInit(FMLPreInitializationEvent event){

    }

    @EventHandler
    public static void init(FMLInitializationEvent event){

    }

    @EventHandler
    public static void PostInit(FMLPostInitializationEvent event) {

    }
    @EventHandler
    public static void serverInit(FMLServerStartingEvent event){
        RegistryHandler.serverRegistries(event);
    }

}
