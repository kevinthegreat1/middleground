package com.kevinthegreat.middleground.mixin.widget.list;

import static com.kevinthegreat.middleground.Middleground.shuffle;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.gui.screen.option.ControlsListWidget;

@Mixin(ControlsListWidget.class)
public abstract class ControlsListWidgetMixin {
	@Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/Arrays;sort([Ljava/lang/Object;)V"))
	private void middleground_redirectSort(Object[] original) {
		shuffle(original);
	}
}
