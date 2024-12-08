package com.purpleprint.betterplayerrespawn.asm;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.Map;

@IFMLLoadingPlugin.Name("BetterPlayerRespawnASM")
public class BPRPlugin implements IFMLLoadingPlugin {

    public static final Logger LOGGER = LogManager.getLogger("BetterPlayerRespawnASM");

    @Override
    public String[] getASMTransformerClass() {
        return new String[] {
                "com.purpleprint.betterplayerrespawn.asm.transformer.GuiGameOverTransformer"
        };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    public static String getTransformerPackage() {
        return "com/purpleprint/betterplayerrespawn/asm/transformer";
    }
}
