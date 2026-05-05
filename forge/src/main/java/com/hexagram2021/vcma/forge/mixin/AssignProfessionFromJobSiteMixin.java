package com.hexagram2021.vcma.forge.mixin;

import com.hexagram2021.vcma.entity.IAgingMCAEntity;
import com.mojang.datafixers.kinds.IdF;
import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.AssignProfessionFromJobSite;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.behavior.declarative.MemoryAccessor;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 对 MCA 村民从工作站获取职业行为（{@link AssignProfessionFromJobSite}）的 Mixin
 * @author liudongyu
 */
@Mixin(AssignProfessionFromJobSite.class)
public class AssignProfessionFromJobSiteMixin {
	@Inject(method = "m_257167_", at = @At("HEAD"), remap = false, cancellable = true)
	private static void vcma$cancelIfElder(BehaviorBuilder.Instance<Villager> var0,
										   MemoryAccessor<IdF.Mu, GlobalPos> potentialJobSite,
										   MemoryAccessor<IdF.Mu, GlobalPos> jobSite,
										   ServerLevel serverLevel, Villager villager, long ticks,
										   CallbackInfoReturnable<Boolean> cir) {
		if(villager instanceof IAgingMCAEntity agingEntity && agingEntity.vcma$isElder()) {
			cir.setReturnValue(false);
		}
	}
}
