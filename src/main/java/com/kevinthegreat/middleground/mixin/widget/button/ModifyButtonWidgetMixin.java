package com.kevinthegreat.middleground.mixin.widget.button;

import static com.kevinthegreat.middleground.Middleground.randWidth;
import static com.kevinthegreat.middleground.Middleground.randX;
import static com.kevinthegreat.middleground.Middleground.randY;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.option.MouseOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.option.SimpleOptionsScreen;
import net.minecraft.client.gui.screen.option.SkinOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.text.Text;

@Mixin({AccessibilityOptionsScreen.class, ControlsOptionsScreen.class, KeybindsScreen.class, LanguageOptionsScreen.class, MouseOptionsScreen.class, OptionsScreen.class,
		PackScreen.class, SimpleOptionsScreen.class, SkinOptionsScreen.class, SoundOptionsScreen.class, VideoOptionsScreen.class})
public abstract class ModifyButtonWidgetMixin extends Screen {
	protected ModifyButtonWidgetMixin(Text text) {
		super(text);
	}

	@ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;dimensions(IIII)Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;"), expect = 0)
	private void middleground_modifyButtonWidget(Args args) {
		args.set(2, randWidth());
		args.set(0, randX(width, args.get(2)));
		args.set(1, randY(height));
	}

	@ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/CyclingButtonWidget$Builder;build(IIIILnet/minecraft/text/Text;Lnet/minecraft/client/gui/widget/CyclingButtonWidget$UpdateCallback;)Lnet/minecraft/client/gui/widget/CyclingButtonWidget;"), expect = 0)
	private void middleground_modifyCyclingButtonWidget(Args args) {
		args.set(2, randWidth());
		args.set(0, randX(width, args.get(2)));
		args.set(1, randY(height));
	}
}
