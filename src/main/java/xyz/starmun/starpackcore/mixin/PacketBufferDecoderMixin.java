package xyz.starmun.starpackcore.mixin;

import net.minecraft.network.protocol.status.ClientboundStatusResponsePacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/*
Increases buffer for multiplayer server list
 */
@Mixin(ClientboundStatusResponsePacket.class)
public class PacketBufferDecoderMixin {
	@ModifyConstant(method = "read", constant = @Constant(intValue = 32767))
	private int getMaxDecodePacketSize(int old) {
		return 100000;
	}
}
