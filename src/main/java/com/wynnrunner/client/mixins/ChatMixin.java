package com.wynnrunner.client.commands;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.report.log.ReceivedMessage;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)

public class ChatMixin {

    @Inject(method = "addMessage*", at = @At("RETURN"))
    public void onGameMessage(Text message, CallbackInfo ci) {
        MinecraftClient.getInstance().player.sendMessage(Text.of("Chat message received: " + message.getString()));
    }
}