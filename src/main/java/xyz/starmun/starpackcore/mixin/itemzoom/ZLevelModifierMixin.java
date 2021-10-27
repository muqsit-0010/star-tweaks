package xyz.starmun.starpackcore.mixin.itemzoom;

import mezz.itemzoom.client.RenderHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(RenderHandler.class)
public class ZLevelModifierMixin {
	@ModifyConstant(method = "renderZoomedStack", remap = false, constant = @Constant(floatValue = 100f,ordinal = 1))
	private float increaseZLevel(float old) {
		return 200;
	}
	@ModifyConstant(method = "renderZoomedStack", remap = false, constant = @Constant(floatValue = 100f,ordinal = 2))
	private float decreaseZLevel(float old) {
		return 200;
	}
}
