package com.kevinthegreat.middleground.mixin.widget.button;

import static com.kevinthegreat.middleground.Middleground.randWidth;
import static com.kevinthegreat.middleground.Middleground.randX;
import static com.kevinthegreat.middleground.Middleground.randY;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import com.llamalad7.mixinextras.injector.ModifyReceiver;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.option.SimpleOptionsScreen;
import net.minecraft.client.gui.screen.option.SkinOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

@Mixin({ControlsOptionsScreen.class, KeybindsScreen.class, OptionsScreen.class, PackScreen.class, SkinOptionsScreen.class})
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

	@ModifyReceiver(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;build()Lnet/minecraft/client/gui/widget/ButtonWidget;"), expect = 0)
	private ButtonWidget.Builder middleground_modifyButtonWidget(ButtonWidget.Builder builder) {
		int buttonWidth = randWidth();
		return builder.dimensions(randX(width, buttonWidth), randY(height), buttonWidth, 20);
	}

	@ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/CyclingButtonWidget$Builder;build(IIIILnet/minecraft/text/Text;Lnet/minecraft/client/gui/widget/CyclingButtonWidget$UpdateCallback;)Lnet/minecraft/client/gui/widget/CyclingButtonWidget;"), expect = 0)
	private void middleground_modifyCyclingButtonWidget(Args args) {
		args.set(2, randWidth());
		args.set(0, randX(width, args.get(2)));
		args.set(1, randY(height));
	}
}

