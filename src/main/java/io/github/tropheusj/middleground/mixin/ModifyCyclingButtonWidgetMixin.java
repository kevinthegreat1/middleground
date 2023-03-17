package io.github.tropheusj.middleground.mixin;

import static io.github.tropheusj.middleground.Middleground.randX;
import static io.github.tropheusj.middleground.Middleground.randWidth;
import static io.github.tropheusj.middleground.Middleground.randY;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.SkinOptionsScreen;
import net.minecraft.text.Text;

@Mixin({SkinOptionsScreen.class})
public abstract class ModifyCyclingButtonWidgetMixin extends Screen {
	protected ModifyCyclingButtonWidgetMixin(Text text) {
		super(text);
	}

	@ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/CyclingButtonWidget$Builder;build(IIIILnet/minecraft/text/Text;Lnet/minecraft/client/gui/widget/CyclingButtonWidget$UpdateCallback;)Lnet/minecraft/client/gui/widget/CyclingButtonWidget;"))
	private void middleground_modifyBuild(Args args) {
		args.set(0, randX(width));
		args.set(1, randY(height));
		args.set(2, randWidth());
	}
}
