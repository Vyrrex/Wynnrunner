package com.wynnrunner.client.commands;

import com.wynnrunner.client.beacons.Beacon;
import com.wynnrunner.client.beacons.BeaconCalculator;
import com.wynnrunner.client.beacons.BeaconColor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.HashMap;

public class BeaconCommand {

    private static final HashMap<Integer, Beacon> beacons = new HashMap<>();

    public static synchronized void execute() {
        beacons.clear();
        MinecraftClient mc = MinecraftClient.getInstance();

        if (mc.world != null) {
            mc.world.getEntities().forEach(entity -> {
                if (entity instanceof ArmorStandEntity armorStand) {
                    armorStand.getArmorItems().forEach(itemStack -> {
                        String material = itemStack.getTranslationKey();
                        if (material.contains("golden_pickaxe") || material.contains("golden_shovel")) {
                            int colorcode = itemStack.getDamage();
                            if (!beacons.containsKey(colorcode)) {
                                beacons.put(itemStack.getDamage(), Beacon.getBeacon(armorStand.getBlockPos(), colorcode));
                            }
                        }
                    });
                }
            });
        }
        new BeaconCalculator().start();
    }

    public static Beacon getBeacon(int color) {
        return beacons.get(color);
    }

    public static HashMap<Integer, Beacon> getBeacons() {
        return beacons;
    }
}

