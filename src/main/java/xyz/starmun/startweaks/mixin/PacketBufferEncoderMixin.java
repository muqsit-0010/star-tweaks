package xyz.starmun.startweaks.mixin;

import net.minecraft.network.PacketBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PacketBuffer.class)
public class PacketBufferEncoderMixin {
	@ModifyConstant(method = "writeUtf(Ljava/lang/String;)Lnet/minecraft/network/PacketBuffer;",constant = @Constant(intValue = 32767))
	private int  getMaxEncodePacketSize(int old) {
		return 100000;
	}
}
