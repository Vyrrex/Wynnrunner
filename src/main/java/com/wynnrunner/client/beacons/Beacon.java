package com.wynnrunner.client.beacons;

import com.wynnrunner.client.commands.BeaconCommand;
import com.wynnrunner.client.util.Vector;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public record Beacon(BlockPos location, int beaconID, BeaconColor color) {

    private final static TreeMap<String, Beacon> beacons = new TreeMap<>();

    public static Beacon getBeacon(BlockPos location, int beaconID) {
        String key = location + ""
        return beacons.getOrDefault()
    }

    public int getDistance() {
        BlockPos playerPos = MinecraftClient.getInstance().player.getBlockPos();
        return (int) new Vector(playerPos.getX(), playerPos.getZ(), this.location()).getLength();
    }
}
