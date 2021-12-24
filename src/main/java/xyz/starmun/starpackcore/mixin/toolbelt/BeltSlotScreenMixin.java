package xyz.starmun.starpackcore.mixin.toolbelt;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.gigaherz.toolbelt.common.BeltContainer;
import dev.gigaherz.toolbelt.common.BeltScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.starmun.starpackcore.StarpackCore;

@Mixin(value = BeltScreen.class)
public class BeltSlotScreenMixin extends AbstractContainerScreen<BeltContainer> {

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(BeltContainer container, Inventory playerInventory, Component title, CallbackInfo ci) {
        this.imageHeight = 156;// 22
        this.inventoryLabelY = this.imageHeight - 94;// 23
    }

    @Shadow(remap=false)
    @Final
    private static ResourceLocation GUI_TEXTURE;

    @SuppressWarnings("deprecation")
    @Inject(method = "renderBg", at = @At("HEAD"), cancellable = true)
    public void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY, CallbackInfo ci) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);// 36
        this.minecraft.getTextureManager().bind(GUI_TEXTURE);// 37
        int i = (this.width - this.imageWidth) / 2;// 38
        int j = (this.height - this.imageHeight) / 2;// 39
        this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.imageHeight);// 40
        int slots = this.getMenu().beltSlots;// 42
        int totalRows = ((slots - 1) / 9) + 1;
        for (int k = 0; k < totalRows; k++) {
            int x = k == totalRows - 1 && slots % 9 != 0 ? 7 + (9 - slots % 9) * 18 / 2 : 7;// 44
            int width = k == totalRows - 1 && slots % 9 != 0 ? (slots % 9) * 18 : 9 * 18;
            this.blit(matrixStack, i + x, j + (k == 0 ? 0 : 1) + (19 * (k + 1)), 0, this.imageHeight, width, 18);// 45
        }
        ci.cancel();
    }// 100

    public BeltSlotScreenMixin(BeltContainer p_i51105_1_, Inventory p_i51105_2_, Component p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
    }

    @Override
    protected void renderBg(PoseStack arg, float f, int i, int j) {

    }
}


