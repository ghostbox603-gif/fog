package com.densefog;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(DenseFogMod.MODID)
public class DenseFogMod {
    public static final String MODID = "densefog";

    public DenseFogMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, FogConfig.SPEC);
    }
}
