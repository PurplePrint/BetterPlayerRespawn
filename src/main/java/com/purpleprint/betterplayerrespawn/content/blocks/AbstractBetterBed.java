package com.purpleprint.betterplayerrespawn.content.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.purpleprint.betterplayerrespawn.BetterPlayerRespawn.LOGGER;

public abstract class AbstractBetterBed extends Block {

    public static final String TAG_SPAWNPOINT = "BetterBed_Spawnpoint";
    public static final String TAG_TEMP_SPAWNPOINT = "BetterBed_Spawnpoint_temp";

    public AbstractBetterBed(String registryName, String translationKey) {
        super(Material.ROCK);

        setRegistryName(new ResourceLocation("betterplayerrespawn", registryName));
        setTranslationKey(translationKey);

        setHardness(getHardness());
        setResistance(getResistance());
    }

    // Переопределяем метод активации ПКМ
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing,
                                    float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {

            NBTTagCompound persistentData = playerIn.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
            NBTTagCompound spawnPos = new NBTTagCompound();
            spawnPos.setInteger("x", pos.getX());
            spawnPos.setInteger("y", pos.up().getY());
            spawnPos.setInteger("z", pos.getZ());
            persistentData.setTag(
                    temporarySpawn() ? TAG_TEMP_SPAWNPOINT : TAG_SPAWNPOINT
                    , spawnPos
            );

            LOGGER.info("Player's NBT: " + playerIn.getEntityData());
            LOGGER.info("Player's NBT (Persistent): " + playerIn.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG));
        }
        return true;
    }

    // Абстрактные методы для получения характеристик
    protected abstract float getHardness();
    protected abstract float getResistance();

    protected abstract boolean temporarySpawn();
}
