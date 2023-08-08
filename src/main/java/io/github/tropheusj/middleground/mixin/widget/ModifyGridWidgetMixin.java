package io.github.tropheusj.middleground.mixin.widget;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Consumer;

import static io.github.tropheusj.middleground.Middleground.*;

@Mixin({OptionsScreen.class})
public abstract class ModifyGridWidgetMixin extends Screen {
    protected ModifyGridWidgetMixin(Text text) {
        super(text);
    }

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/GridWidget;forEachChild(Ljava/util/function/Consumer;)V"))
    private Consumer<ClickableWidget> midleground_modifyGridWidget(Consumer<ClickableWidget> addDrawableChild) {
        return ((Consumer<ClickableWidget>) widget -> {
            widget.setX(randX(width));
            widget.setY(randY(height));
            widget.setWidth(randWidth());
            if (widget instanceof TextWidget textWidget) {
                textWidget.setTextColor(randColor());
            }
        }).andThen(addDrawableChild);
    }
}
