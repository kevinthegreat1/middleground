package io.github.tropheusj.middleground.mixin.buttonwidget;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.gui.screen.option.SimpleOptionsScreen;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static io.github.tropheusj.middleground.Middleground.*;

@Mixin({SimpleOptionsScreen.class, AccessibilityOptionsScreen.class})
public abstract class ModifyFooterButtonWidgetMixin extends Screen {
	protected ModifyFooterButtonWidgetMixin(Text title) {
		super(title);
	}

	@ModifyArgs(method = "initFooter", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;dimensions(IIII)Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;"))
	private void middleground_modifyButtonWidget(Args args) {
		args.set(0, randX(width));
		args.set(1, randY(height));
		args.set(2, randWidth());
	}
}
