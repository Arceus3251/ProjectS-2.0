package com.DPN.ProjectS.network;

import com.DPN.ProjectS.Main;
import com.DPN.ProjectS.network.MessageSizeChange;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

    public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(com.DPN.ProjectS.Main.MODID);

    private static int id = 0;

    public static void registerMessages() {
        INSTANCE.registerMessage(MessageSizeChange.MessageHolder.class, MessageSizeChange.class, id++, Side.CLIENT);
    }

}