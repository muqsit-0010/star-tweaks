package xyz.starmun.starpackcore.mixin;

import com.github.alexthe666.citadel.server.entity.datatracker.EntityProperties;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.ref.WeakReference;

@SuppressWarnings("deprecation")
@Mixin(value = EntityProperties.class, remap = false)
public class EntityPropertiesMixin<T extends Entity>  {
    @Shadow private WeakReference<T> entity;

    @Inject(method = "getEntity", at=@At(value = "HEAD"), cancellable = true)
    public void getEntity(CallbackInfoReturnable<T> cir){
         if(this.entity != null){
             cir.setReturnValue(this.entity.get());// 63
             return;
         }
         cir.setReturnValue(null);
         return;
    }
}
