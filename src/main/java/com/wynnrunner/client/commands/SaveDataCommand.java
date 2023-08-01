package com.wynnrunner.client.commands;

import com.mojang.brigadier.context.CommandContext;
import com.wynnrunner.client.Client;
import com.wynnrunner.client.beacons.Beacon;
import com.wynnrunner.client.beacons.BeaconColor;
import com.wynnrunner.client.beacons.BeaconFinder;
import com.wynnrunner.client.challenges.Challenge;
import com.wynnrunner.client.challenges.ChallengeType;
import com.wynnrunner.client.sql.BeaconTable;
import com.wynnrunner.client.sql.ChallengeTable;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.util.math.BlockPos;

public class SaveDataCommand {

    public static Beacon savedBeacon;

    public static void saveChallenge(CommandContext<FabricClientCommandSource> context) {

        String beacon = context.getArgument("beacon", String.class);
        String name = context.getArgument("identifier", String.class);
        String type = context.getArgument("type", String.class);

        int beaconID = Integer.parseInt(beacon);

        Beacon beacon1 =  BeaconFinder.getBeacon(beaconID);
        BlockPos location = beacon1.location();
        ChallengeTable.set(Challenge.fromString(beacon1.location(), name + "" ChallengeType.valueOf(type.toUpperCase()));

        Client.sendMessage("§2Saved new Challenge: §e" + name + " " + type + " §2at previous " + beacon1.color().getName() + " Beacon " +
                "§7(" + location.getX() + " " + location.getZ() + ")");
    }

    public static void saveSavedChallenge(CommandContext<FabricClientCommandSource> context) {

        String name = context.getArgument("identifier", String.class);
        String type = context.getArgument("type", String.class);

        BlockPos location = savedBeacon.location();
        ChallengeTable.set(new Challenge(location, name, ChallengeType.valueOf(type.toUpperCase())));

        Client.sendMessage("§2Saved new Challenge: §e" + name + " " + type + " §2at previous " + savedBeacon.color().getName() + " Beacon " +
                "§7(" + location.getX() + " " + location.getZ() + ")");
    }

    public static void saveBeaconColor(CommandContext<FabricClientCommandSource> context) {
        String beacon = context.getArgument("beacon", String.class);
        String color = context.getArgument("color", String.class);

        int beaconID = Integer.parseInt(beacon);
        BeaconColor beaconColor = BeaconColor.valueOf(color.toUpperCase());

        BeaconTable.set(beaconID, beaconColor);
        Client.sendMessage("§2Saved Beacon as " + color + "!");
    }

    public static void saveSavedBeaconColor(CommandContext<FabricClientCommandSource> context) {
        String color = context.getArgument("color", String.class);

        int beaconID = savedBeacon.beaconID();
        BeaconColor beaconColor = BeaconColor.valueOf(color.toUpperCase());

        BeaconTable.set(beaconID, beaconColor);
        Client.sendMessage("§2Saved previous Beacon as " + color + "!");
    }

    public static void saveBeacon(CommandContext<FabricClientCommandSource> context) {
        String beacon = context.getArgument("beacon", String.class);

        int beaconID = Integer.parseInt(beacon);

        savedBeacon = BeaconFinder.getBeacon(beaconID);
        Client.sendMessage("§3Saved " + savedBeacon.color().name() + " Beacon §3 at " + savedBeacon.location().getX() + " | " + savedBeacon.location().getZ());
    }

    public static void removeChallenge(CommandContext<FabricClientCommandSource> context) {
        String name = context.getArgument("identifier", String.class);

        ChallengeTable.remove(name);
    }
}
