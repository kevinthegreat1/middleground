package com.kevinthegreat.middleground.mixin.widget.list;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.gui.widget.OptionListWidget;

@Mixin(OptionListWidget.WidgetEntry.class)
public abstract class OptionListWidgetWidgetEntryMixin extends ElementListWidget.Entry<OptionListWidget.WidgetEntry> {
	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ClickableWidget;setPosition(II)V"))
	private void middleground$redirectSetPosition(ClickableWidget clickableWidget, int x, int y) {
		clickableWidget.setY(y);
	}
}
