package xyz.starmun.starpackcore.mixin.minecraft;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.chunk.storage.ChunkSerializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import xyz.starmun.starpackcore.data.NBTLite;
/*
Fixes Chunk serializer memory usage
 */
@Mixin(ChunkSerializer.class)
public class ChunkSerializerMixin {
    @ModifyVariable(method = "postLoadChunk", at = @At(value = "HEAD"))
    private static CompoundTag postLoadChunk(CompoundTag p_222650_0_) {
        return new NBTLite(p_222650_0_,"TileEntities", "Entities");
    }

}

