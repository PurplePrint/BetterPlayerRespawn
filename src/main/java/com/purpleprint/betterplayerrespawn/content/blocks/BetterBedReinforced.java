package com.purpleprint.betterplayerrespawn.content.blocks;

public class BetterBedReinforced extends AbstractBetterBed {
    public BetterBedReinforced() {
        super("better_bed_reinforced", "betterplayerrespawn.better_bed_reinforced");
    }

    @Override
    protected float getHardness() {
        return 50.0F;
    }

    @Override
    protected float getResistance() {
        return 1200.0F;
    }

    @Override
    protected boolean temporarySpawn() {
        return false;
    }
}
