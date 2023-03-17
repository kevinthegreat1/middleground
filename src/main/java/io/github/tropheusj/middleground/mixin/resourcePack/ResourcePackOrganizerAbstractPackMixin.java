package io.github.tropheusj.middleground.mixin.resourcePack;

import io.github.tropheusj.middleground.Middleground;
import net.minecraft.resource.ResourcePackProfile;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;
import java.util.List;

@Mixin(targets = "net/minecraft/client/gui/screen/pack/ResourcePackOrganizer$AbstractPack")
public abstract class ResourcePackOrganizerAbstractPackMixin {
	@Shadow
	protected abstract List<ResourcePackProfile> getCurrentList();

	@Shadow
	protected abstract List<ResourcePackProfile> getOppositeList();

	@Inject(method = {"toggle", "move"}, at = @At(value = "INVOKE", target = "Ljava/lang/Runnable;run()V"))
	private void middleground_shuffleResourcePackList(CallbackInfo ci) {
		Collections.shuffle(getCurrentList(), Middleground.RAND);
		Collections.shuffle(getOppositeList(), Middleground.RAND);
	}
}
