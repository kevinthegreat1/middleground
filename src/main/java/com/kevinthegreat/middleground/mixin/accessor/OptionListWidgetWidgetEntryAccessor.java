package com.kevinthegreat.middleground.mixin.accessor;

import net.minecraft.client.gui.widget.ClickableWidget;

import net.minecraft.client.gui.widget.OptionListWidget;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(OptionListWidget.WidgetEntry.class)
public interface OptionListWidgetWidgetEntryAccessor {
	@Accessor
	List<ClickableWidget> getWidgets();
}
