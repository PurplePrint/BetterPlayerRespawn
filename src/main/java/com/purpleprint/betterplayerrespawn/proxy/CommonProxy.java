package com.purpleprint.betterplayerrespawn.proxy;

import com.purpleprint.betterplayerrespawn.event.SpawnPointEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.purpleprint.betterplayerrespawn.BetterPlayerRespawn.LOGGER;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("preInit event from CommonProxy");

        MinecraftForge.EVENT_BUS.register(new SpawnPointEvent());
    }

    public void init(FMLInitializationEvent event) {
        LOGGER.info("init event from CommonProxy");
    }
}
