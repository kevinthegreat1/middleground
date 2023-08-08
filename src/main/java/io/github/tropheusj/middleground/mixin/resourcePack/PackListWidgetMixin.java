package io.github.tropheusj.middleground.mixin.resourcePack;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.pack.PackListWidget;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static io.github.tropheusj.middleground.Middleground.*;

@Mixin(PackListWidget.class)
public abstract class PackListWidgetMixin extends AlwaysSelectedEntryListWidget<PackListWidget.ResourcePackEntry> {
    @Unique
    private int titleX;
    @Unique
    private int titleY;
    @Unique
    private int titleColor;

    public PackListWidgetMixin(MinecraftClient minecraftClient, int i, int j, int k, int l, int m) {
        super(minecraftClient, i, j, k, l, m);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void middleground_setHeaderArgs(MinecraftClient client, PackScreen screen, int width, int height, Text title, CallbackInfo ci) {
        titleX = randX(screen.width);
        titleY = randY(screen.height);
        titleColor = randColor();
    }

    @ModifyArgs(method = "renderHeader", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)I"))
    private void middleground_modifyHeader(Args args) {
        args.set(2, titleX);
        args.set(3, titleY);
        args.set(4, titleColor);
    }
}
