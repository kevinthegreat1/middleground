package com.kevinthegreat.middleground.mixin.widget;

import static com.kevinthegreat.middleground.Middleground.randColor;
import static com.kevinthegreat.middleground.Middleground.randWidth;
import static com.kevinthegreat.middleground.Middleground.randX;
import static com.kevinthegreat.middleground.Middleground.randY;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.CreditsAndAttributionScreen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.option.TelemetryInfoScreen;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
import net.minecraft.text.Text;

@Mixin({CreditsAndAttributionScreen.class, GameOptionsScreen.class, OptionsScreen.class, PackScreen.class, TelemetryInfoScreen.class})
public abstract class ModifyThreePartsLayoutWidgetMixin extends Screen {
    @Unique
    private ThreePartsLayoutWidget midleground$layout;

    protected ModifyThreePartsLayoutWidgetMixin(Text text) {
        super(text);
    }

    @ModifyExpressionValue(method = {
            "<init>",
            "<init>(Lnet/minecraft/class_437;)V",
            "<init>(Lnet/minecraft/class_437;Lnet/minecraft/class_315;)V",
            "<init>(Lnet/minecraft/class_437;Lnet/minecraft/class_315;Lnet/minecraft/class_2561;)V",
            "<init>(Lnet/minecraft/class_3283;Ljava/util/function/Consumer;Ljava/nio/file/Path;Lnet/minecraft/class_2561;)V"
    }, at = @At(value = "NEW", target = "Lnet/minecraft/client/gui/widget/ThreePartsLayoutWidget;", remap = true), remap = false)
    private ThreePartsLayoutWidget midleground$getThreePartsLayoutWidget1(ThreePartsLayoutWidget layout) {
        return this.midleground$layout = layout;
    }

    @Inject(method = {"init", "initTabNavigation"}, at = {
			@At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ThreePartsLayoutWidget;refreshPositions()V", shift = At.Shift.AFTER),
			@At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/OptionListWidget;position(ILnet/minecraft/client/gui/widget/ThreePartsLayoutWidget;)V", shift = At.Shift.AFTER)
	})
    private void midleground$modifyThreePartsLayoutWidget(CallbackInfo ci) {
        midleground$layout.forEachChild(widget -> {
			if (widget instanceof OptionListWidget) return;
            widget.setWidth(randWidth());
            widget.setX(randX(width, widget.getWidth()));
            widget.setY(randY(height));
            if (widget instanceof TextWidget textWidget) {
                textWidget.setTextColor(randColor());
            }
        });
    }
}
