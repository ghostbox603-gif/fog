package com.densefog;

import net.minecraftforge.common.ForgeConfigSpec;

public class FogConfig {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.DoubleValue FOG_DISTANCE;
    public static final ForgeConfigSpec.DoubleValue PULSE_STRENGTH;
    public static final ForgeConfigSpec.BooleanValue CREEPY_SOUNDS;

    static {
        ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();
        FOG_DISTANCE = b.comment("Tầm nhìn tối đa (block). Càng nhỏ sương càng dày. Mặc định 14.")
                .defineInRange("fogDistance", 14.0D, 3.0D, 128.0D);
        PULSE_STRENGTH = b.comment("Độ 'thở' của sương (0 = đứng yên, 0.3 = dao động mạnh).")
                .defineInRange("pulseStrength", 0.12D, 0.0D, 0.5D);
        CREEPY_SOUNDS = b.comment("Bật âm thanh bước chân / tiếng động ma quái từ trong sương.")
                .define("creepySounds", true);
        SPEC = b.build();
    }
}
