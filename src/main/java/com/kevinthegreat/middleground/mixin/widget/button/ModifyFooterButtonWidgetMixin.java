package com.kevinthegreat.middleground.mixin.widget.button;

import static com.kevinthegreat.middleground.Middleground.randWidth;
import static com.kevinthegreat.middleground.Middleground.randX;
import static com.kevinthegreat.middleground.Middleground.randY;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import com.llamalad7.mixinextras.injector.ModifyReceiver;

import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.text.Text;

@Mixin({AccessibilityOptionsScreen.class, GameOptionsScreen.class, KeybindsScreen.class, LanguageOptionsScreen.class})
public abstract class ModifyFooterButtonWidgetMixin extends Screen {
	protected ModifyFooterButtonWidgetMixin(Text title) {
		super(title);
	}

	@ModifyReceiver(method = "initFooter", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;build()Lnet/minecraft/client/gui/widget/ButtonWidget;"))
	private ButtonWidget.Builder middleground_modifyButtonWidget(ButtonWidget.Builder builder) {
		int buttonWidth = randWidth();
		return builder.dimensions(randX(width, buttonWidth), randY(height), buttonWidth, 20);
	}
}
