package com.purpleprint.betterplayerrespawn.content;

import com.purpleprint.betterplayerrespawn.content.blocks.BetterBed;
import com.purpleprint.betterplayerrespawn.content.blocks.BetterBedReinforced;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.purpleprint.betterplayerrespawn.BetterPlayerRespawn.LOGGER;

public class BlocksAll {
    public static final BetterBed BETTER_BED = new BetterBed();
    public static final BetterBedReinforced BETTER_BED_REINFORCED = new BetterBedReinforced();

    public static final List<Block> ALL_BLOCKS = new ArrayList<>();

    static {
        ALL_BLOCKS.add(BETTER_BED);
        ALL_BLOCKS.add(BETTER_BED_REINFORCED);
    }
}
