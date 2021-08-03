package xyz.starmun.starpackcore.data;

import net.minecraft.nbt.CompoundTag;

public class NBTLite extends CompoundTag {
    private final java.util.Set<String> keys = new java.util.HashSet<>();

    public NBTLite(CompoundTag base, String... keys) {
        for (String key : keys) {
            this.keys.add(key);
            this.put(key, base.get(key));
        }
    }
    @Override
    public boolean contains(String key) {
        if (this.keys.contains(key)) {
            return true;
        }
        throw new IllegalStateException("Key" + key + "missing");
    }

    @Override
    public boolean contains(String key, int tag) {
        return contains(key) && super.contains(key, tag);
    }
}
