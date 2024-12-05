package com.purpleprint.betterplayerrespawn.content.blocks;

public class TravelersAnchor extends AbstractBetterBed {
    public TravelersAnchor() {
        super("travelers_anchor", "betterplayerrespawn.travelers_anchor");
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
        return true;
    }
}
