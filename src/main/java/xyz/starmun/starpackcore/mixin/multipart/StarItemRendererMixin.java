package xyz.starmun.starpackcore.mixin.multipart;

import codechicken.lib.render.item.IItemRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ItemRenderer.class, priority =999)
public class StarItemRendererMixin {
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void onRenderItem(ItemStack stack, ItemTransforms.TransformType transformType, boolean leftHand, PoseStack mStack, MultiBufferSource buffers, int packedLight, int packedOverlay, BakedModel modelIn, CallbackInfo ci) {
        if (modelIn instanceof IItemRenderer) {// 30
            ci.cancel();// 31
            mStack.pushPose();// 32
            IItemRenderer renderer = (IItemRenderer) ForgeHooksClient.handleCameraTransforms(mStack, modelIn, transformType, leftHand);// 34
            mStack.translate(-0.5D, -0.5D, -0.5D);// 35
            try {
                renderer.renderItem(stack, transformType, mStack, buffers, packedLight, packedOverlay);// 36
            }
            catch (Exception ex){

            }
            mStack.popPose();// 37
        }
    }
}
