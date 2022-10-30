package io.github.tropheusj.middleground.mixin;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.resource.language.LanguageDefinition;

@Mixin(targets = "net.minecraft.client.gui.screen.option.LanguageOptionsScreen$LanguageSelectionListWidget")
public abstract class LanguageSelectionListWidgetMixin {
	@Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/SortedSet;iterator()Ljava/util/Iterator;"))
	private Iterator<LanguageDefinition> middleground_redirectIteratorInLoop(SortedSet<LanguageDefinition> sortedSet) {
		List<LanguageDefinition> list = new java.util.ArrayList<>(sortedSet);
		Collections.shuffle(list);
		return list.iterator();
	}
}
