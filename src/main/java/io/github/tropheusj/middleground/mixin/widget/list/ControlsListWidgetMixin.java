package io.github.tropheusj.middleground.mixin.widget.list;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import io.github.tropheusj.middleground.Middleground;
import net.minecraft.client.gui.screen.option.ControlsListWidget;

@Mixin(ControlsListWidget.class)
public abstract class ControlsListWidgetMixin {
	@Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/Arrays;sort([Ljava/lang/Object;)V"))
	private void middleground_redirectSort(Object[] original) {
		Middleground.shuffle(original);
	}
}
