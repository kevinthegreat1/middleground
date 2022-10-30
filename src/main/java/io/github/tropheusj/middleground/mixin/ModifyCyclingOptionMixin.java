package io.github.tropheusj.middleground.mixin;

import static io.github.tropheusj.middleground.Middleground.rand;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.option.SkinOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.text.Text;

@Mixin({SoundOptionsScreen.class, SkinOptionsScreen.class, OptionsScreen.class, ControlsOptionsScreen.class, LanguageOptionsScreen.class})
public abstract class ModifyCyclingOptionMixin extends Screen {
	protected ModifyCyclingOptionMixin(Text text) {
		super(text);
	}

	@ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/SimpleOption;createButton(Lnet/minecraft/client/option/GameOptions;III)Lnet/minecraft/client/gui/widget/ClickableWidget;"))
	private void middleground_modifyCyclingOption(Args args) {
		args.set(1, rand(width));
		args.set(2, rand(height));
	}
}
