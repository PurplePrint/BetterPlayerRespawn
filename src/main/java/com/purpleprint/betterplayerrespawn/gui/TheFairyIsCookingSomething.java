package com.purpleprint.betterplayerrespawn.gui;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;
import javax.annotation.Nullable;

public class TheFairyIsCookingSomething implements IFMLLoadingPlugin {

    public String[] getASMTransformerClass() {
        return new String[]{
                "com.purpleprint.betterplayerrespawn.gui.transformer.GuiGameOverTransformer"
        };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

}
