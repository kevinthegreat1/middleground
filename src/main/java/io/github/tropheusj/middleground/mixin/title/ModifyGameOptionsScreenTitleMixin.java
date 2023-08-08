package io.github.tropheusj.middleground.mixin.title;

import static io.github.tropheusj.middleground.Middleground.randColor;
import static io.github.tropheusj.middleground.Middleground.randX;
import static io.github.tropheusj.middleground.Middleground.randY;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.text.Text;

@Mixin(GameOptionsScreen.class)
public class ModifyGameOptionsScreenTitleMixin extends Screen {
	@Unique
	private int oldWidth;
	@Unique
	private int titleX;
	@Unique
	private int titleY;
	@Unique
	private int titleColor;

	public ModifyGameOptionsScreenTitleMixin(Text title) {
		super(title);
	}

	@Override
	protected void init() {
		super.init();
		titleX = randX(this.width);
		titleY = randY(this.height);
		titleColor = randColor();
	}

	@Inject(method = "render", at = @At(value = "CONSTANT", args = {"intValue=2"} /* width / 2 */, shift = At.Shift.BEFORE))
	private void middleground_renderHead(DrawContext context, OptionListWidget optionButtons, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		oldWidth = this.width;
		this.width = titleX * 2;
	}

	@ModifyConstant(method = "render", constant = @Constant(intValue = 5), require = 0, expect = 0)
	private int middleground_setY0(int original) {
		return titleY;
	}

	@ModifyConstant(method = "render", constant = @Constant(intValue = 15), require = 0, expect = 0)
	private int middleground_setY1(int original) {
		return titleY;
	}

	@ModifyConstant(method = "render", constant = @Constant(intValue = 20), require = 0, expect = 0)
	private int middleground_setY2(int original) {
		return titleY;
	}

	@ModifyConstant(method = "render", constant = @Constant(intValue = 16777215))
	private int middleground_setColor(int original) {
		return titleColor;
	}

	@Inject(method = "render", at = @At(value = "CONSTANT", args = {"intValue=2"} /* width / 2 */, shift = At.Shift.AFTER))
	private void middleground_renderTail(DrawContext context, OptionListWidget optionButtons, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		this.width = oldWidth;
	}
}
