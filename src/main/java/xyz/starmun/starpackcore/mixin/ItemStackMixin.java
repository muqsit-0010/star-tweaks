package xyz.starmun.starpackcore.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract Item getItem();

    @Inject(method = "getHoverName", at=@At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item;getName(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/network/chat/Component;", shift = At.Shift.BEFORE), cancellable = true)
    public void getHoverName(CallbackInfoReturnable<Component> cir){
        try {
             cir.setReturnValue(this.getItem().getName((ItemStack)(Object)this));// 529
        }
        catch (Exception ex){
            cir.setReturnValue(new TextComponent(""));
        }
    }
}
