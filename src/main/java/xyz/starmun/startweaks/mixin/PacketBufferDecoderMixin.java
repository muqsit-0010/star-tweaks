package xyz.starmun.startweaks.mixin;

import net.minecraft.network.status.server.SServerInfoPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SServerInfoPacket.class)
public class PacketBufferDecoderMixin {
	@ModifyConstant(method = "read", constant = @Constant(intValue = 32767))
	private int  getMaxDecodePacketSize(int old) {
		return 100000;
	}
}
