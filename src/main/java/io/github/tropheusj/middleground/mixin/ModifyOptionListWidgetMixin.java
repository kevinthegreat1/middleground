package io.github.tropheusj.middleground.mixin;

import static io.github.tropheusj.middleground.Middleground.rand;
import static io.github.tropheusj.middleground.Middleground.randWidth;

import java.util.Collections;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.tropheusj.middleground.Middleground;
import io.github.tropheusj.middleground.mixin.accessor.OptionListWidgetWidgetEntryAccessor;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.MouseOptionsScreen;
import net.minecraft.client.gui.screen.option.SimpleOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.text.Text;

@Mixin(value = {SimpleOptionsScreen.class, MouseOptionsScreen.class, VideoOptionsScreen.class, SoundOptionsScreen.class})
public abstract class ModifyOptionListWidgetMixin extends Screen {
	@Shadow(aliases = {"field_2639", "field_19246", "field_26681", "field_40416"})
	private OptionListWidget optionListWidget;

	public ModifyOptionListWidgetMixin(Text title) {
		super(title);
	}

	@Inject(method = "init", at = @At("RETURN"))
	private void middleground_modifyButtonListOrder(CallbackInfo info) {
		for (OptionListWidget.WidgetEntry entry : optionListWidget.children()) {
			for (ClickableWidget widget : ((OptionListWidgetWidgetEntryAccessor) entry).getWidgets()) {
				widget.setX(rand(width));
				widget.setY(rand(height));
				widget.setWidth(randWidth());
			}
		}
		for (OptionListWidget.WidgetEntry entry : optionListWidget.children()) {
			for (ClickableWidget widget : ((OptionListWidgetWidgetEntryAccessor) entry).getWidgets()) {
				Middleground.LOGGER.info("x: {}, y: {}, width: {}", widget.getX(), widget.getY(), widget.getWidth());
			}
		}
		Collections.shuffle(optionListWidget.children(), Middleground.RAND);
	}
}
