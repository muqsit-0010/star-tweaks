package xyz.starmun.starpackcore.mixin;

import com.mojang.blaze3d.platform.TextureUtil;
import org.lwjgl.system.MemoryUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
/*
Fixes vanilla texture memory leak
 */
@Mixin(TextureUtil.class)
public class TextureUtilMixin {
    @Inject(method = "readResource", at = @At("HEAD"), cancellable = true)
    private static void readResource(InputStream p_225684_0_, CallbackInfoReturnable<ByteBuffer> cir) throws IOException {
        ByteBuffer bytebuffer = null;
        try {
            if (p_225684_0_ instanceof FileInputStream) {
                FileInputStream fileinputstream = (FileInputStream) p_225684_0_;
                FileChannel filechannel = fileinputstream.getChannel();
                bytebuffer = MemoryUtil.memAlloc((int) filechannel.size() + 1);

                while (filechannel.read(bytebuffer) != -1) {
                }
            } else {
                bytebuffer = MemoryUtil.memAlloc(8192);
                ReadableByteChannel readablebytechannel = Channels.newChannel(p_225684_0_);

                while (readablebytechannel.read(bytebuffer) != -1) {
                    if (bytebuffer.remaining() == 0) {
                        bytebuffer = MemoryUtil.memRealloc(bytebuffer, bytebuffer.capacity() * 2);
                    }
                }
            }

            cir.setReturnValue(bytebuffer);
        } catch (IOException ex) {
            if (bytebuffer != null) {
                MemoryUtil.memFree(bytebuffer);
            }
            cir.setReturnValue(bytebuffer);
            throw ex;
        }
    }
}
