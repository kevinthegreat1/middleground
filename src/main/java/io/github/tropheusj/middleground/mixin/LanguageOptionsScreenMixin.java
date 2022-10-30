package io.github.tropheusj.middleground.mixin;

import static io.github.tropheusj.middleground.Middleground.rand;
import static io.github.tropheusj.middleground.Middleground.randColor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.text.Text;

@Mixin(LanguageOptionsScreen.class)
public abstract class LanguageOptionsScreenMixin extends Screen {
	@Unique
	private int titleX;
	@Unique
	private int titleY;
	@Unique
	private int titleColor;

	protected LanguageOptionsScreenMixin(Text text) {
		super(text);
	}

	@Inject(method = "init", at = @At("HEAD"))
	private void middleground_init(CallbackInfo ci) {
		titleX = rand(this.width);
		titleY = rand(this.height);
		titleColor = randColor();
	}

	@ModifyArgs(method = "render", at = @At(value = "INVOKE", ordinal = 1, target = "Lnet/minecraft/client/gui/screen/option/LanguageOptionsScreen;drawCenteredText(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)V"))
	private void middleground_modifyRender0(Args args) {
		args.set(3, titleX);
		args.set(4, titleY);
		args.set(5, titleColor);
	}
}
