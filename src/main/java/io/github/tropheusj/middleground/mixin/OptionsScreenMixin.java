package io.github.tropheusj.middleground.mixin;

import java.util.Random;
import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {
	@Unique
	private static final Random rand = new Random();

	protected OptionsScreenMixin(Text text) {
		super(text);
	}

	private static int randomX(int width) {
		return rand.nextInt(width - 300);
	}

	private static int randomY(int height) {
		return rand.nextInt(height - 20);
	}

	private static int randomWidth() {
		return rand.nextInt(300);
	}

	@ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/GridWidget;forEachChild(Ljava/util/function/Consumer;)V", ordinal = 0))
	private Consumer<ClickableWidget> midleground_modifyGridWidgetWidgets(Consumer<ClickableWidget> addDrawableChild) {
		return widget -> {
			widget.setX(randomX(width));
			widget.setY(randomY(height));
			widget.setWidth(randomWidth());
			addDrawableChild.accept(widget);
		};
	}
}
