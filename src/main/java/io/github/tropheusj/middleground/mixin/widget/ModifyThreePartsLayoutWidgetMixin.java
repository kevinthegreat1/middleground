package io.github.tropheusj.middleground.mixin.widget;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.CreditsAndAttributionScreen;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.github.tropheusj.middleground.Middleground.*;

@Mixin(CreditsAndAttributionScreen.class)
public abstract class ModifyThreePartsLayoutWidgetMixin extends Screen {
    @Shadow
    @Final
    private ThreePartsLayoutWidget layout;

    protected ModifyThreePartsLayoutWidgetMixin(Text text) {
        super(text);
    }

    @Inject(method = {"init", "initTabNavigation"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ThreePartsLayoutWidget;refreshPositions()V", shift = At.Shift.AFTER))
    private void midleground_modifyThreePartsLayoutWidget(CallbackInfo ci) {
        layout.forEachChild(widget -> {
            widget.setX(randX(width));
            widget.setY(randY(height));
            widget.setWidth(randWidth());
            if (widget instanceof TextWidget textWidget) {
                textWidget.setTextColor(randColor());
            }
        });
    }
}
