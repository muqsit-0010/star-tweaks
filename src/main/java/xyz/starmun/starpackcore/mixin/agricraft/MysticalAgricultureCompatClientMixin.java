package xyz.starmun.starpackcore.mixin.agricraft;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.infinityraider.agricraft.plugins.mysticalagriculture.MysticalAgricultureCompatClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = MysticalAgricultureCompatClient.class, remap = false)
public class MysticalAgricultureCompatClientMixin {
    @Inject(method = "getCropFromPlantId", at=@At("HEAD"), cancellable = true)
    private static void getCropFromPlantId(String plantId, @SuppressWarnings("deprecation") CallbackInfoReturnable<ICrop> cr) {
       if(plantId.equals("none")){
           cr.setReturnValue(null);
        }
    }
}
