package xyz.starmun.starpackcore.mixin.toolbelt;

import dev.gigaherz.toolbelt.belt.ToolBeltInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = ToolBeltInventory.class,remap = false)
public class ToolBeltInventoryMixin {
    @ModifyConstant(method = "getSlots",constant = @Constant(intValue = 9))
    public int maxSlots(int slots){
        return 18;
    }

}
