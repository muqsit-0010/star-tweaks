package xyz.starmun.starpackcore.mixin.forge;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.network.FMLStatusPing;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.starmun.starpackcore.StarpackCore;
import xyz.starmun.starpackcore.contracts.IFMLStatPingExtensions;

import java.util.Map;

@Mixin(value = FMLStatusPing.class, remap = false)
public class FMLStatusPingMixin implements IFMLStatPingExtensions {

    private final static int CHANNEL_TRUNCATE_LIMIT=Integer.MAX_VALUE;

    private final static int MOD_TRUNCATE_LIMIT=Integer.MAX_VALUE;

    @Shadow public transient Map<ResourceLocation, Pair<String, Boolean>> channels;

    @Shadow private transient Map<String, String> mods;

    @Shadow private static volatile boolean warnedAboutTruncation;

    @Shadow private transient int fmlNetworkVer;

    @Override
    public Map<ResourceLocation, Pair<String, Boolean>> getChannels() {
        return channels;
    }
    @Override
    public  Map<String, String> getMods() {
        return mods;
    }
    @Override
    public int getFMLNetworkVer() {
        return fmlNetworkVer;
    }

    @Mixin(value = FMLStatusPing.Serializer.class, remap = false)
    public static class SerializerMixin {
        @Inject(method = "serialize",at = @At("HEAD"), cancellable = true)
        private static void serialize(FMLStatusPing forgeData, JsonSerializationContext ctx, CallbackInfoReturnable<JsonObject> cir){

            JsonObject obj = new JsonObject();
            JsonArray channels = new JsonArray();
            boolean truncated = ((IFMLStatPingExtensions)forgeData).getChannels().size() > CHANNEL_TRUNCATE_LIMIT || ((IFMLStatPingExtensions)forgeData).getMods().size() > MOD_TRUNCATE_LIMIT;
            if (truncated && !warnedAboutTruncation)
            {
                warnedAboutTruncation = true;
                StarpackCore.LOGGER.warn("Heuristically truncating mod and/or network channel list in server status ping packet. Compatibility report " +
                        "in the multiplayer screen may be inaccurate.");
            }

            ((IFMLStatPingExtensions)forgeData).getChannels().entrySet().stream().limit(CHANNEL_TRUNCATE_LIMIT).forEach(entry -> {
                ResourceLocation namespace = entry.getKey();
                Pair<String, Boolean> version = entry.getValue();
                JsonObject mi = new JsonObject();
                mi.addProperty("res", namespace.toString());
                mi.addProperty("version", version.getLeft());
                mi.addProperty("required", version.getRight());
                channels.add(mi);
            });

            obj.add("channels", channels);

            JsonArray modTestValues = new JsonArray();
            ((IFMLStatPingExtensions)forgeData).getMods().entrySet().stream().limit(MOD_TRUNCATE_LIMIT).forEach(entry -> {
                String modId = entry.getKey();
                String value = entry.getValue();
                JsonObject mi = new JsonObject();
                mi.addProperty("modId", modId);
                mi.addProperty("modmarker", value);
                modTestValues.add(mi);
            });
            obj.add("mods", modTestValues);
            obj.addProperty("fmlNetworkVersion", ((IFMLStatPingExtensions)forgeData).getFMLNetworkVer());
            obj.addProperty("truncated", truncated);
            cir.setReturnValue(obj);
        }
    }
}