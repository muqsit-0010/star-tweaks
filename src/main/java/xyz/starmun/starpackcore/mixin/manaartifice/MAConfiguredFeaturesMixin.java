package xyz.starmun.starpackcore.mixin.manaartifice;

import com.ma.worldgen.features.MAConfigurations;
import com.ma.worldgen.features.MAConfiguredFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static com.ma.worldgen.features.MAConfiguredFeatures.newConfiguredFeature;

@Mixin(value = MAConfiguredFeatures.class, remap = false)
public class MAConfiguredFeaturesMixin {
    @Shadow
    public static final ConfiguredFeature<?, ?> VINTEUM_ORE;

    static {
        VINTEUM_ORE = newConfiguredFeature("vinteum_ore",  Feature.ORE.configured(MAConfigurations.VINTEUM_ORE).range(45).squared().count(20));// 13 14
    }
}
