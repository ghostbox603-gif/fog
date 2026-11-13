package com.densefog;

import net.minecraft.client.Minecraft;
import com.mojang.blaze3d.shaders.FogShape;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.material.FogType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DenseFogMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class FogHandler {

    @SubscribeEvent
    public static void onRenderFog(ViewportEvent.RenderFog event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return;

        // Giữ nguyên hiệu ứng vanilla dưới nước / dung nham / tuyết bột
        if (event.getCamera().getFluidInCamera() != FogType.NONE) return;
        if (mc.player.hasEffect(MobEffects.BLINDNESS) || mc.player.hasEffect(MobEffects.DARKNESS)) return;

        double base = FogConfig.FOG_DISTANCE.get();
        double pulse = FogConfig.PULSE_STRENGTH.get();
        double t = System.currentTimeMillis() / 1000.0D;

        // Sương "thở": hai sóng sin chồng nhau cho cảm giác không đều, bất an
        double wave = Math.sin(t * 0.35D) * 0.6D + Math.sin(t * 0.91D + 1.7D) * 0.4D;
        float far = (float) Math.max(2.5D, base * (1.0D + pulse * wave));

        event.setNearPlaneDistance(0.0F);
        event.setFarPlaneDistance(far);
        event.setFogShape(FogShape.SPHERE);
        event.setCanceled(true); // bắt buộc để Forge áp dụng giá trị
    }

    @SubscribeEvent
    public static void onFogColor(ViewportEvent.ComputeFogColor event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;
        if (event.getCamera().getFluidInCamera() != FogType.NONE) return;

        // Xám xanh tối, hơi ngả lục - màu sương chết chóc
        event.setRed(0.16F);
        event.setGreen(0.19F);
        event.setBlue(0.18F);
    }
}
