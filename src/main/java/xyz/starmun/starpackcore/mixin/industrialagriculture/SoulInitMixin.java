package xyz.starmun.starpackcore.mixin.industrialagriculture;

import com.blakebr0.mysticalagriculture.api.registry.IMobSoulTypeRegistry;
import com.blakebr0.mysticalagriculture.api.soul.MobSoulType;
import com.github.elrol.industrialagriculture.IndustrialAgriculture;
import com.github.elrol.industrialagriculture.config.MainConfig;
import com.github.elrol.industrialagriculture.init.ColorInit;
import com.github.elrol.industrialagriculture.init.SoulInit;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.starmun.starpackcore.StarpackCore;

import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

@Mixin(value = SoulInit.class, remap = false)
public abstract class SoulInitMixin {

    @Shadow
    @Final
    private static Map<String, MobSoulType> soulMap;

    @Shadow
    @Final
    private static Logger logger;

    @Shadow
    private static Set<ResourceLocation> getEntities(String name) {
        return null;
    }

    @Inject(method = "init", at=@At("HEAD"))
    private static void initConfig(IMobSoulTypeRegistry registry, CallbackInfo ci){
        try {
            if(IndustrialAgriculture.config == null){
                IndustrialAgriculture.config = MainConfig.load();
            }
        }
        catch (Exception ex){
            StarpackCore.LOGGER.error("Failed to load default config for IndustrialAgriculture", ex);
        }

    }
    @Inject(method = "addSoul", at = @At("HEAD"), cancellable = true)
    private static void addSoul(String name, CallbackInfo ci) {
        ci.cancel();
        try {
            if (soulMap.containsKey(name)) {// 123
                logger.info("Mob soul already has been registered");// 124
            } else {
                int count = IndustrialAgriculture.config.getSoulQty(name);// 127
                if (count >= 1) {// 128
                    soulMap.put(name, new MobSoulType(new ResourceLocation("industrialagriculture", name), getEntities(name), name, (double) count, ColorInit.getColor(name)));// 129
                }
            }
        } catch (Exception ex) {
            StarpackCore.LOGGER.info(name);
            StarpackCore.LOGGER.info(IndustrialAgriculture.config != null);
            StarpackCore.LOGGER.error(name, ex);
        }// 125 130
    }
}

