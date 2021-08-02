package xyz.starmun.starpackcore.mixin;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.chunk.storage.ChunkSerializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import xyz.starmun.starpackcore.data.NBTLite;

@Mixin(ChunkSerializer.class)
public class ChunkSerializerMixin {
    @ModifyVariable(method = "postLoadChunk(Lnet/minecraft/nbt/CompoundNBT;Lnet/minecraft/world/chunk/Chunk;)V", at = @At(value = "HEAD"))
    private static CompoundNBT postLoadChunk(CompoundNBT p_222650_0_) {
        return new NBTLite(p_222650_0_,"TileEntities", "Entities");
    }

}

