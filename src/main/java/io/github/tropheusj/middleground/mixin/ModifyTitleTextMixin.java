package io.github.tropheusj.middleground.mixin;

import static io.github.tropheusj.middleground.Middleground.rand;
import static io.github.tropheusj.middleground.Middleground.randColor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.option.MouseOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.option.SimpleOptionsScreen;
import net.minecraft.client.gui.screen.option.SkinOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

@Mixin(value = {ControlsOptionsScreen.class, KeybindsScreen.class, LanguageOptionsScreen.class,
		MouseOptionsScreen.class, OptionsScreen.class, SimpleOptionsScreen.class, SkinOptionsScreen.class,
		SoundOptionsScreen.class, VideoOptionsScreen.class, PackScreen.class})
public abstract class ModifyTitleTextMixin extends Screen {
	private int oldWidth;
	private int titleX;
	private int titleY;
	private int titleColor;

	protected ModifyTitleTextMixin(Text text) {
		super(text);
	}

	@Inject(method = "init", at = @At("HEAD"))
	private void middleground_init(CallbackInfo ci) {
		titleX = rand(this.width);
		titleY = rand(this.height);
		titleColor = randColor();
	}

	@Inject(method = "render", at = @At(value = "CONSTANT", args = {"intValue=2"} /* width / 2 */, shift = At.Shift.BEFORE))
	private void middleground_renderHead(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		oldWidth = this.width;
		this.width = titleX * 2;
	}

	@ModifyConstant(require = 0, method = "render", constant = @Constant(intValue = 5))
	private int middleground_setY0(int original) {
		return titleY;
	}

	@ModifyConstant(require = 0, method = "render", constant = @Constant(intValue = 15))
	private int middleground_setY1(int original) {
		return titleY;
	}

	@ModifyConstant(require = 0, method = "render", constant = @Constant(intValue = 20))
	private int middleground_setY2(int original) {
		return titleY;
	}

	@ModifyConstant(method = "render", constant = @Constant(intValue = 16777215))
	private int middleground_setColor(int original) {
		return titleColor;
	}

	@Inject(method = "render", at = @At(value = "CONSTANT", args = {"intValue=2"} /* width / 2 */, shift = At.Shift.AFTER))
	private void middleground_renderTail(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		this.width = oldWidth;
	}
}
