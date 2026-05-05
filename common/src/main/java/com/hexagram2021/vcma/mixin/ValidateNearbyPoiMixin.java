package com.hexagram2021.vcma.mixin;

import com.hexagram2021.vcma.entity.IAgingMCAEntity;
import com.mojang.datafixers.kinds.IdF;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.ValidateNearbyPoi;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.behavior.declarative.MemoryAccessor;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

/**
 * 对 MCA 村民验证附近 POI 行为（{@link ValidateNearbyPoi}）的 Mixin
 * @author liudongyu
 */
@Mixin(ValidateNearbyPoi.class)
public class ValidateNearbyPoiMixin {
	@Inject(method = "lambda$create$0", at = @At("HEAD"), remap = false, cancellable = true)
	private static void vcma$cancelIfElder(BehaviorBuilder.Instance<LivingEntity> instance,
										   MemoryAccessor<IdF.Mu, GlobalPos> memoryAccessor,
										   Predicate<Holder<PoiType>> poiValidator,
										   ServerLevel serverLevel, LivingEntity livingEntity, long ticks,
										   CallbackInfoReturnable<Boolean> cir) {
		if(livingEntity instanceof IAgingMCAEntity agingEntity && agingEntity.vcma$isElder() && MemoryModuleType.POTENTIAL_JOB_SITE == memoryAccessor.memoryType) {
			cir.setReturnValue(false);
		}
	}
}
