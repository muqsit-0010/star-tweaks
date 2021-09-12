package xyz.starmun.starpackcore.mixin.minecolonies;

import com.minecolonies.api.entity.citizen.Skill;
import com.minecolonies.coremod.colony.buildings.workerbuildings.BuildingSawmill;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BuildingSawmill.class, remap = false)
public class BuildingSawmillMixin {
    @Inject(method = "getPrimarySkill", at=@At("HEAD"), cancellable = true)
    public void getPrimarySkill(CallbackInfoReturnable<Skill> cir){
        cir.setReturnValue(Skill.Dexterity);
    }
    @Inject(method = "getSecondarySkill", at=@At("HEAD"), cancellable = true)
    public void getSecondarySkill(CallbackInfoReturnable<Skill> cir){
        cir.setReturnValue(Skill.Knowledge);
    }
}
