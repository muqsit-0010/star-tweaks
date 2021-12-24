package xyz.starmun.starpackcore.mixin.silentgear;

import net.silentchaos512.gear.util.GearData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GearData.class)
public class GearDataMixin {
    @Redirect(method = "tryRecalculateStats", at=@At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;equal(FF)Z"))
    private static boolean equal(float a, float b){
       return Math.abs(a - b) < 1.0E-5F;
    }
}
