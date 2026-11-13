package com.densefog;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/** Thỉnh thoảng phát ra tiếng động từ một hướng ngẫu nhiên trong sương. */
@Mod.EventBusSubscriber(modid = DenseFogMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class CreepyAmbience {
    private static final Random RNG = new Random();
    private static int cooldown = 200;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!FogConfig.CREEPY_SOUNDS.get()) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null || mc.isPaused()) return;

        if (cooldown-- > 0) return;
        cooldown = 300 + RNG.nextInt(900); // 15 - 60 giây giữa các lần

        double angle = RNG.nextDouble() * Math.PI * 2.0D;
        double dist = 9.0D + RNG.nextDouble() * 9.0D;
        double x = mc.player.getX() + Math.cos(angle) * dist;
        double z = mc.player.getZ() + Math.sin(angle) * dist;
        double y = mc.player.getY();

        int kind = RNG.nextInt(6);
        if (kind <= 2) {
            // Chuỗi bước chân đang lại gần
            for (int i = 0; i < 4; i++) {
                double d = dist - i * 1.8D;
                double sx = mc.player.getX() + Math.cos(angle) * d;
                double sz = mc.player.getZ() + Math.sin(angle) * d;
                SoundEvent step = SoundEvents.ZOMBIE_STEP;
                mc.level.playLocalSound(sx, y, sz, step, SoundSource.AMBIENT, 0.7F, 0.55F, false);
            }
        } else if (kind == 3) {
            mc.level.playLocalSound(x, y, z, SoundEvents.ZOMBIE_AMBIENT, SoundSource.AMBIENT, 0.6F, 0.5F, false);
        } else if (kind == 4) {
            mc.level.playLocalSound(x, y, z, SoundEvents.ENDERMAN_AMBIENT, SoundSource.AMBIENT, 0.5F, 0.4F, false);
        } else {
            mc.level.playLocalSound(x, y, z, SoundEvents.SKELETON_STEP, SoundSource.AMBIENT, 0.8F, 0.6F, false);
        }
    }
}
