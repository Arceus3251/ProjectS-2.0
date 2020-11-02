package com.DPN.ProjectS;

import com.DPN.ProjectS.proxy.ClientProxy;
import com.DPN.ProjectS.proxy.CommonProxy;
import com.DPN.ProjectS.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, useMetadata = true)
public class Main {
    @Instance
    public static Main instance;

    public static CommonProxy commonProxy = new CommonProxy();
    public static ClientProxy clientProxy = new ClientProxy();

    @EventHandler
    public static void PreInit(FMLPreInitializationEvent event){

    }

    @EventHandler
    public static void init(FMLInitializationEvent event){

    }

    @EventHandler
    public static void PostInit(FMLPostInitializationEvent event){

    }

}
