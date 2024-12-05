package com.purpleprint.betterplayerrespawn;

import com.purpleprint.betterplayerrespawn.init.InitBlocks;
import com.purpleprint.betterplayerrespawn.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class BetterPlayerRespawn {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    @SidedProxy(clientSide = "com.purpleprint.betterplayerrespawn.proxy.ClientProxy", serverSide = "com.purpleprint.betterplayerrespawn.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static SimpleNetworkWrapper network;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("Hello From {}!", Tags.MOD_NAME);
        LOGGER.info("preInit...");

        MinecraftForge.EVENT_BUS.register(new InitBlocks());

        proxy.preInit(event);

//        network = NetworkRegistry.INSTANCE.newSimpleChannel(Tags.MOD_ID);
//        network.registerMessage(RespawnPacket.Handler.class, RespawnPacket.class, 0, Side.SERVER);
//        network.registerMessage(PacketSetRespawnPoint.Handler.class, PacketSetRespawnPoint.class, 0, Side.SERVER);

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info("init...");
        proxy.init(event);
    }

}
