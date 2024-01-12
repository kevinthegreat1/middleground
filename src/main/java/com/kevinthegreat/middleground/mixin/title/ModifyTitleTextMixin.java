package com.kevinthegreat.middleground.mixin.title;

import static com.kevinthegreat.middleground.Middleground.randColor;
import static com.kevinthegreat.middleground.Middleground.randX;
import static com.kevinthegreat.middleground.Middleground.randY;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

import net.minecraft.client.gui.DrawContext;
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
import net.minecraft.text.Text;

@Mixin({ControlsOptionsScreen.class, KeybindsScreen.class, LanguageOptionsScreen.class, MouseOptionsScreen.class, OptionsScreen.class, SimpleOptionsScreen.class, SkinOptionsScreen.class, SoundOptionsScreen.class, PackScreen.class, VideoOptionsScreen.class})
public abstract class ModifyTitleTextMixin extends Screen {
    /**
     * The first random x value. Used to randomize the title.
     */
    @Unique
    private int x1;
    /**
     * The second random x value. Used to randomize the second text element on a screen, if any.
     */
    @Unique
    private int x2;
    /**
     * The first random y value. Used to randomize the title.
     */
    @Unique
    private int y1;
    /**
     * The second random y value. Used to randomize the second text element on a screen, if any.
     */
    @Unique
    private int y2;
    /**
     * The first random color. Used to randomize the title.
     */
    @Unique
    private int color1;
    /**
     * The second random color. Used to randomize the second text element on a screen, if any.
     */
    @Unique
    private int color2;

    protected ModifyTitleTextMixin(Text text) {
        super(text);
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void middleground_init(CallbackInfo ci) {
        x1 = randX(this.width);
        x2 = randX(this.width);
        y1 = randY(this.height);
        y2 = randY(this.height);
        color1 = randColor();
        color2 = randColor();
    }

    @Inject(method = "render", at = @At(value = "CONSTANT", args = {"intValue=2"} /* width / 2 */, ordinal = 0, shift = At.Shift.BEFORE))
    private void middleground_beforeDrawText1(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci, @Share("oldWidth") LocalIntRef oldWidth) {
        oldWidth.set(this.width);
        this.width = x1 * 2;
    }

    @Inject(method = "render", at = @At(value = "CONSTANT", args = {"intValue=2"} /* width / 2 */, ordinal = 1, shift = At.Shift.BEFORE), expect = 0)
    private void middleground_beforeDrawText2(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci, @Share("oldWidth") LocalIntRef oldWidth) {
		oldWidth.set(this.width);
        this.width = x2 * 2;
    }

    @ModifyConstant(method = "render", constant = {@Constant(intValue = 5), @Constant(intValue = 8), @Constant(intValue = 15), @Constant(intValue = 16)}, expect = 0)
    private int middleground_setY1(int original) {
        return y1;
    }

    /**
     * This is needed to have different randomization for the second text element for example in {@link PackScreen#render(DrawContext, int, int, float)}.
     * Note that this will also match the title y value in {@link SkinOptionsScreen#render(DrawContext, int, int, float)}, but it doesn't matter since this is also just a random value.
     */
    @ModifyConstant(method = "render", constant = @Constant(intValue = 20), expect = 0)
    private int middleground_setY2(int original) {
        return y2;
    }

    /**
     * This is specifically to match the subtracted constant ({@code height - 56}) in {@link LanguageOptionsScreen#render(DrawContext, int, int, float)}.
     */
    @ModifyConstant(method = "render", constant = @Constant(intValue = 56), expect = 0)
    private int middleground_setInvertedY2(int original) {
        return height - y2;
    }

    @ModifyConstant(method = "render", constant = @Constant(intValue = 16777215, ordinal = 0))
    private int middleground_setColor1(int original) {
        return color1;
    }

    @ModifyConstant(method = "render", constant = {@Constant(intValue = 16777215, ordinal = 1), @Constant(intValue = 8421504)}, expect = 0)
    private int middleground_setColor2(int original) {
        return color2;
    }

    @Inject(method = "render", at = @At(value = "CONSTANT", args = {"intValue=2"} /* width / 2 */, shift = At.Shift.AFTER))
    private void middleground_afterDrawText(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci, @Share("oldWidth") LocalIntRef oldWidth) {
        this.width = oldWidth.get();
    }
}
