package io.github.tropheusj.middleground.mixin;

import static io.github.tropheusj.middleground.Middleground.randX;
import static io.github.tropheusj.middleground.Middleground.randWidth;
import static io.github.tropheusj.middleground.Middleground.randY;

import java.util.Collections;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

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
	@Unique
	private OptionListWidget optionListWidget;

	public ModifyOptionListWidgetMixin(Text title) {
		super(title);
	}

	@SuppressWarnings({"MixinAnnotationTarget", "InvalidMemberReference"})
	@ModifyExpressionValue(method = "init", at = @At(value = "NEW", target = "(Lnet/minecraft/client/MinecraftClient;IIIII)Lnet/minecraft/client/gui/widget/OptionListWidget;"))
	private OptionListWidget middleground_getOptionListWidget(OptionListWidget optionListWidget) {
		this.optionListWidget = optionListWidget;
		return optionListWidget;
	}

	@Inject(method = "init", at = @At("RETURN"))
	private void middleground_modifyButtonListOrder(CallbackInfo info) {
		for (OptionListWidget.WidgetEntry entry : optionListWidget.children()) {
			for (ClickableWidget widget : ((OptionListWidgetWidgetEntryAccessor) entry).getWidgets()) {
				widget.setX(randX(width));
				widget.setY(randY(height));
				widget.setWidth(randWidth());
			}
		}
		Collections.shuffle(optionListWidget.children(), Middleground.RAND);
	}
}
