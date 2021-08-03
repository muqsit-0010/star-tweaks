package xyz.starmun.starpackcore.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import mezz.itemzoom.client.RenderHandler;
@Mixin(RenderHandler.class)
public class ZLevelModifierMixin {
	@ModifyConstant(method = "renderZoomedStack", remap = false, constant = @Constant(floatValue = 100f))

	private float  getMaxDecodePacketSize(float old) {
		return 200;
	}
}
