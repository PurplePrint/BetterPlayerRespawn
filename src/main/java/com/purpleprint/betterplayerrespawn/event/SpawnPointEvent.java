package com.purpleprint.betterplayerrespawn.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SpawnPointEvent {
    @SubscribeEvent
    public void playerSetSpawnEvent(PlayerSetSpawnEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        if (player.world.getBlockState(event.getNewSpawn()).getBlock() == Blocks.BED) {
            event.setCanceled(true);
            player.sendMessage(new TextComponentTranslation("message.betterplayerrespawn.nospawn"));
        }
    }
}
