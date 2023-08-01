package com.wynnrunner.client.mixins;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(TitleScreen.class)
public class TitleMixin {

    @Inject(method = "TitleScreen", at = @)
}
