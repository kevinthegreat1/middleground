package com.kevinthegreat.middleground.mixin.widget.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.function.BiConsumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.resource.language.LanguageDefinition;

@Mixin(targets = "net.minecraft.client.gui.screen.option.LanguageOptionsScreen$LanguageSelectionListWidget")
public abstract class LanguageSelectionListWidgetMixin {
	@Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/SortedMap;forEach(Ljava/util/function/BiConsumer;)V"))
	private void middleground_redirectIteratorInLoop(SortedMap<String, LanguageDefinition> languageMap, BiConsumer<String, LanguageDefinition> languageConsumer) {
		List<String> list = new ArrayList<>(languageMap.keySet());
		Collections.shuffle(list);
		for (String string : list) {
			languageConsumer.accept(string, languageMap.get(string));
		}
	}
}
