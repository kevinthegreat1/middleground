package com.kevinthegreat.middleground.mixin.resourcePack;

import com.kevinthegreat.middleground.Middleground;

import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer;
import net.minecraft.resource.ResourcePackProfile;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;
import java.util.List;

@Mixin(ResourcePackOrganizer.class)
public abstract class ResourcePackOrganizerMixin {
	@Shadow
	@Final
	List<ResourcePackProfile> enabledPacks;

	@Shadow
	@Final
	List<ResourcePackProfile> disabledPacks;

	@Inject(method = {"<init>", "refresh"}, at = @At("RETURN"))
	private void middleground_randomizeResourcePackList(CallbackInfo ci) {
		Collections.shuffle(enabledPacks, Middleground.RAND);
		Collections.shuffle(disabledPacks, Middleground.RAND);
	}
}
