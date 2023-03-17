package io.github.tropheusj.middleground.mixin;

import static io.github.tropheusj.middleground.Middleground.randWidth;
import static io.github.tropheusj.middleground.Middleground.randX;
import static io.github.tropheusj.middleground.Middleground.randY;

import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {

	protected OptionsScreenMixin(Text text) {
		super(text);
	}

	@ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/GridWidget;forEachChild(Ljava/util/function/Consumer;)V", ordinal = 0))
	private Consumer<ClickableWidget> midleground_modifyGridWidgetWidgets(Consumer<ClickableWidget> addDrawableChild) {
		return widget -> {
			widget.setX(randX(width));
			widget.setY(randY(height));
			widget.setWidth(randWidth());
			addDrawableChild.accept(widget);
		};
	}
}
