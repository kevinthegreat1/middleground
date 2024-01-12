package com.kevinthegreat.middleground.mixin.widget.list;

import java.util.Collections;

import com.kevinthegreat.middleground.Middleground;
import com.kevinthegreat.middleground.mixin.accessor.OptionListWidgetWidgetEntryAccessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.MouseOptionsScreen;
import net.minecraft.client.gui.screen.option.SimpleOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.text.Text;

import static com.kevinthegreat.middleground.Middleground.*;

@Mixin(value = {SimpleOptionsScreen.class, MouseOptionsScreen.class, VideoOptionsScreen.class, SoundOptionsScreen.class})
public abstract class ModifyOptionListWidgetMixin extends Screen {
	@Unique
	private OptionListWidget optionListWidget;

	public ModifyOptionListWidgetMixin(Text title) {
		super(title);
	}

	@ModifyExpressionValue(method = "init", at = @At(value = "NEW", target = "(Lnet/minecraft/client/MinecraftClient;IIII)Lnet/minecraft/client/gui/widget/OptionListWidget;"))
	private OptionListWidget middleground_getOptionListWidget(OptionListWidget optionListWidget) {
		return this.optionListWidget = optionListWidget;
	}

	@Inject(method = "init", at = @At("RETURN"))
	private void middleground_modifyButtonListOrder(CallbackInfo info) {
		for (OptionListWidget.WidgetEntry entry : optionListWidget.children()) {
			for (ClickableWidget widget : ((OptionListWidgetWidgetEntryAccessor) entry).getWidgets()) {
				widget.setWidth(randWidth());
				widget.setX(randX(width, widget.getWidth()));
				widget.setY(randY(height));
			}
		}
		Collections.shuffle(optionListWidget.children(), Middleground.RAND);
	}
}
