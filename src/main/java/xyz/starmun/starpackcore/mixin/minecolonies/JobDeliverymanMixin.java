package xyz.starmun.starpackcore.mixin.minecolonies;

import com.minecolonies.api.colony.ICitizenData;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.minecolonies.coremod.colony.jobs.AbstractJob;
import com.minecolonies.coremod.colony.jobs.JobDeliveryman;
import com.minecolonies.coremod.entity.ai.citizen.deliveryman.EntityAIWorkDeliveryman;
import com.minecolonies.coremod.util.AttributeModifierUtils;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = JobDeliveryman.class, remap = false)
public abstract class JobDeliverymanMixin extends AbstractJob<EntityAIWorkDeliveryman, JobDeliveryman> {
    public JobDeliverymanMixin(ICitizenData entity) {
        super(entity);
    }

    @Shadow public EntityAIWorkDeliveryman generateAI(){return null;}

    @Inject(method = "onLevelUp", at=@At("HEAD"), cancellable = true)
    public void onLevelUp(CallbackInfo ci){

        if (this.getCitizen().getEntity().isPresent()) {// 93
            AbstractEntityCitizen worker = this.getCitizen().getEntity().get();// 95
            AttributeModifier speedModifier = new AttributeModifier("SkillSpeedBonus", (double)this.getCitizen().getCitizenSkillHandler().getLevel(this.getCitizen().getWorkBuilding().getPrimarySkill()) * 0.1D, AttributeModifier.Operation.ADDITION);// 96
            AttributeModifierUtils.addModifier(worker, speedModifier, Attributes.MOVEMENT_SPEED);// 97
        }
        ci.cancel();
    }
}
