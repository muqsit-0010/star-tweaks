package xyz.starmun.starpackcore.mixin.toolbelt;

import dev.gigaherz.toolbelt.common.BeltContainer;
import dev.gigaherz.toolbelt.common.BeltSlot;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BeltContainer.class)
public abstract class BeltContainerMixin extends AbstractContainerMenu {


    @Mutable
    @Shadow
    @Final
    public int beltSlots;

    protected BeltContainerMixin(@Nullable MenuType<?> p_i50105_1_, int p_i50105_2_) {
        super(p_i50105_1_, p_i50105_2_);
    }

    @Redirect(method = "<init>(ILnet/minecraft/world/Container;ILnet/minecraft/world/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Ldev/gigaherz/toolbelt/common/BeltContainer;addSlot(Lnet/minecraft/world/inventory/Slot;)Lnet/minecraft/world/inventory/Slot;"))
    public Slot addSlot(BeltContainer instance, Slot slot) {
        return null;
    }

    @Inject(method = "<init>(ILnet/minecraft/world/Container;ILnet/minecraft/world/item/ItemStack;)V", at = @At(value = "TAIL"))
    private void init(int id, Container playerInventory, int blockedSlot, ItemStack heldItem, CallbackInfo ci) {
        int totalRows = ((this.beltSlots - 1) / 9) + 1;

        for (int k = 0; k < this.beltSlots; ++k) {// 38
            int xOffset = this.beltSlots % 9 != 0 && (k / 9 == totalRows - 1) ? (9 - this.beltSlots % 9) * 18 / 2 : 0;// 37

            this.addSlot(new BeltSlot(playerInventory, heldItem, blockedSlot, k, 8 + xOffset + (k%9) * 18, (k / 9 + 1) * 20));// 40
        }
    }

    @ModifyConstant(method = "bindPlayerInventory", constant = @Constant(intValue = 51))
    public int modifyInventoryY(int y) {
        return 74;
    }

    @ModifyConstant(method = "bindPlayerInventory", constant = @Constant(intValue = 109))
    public int modifyHotbarY(int y) {
        return 132;
    }

    @Override
    public boolean stillValid(Player arg) {
        return true;
    }
}
