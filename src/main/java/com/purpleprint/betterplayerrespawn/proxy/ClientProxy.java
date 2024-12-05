package com.purpleprint.betterplayerrespawn.proxy;

//import com.purpleprint.betterplayerrespawn.respawn.ClientEventHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.purpleprint.betterplayerrespawn.BetterPlayerRespawn.LOGGER;
import static com.purpleprint.betterplayerrespawn.content.BlocksAll.ALL_BLOCKS;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for (Block block : ALL_BLOCKS) {
            Item item = Item.getItemFromBlock(block);
            if (item != null) {
                ModelLoader.setCustomModelResourceLocation(item, 0,
                        new ModelResourceLocation(item.getRegistryName(), "inventory"));
            } else {
                System.out.println("Item is null");
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void init(FMLInitializationEvent event) {
        super.init(event);
        LOGGER.info("init event from ClientProxy");

//        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }
}

