package xyz.starmun.startweaks.mixin;

import mezz.itemzoom.client.RenderHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(RenderHandler.class)
public class ZoomModifierMixin {
	@ModifyConstant(method = "renderZoomedStack", remap = false, constant = @Constant(floatValue = 17))

	private float  getMaxDecodePacketSize(float old) {
		return 8.5f;
	}
}
