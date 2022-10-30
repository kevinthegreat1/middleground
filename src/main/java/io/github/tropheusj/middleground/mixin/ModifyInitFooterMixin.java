package io.github.tropheusj.middleground.mixin;

import static io.github.tropheusj.middleground.Middleground.rand;
import static io.github.tropheusj.middleground.Middleground.randWidth;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.text.Text;

@Mixin(value = {AccessibilityOptionsScreen.class})
public abstract class ModifyInitFooterMixin extends Screen {
	protected ModifyInitFooterMixin(Text text) {
		super(text);
	}

	@ModifyArgs(method = "initFooter", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget;<init>(IIIILnet/minecraft/text/Text;Lnet/minecraft/client/gui/widget/ButtonWidget$PressAction;)V"))
	private void middleground_modifyButtonWidget(Args args) {
		args.set(0, rand(width));
		args.set(1, rand(height));
		args.set(2, randWidth());
	}
}
