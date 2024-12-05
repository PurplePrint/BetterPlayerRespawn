package com.purpleprint.betterplayerrespawn.content.blocks;

public class BetterBed extends AbstractBetterBed {
    public BetterBed() {
        super("better_bed", "betterplayerrespawn.better_bed");
    }

    @Override
    protected float getHardness() {
        return 3.0F;
    }

    @Override
    protected float getResistance() {
        return 20.0F;
    }

    @Override
    protected boolean temporarySpawn() {
        return false;
    }
}
