package xyz.starmun.starpackcore.contracts;

import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

public interface IFMLStatPingExtensions {
    Map<ResourceLocation, Pair<String, Boolean>> getChannels();
    Map<String, String> getMods();
    int getFMLNetworkVer();
}
