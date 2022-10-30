package io.github.tropheusj.middleground.mixin;

import java.util.Collections;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.tropheusj.middleground.Middleground;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.MouseOptionsScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.text.Text;

@Mixin(value = {MouseOptionsScreen.class})
public abstract class ModifyButtonListOrderMixin extends Screen {
	@Shadow
	private ButtonListWidget buttonList;

	public ModifyButtonListOrderMixin(Text title) {
		super(title);
	}

	@Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/option/MouseOptionsScreen;addSelectableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;"))
	private void middleground_modifyButtonListOrder(CallbackInfo info) {
		Collections.shuffle(buttonList.children(), Middleground.RAND);
	}
}
