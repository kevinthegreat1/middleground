package com.kevinthegreat.middleground.mixin.widget.list;

import static com.kevinthegreat.middleground.Middleground.randWidth;
import static com.kevinthegreat.middleground.Middleground.randX;
import static com.kevinthegreat.middleground.Middleground.randY;

import java.util.Collections;

import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kevinthegreat.middleground.Middleground;
import com.kevinthegreat.middleground.mixin.accessor.OptionListWidgetWidgetEntryAccessor;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.MouseOptionsScreen;
import net.minecraft.client.gui.screen.option.SimpleOptionsScreen;
import net.minecraft.client.gui.screen.option.SkinOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.text.Text;

@Mixin(value = {ControlsOptionsScreen.class, MouseOptionsScreen.class, SimpleOptionsScreen.class, SkinOptionsScreen.class, SoundOptionsScreen.class, VideoOptionsScreen.class})
public abstract class ModifyOptionListWidgetMixin extends Screen {
	@Unique
	private OptionListWidget middleground$optionListWidget;

	public ModifyOptionListWidgetMixin(Text title) {
		super(title);
	}

	@ModifyExpressionValue(method = "init", at = @At(value = "NEW", target = "(Lnet/minecraft/client/MinecraftClient;IILnet/minecraft/client/gui/screen/option/GameOptionsScreen;)Lnet/minecraft/client/gui/widget/OptionListWidget;"))
	private OptionListWidget middleground_getOptionListWidget(OptionListWidget optionListWidget) {
		return this.middleground$optionListWidget = optionListWidget;
	}

	@Inject(method = {"init", "initTabNavigation"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/OptionListWidget;position(ILnet/minecraft/client/gui/widget/ThreePartsLayoutWidget;)V", shift = At.Shift.AFTER))
	private void middleground_modifyButtonListOrder(CallbackInfo info) {
		for (OptionListWidget.WidgetEntry entry : middleground$optionListWidget.children()) {
			for (ClickableWidget widget : ((OptionListWidgetWidgetEntryAccessor) entry).getWidgets()) {
				widget.setWidth(randWidth());
				widget.setX(randX(width, widget.getWidth()));
				widget.setY(randY(height));
			}
		}
		Collections.shuffle(middleground$optionListWidget.children(), Middleground.RAND);
	}
}
