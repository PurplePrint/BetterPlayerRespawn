package com.purpleprint.betterplayerrespawn.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.purpleprint.betterplayerrespawn.content.BlocksAll.ALL_BLOCKS;
import static com.purpleprint.betterplayerrespawn.BetterPlayerRespawn.LOGGER;

public class InitBlocks {
    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block block : ALL_BLOCKS) {
            event.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public void registerItemBlocks(RegistryEvent.Register<Item> event) {
        for (Block block : ALL_BLOCKS) {
            ItemBlock itemBlock = new ItemBlock(block);
            itemBlock.setRegistryName(block.getRegistryName());
            event.getRegistry().register(itemBlock);
        }
    }
}
