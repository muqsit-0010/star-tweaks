package xyz.starmun.starpackcore.mixin.minecraft;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.starmun.starpackcore.StarpackCore;

import java.util.Random;

@Mixin(SaplingBlock.class)
public class SaplingBlockMixin extends BushBlock {
    @Shadow
    @Final
    public static IntegerProperty STAGE;

    @Shadow
    @Final
    private AbstractTreeGrower treeGrower;

    public SaplingBlockMixin(Properties arg) {
        super(arg);
    }

    @Inject(method = "advanceTree", at = @At("HEAD"), cancellable = true)
    public void advanceTree(ServerLevel arg, BlockPos arg2, BlockState arg3, Random random, CallbackInfo ci) {

        try {
            if (arg3.getValue(STAGE) == 0) {// 39
                arg.setBlock(arg2, arg3.cycle(STAGE), 4);// 40
            } else {
                if (!ForgeEventFactory.saplingGrowTree(arg, random, arg2)) {// 42
                    return;
                }

                this.treeGrower.growTree(arg, arg.getChunkSource().getGenerator(), arg2, arg3, random);// 43
            }
        } catch(ClassCastException exception){
            try {

                StarpackCore.LOGGER.info("Location:[ "+arg2.getX() + " " + arg2.getY() + " " + arg2.getZ()+"]");
                StarpackCore.LOGGER.error("Failed to grow tree: "+this.asBlock().toString());
            }
            catch (Exception exception1){
                StarpackCore.LOGGER.error("Error while printing tree info.");
            }
        }
    }
}
