package io.github.tropheusj.middleground.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.client.gui.widget.LockButtonWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.text.Text;
import net.minecraft.world.Difficulty;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {
	@Unique
	private static final Random rand = new Random();
	@Shadow
	private CyclingButtonWidget<Difficulty> difficultyButton;

	@Shadow
	private LockButtonWidget lockDifficultyButton;

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

	@Inject(method = "createTopRightButton", at = @At("RETURN"))
	private void middleground_modifyDifficultyButtonWidgetInit(CallbackInfoReturnable<Widget> cir) {
		if (difficultyButton != null) {
			difficultyButton.setX(randomX(width));
			difficultyButton.setY(randomY(height));
			difficultyButton.setWidth(randomWidth());
		}
		if (lockDifficultyButton != null) {
			lockDifficultyButton.setX(randomX(width));
			lockDifficultyButton.setY(randomY(height));
			lockDifficultyButton.setWidth(randomWidth());
		}
	}
}
