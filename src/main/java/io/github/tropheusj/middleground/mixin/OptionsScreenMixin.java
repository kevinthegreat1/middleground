package io.github.tropheusj.middleground.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.world.Difficulty;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {
	@Unique
	private static final Random rand = new Random();
	@Shadow
	private CyclingButtonWidget<Difficulty> difficultyButton;

	protected OptionsScreenMixin(Text text) {
		super(text);
	}

	private static int randomX(int width) {
		return rand.nextInt(width - 300);
	}

	private static int randomY(int height) {
		return rand.nextInt(height - 20);
	}

	private static int randomWidth() {
		return rand.nextInt(300);
	}

	@ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/LockButtonWidget;<init>(IILnet/minecraft/client/gui/widget/ButtonWidget$PressAction;)V"))
	private void middleground_modifyLockButtonWidgetInit(Args args) {
		args.set(0, randomX(width));
		args.set(1, randomY(height));
	}

	@Inject(method = "init", at = @At("RETURN"))
	private void middleground_finalizeDifficultyButton(CallbackInfo ci) {
		if (difficultyButton != null) {
			difficultyButton.setWidth(randomWidth());
		}
	}

	@ModifyArgs(method = "createDifficultyButtonWidget", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/CyclingButtonWidget$Builder;build(IIIILnet/minecraft/text/Text;Lnet/minecraft/client/gui/widget/CyclingButtonWidget$UpdateCallback;)Lnet/minecraft/client/gui/widget/CyclingButtonWidget;"))
	private static void middleground_modifyDifficultyButtonWidgetInit(Args args, int buttonIndex, int width, int height, String translationKey, MinecraftClient client) {
		args.set(0, randomX(width));
		args.set(1, randomY(height));
		args.set(2, randomWidth());
	}
}
