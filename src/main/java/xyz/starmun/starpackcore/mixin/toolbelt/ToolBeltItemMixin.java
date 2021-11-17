package xyz.starmun.starpackcore.mixin.toolbelt;

import dev.gigaherz.toolbelt.belt.ToolBeltItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static dev.gigaherz.toolbelt.belt.ToolBeltItem.getSlotsCount;
import static dev.gigaherz.toolbelt.belt.ToolBeltItem.setSlotsCount;

@Mixin(value = ToolBeltItem.class, remap = false)
public class ToolBeltItemMixin {
    @Inject(method = "getUpgradeXP", at = @At("HEAD"), cancellable = true)
    private static void getUpgradeXP(ItemStack stack, CallbackInfoReturnable<Integer> cir){
        int slots = getSlotsCount(stack);// 194
        if (slots >= 18) {// 196
            cir.setReturnValue( -1);// 197
        } else {
            cir.setReturnValue(slots / 2 * 5);// 199 200 202
        }
    }

    @ModifyConstant(method = "getSlotsCount", constant = @Constant(intValue = 9))
    private static int modifyGetMaxSlotCount(int slots){
        return 18;
    }

    @Inject(method = "upgrade", at= @At("HEAD"), cancellable = true)
    private static void upgrade(ItemStack stack, CallbackInfoReturnable<ItemStack> cir){
        int slots = getSlotsCount(stack);// 207
        if (slots >= 18) {// 209
            cir.setReturnValue(stack.copy());
        } else {
            stack = stack.copy();// 212
            setSlotsCount(stack, slots + 2);// 213
            cir.setReturnValue(stack);
        }
    }
}
