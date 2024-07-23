package com.teampotato.notfullscreen.mixin;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.main.GameConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {
    @Shadow @Final public Options options;

    @Shadow @Final private Window window;

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    public void notFull(GameConfig pGameConfig, CallbackInfo ci){
        if (this.options.fullscreen().get() && this.window.isFullscreen()) {
            this.window.toggleFullScreen();
            this.options.fullscreen().set(this.window.isFullscreen());
        }
    }
}
