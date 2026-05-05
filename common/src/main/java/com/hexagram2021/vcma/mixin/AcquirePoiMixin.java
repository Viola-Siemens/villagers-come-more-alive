package com.hexagram2021.vcma.mixin;

import com.hexagram2021.vcma.entity.IAgingMCAEntity;
import com.mojang.datafixers.kinds.Const;
import com.mojang.datafixers.util.Unit;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.behavior.AcquirePoi;
import net.minecraft.world.entity.ai.behavior.declarative.MemoryAccessor;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import org.apache.commons.lang3.mutable.MutableLong;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * 对 MCA 村民获取 POI 行为（{@link AcquirePoi}）的 Mixin
 * @author liudongyu
 */
@Mixin(AcquirePoi.class)
public class AcquirePoiMixin {
	@Inject(method = "lambda$create$6", at = @At(value = "HEAD"), remap = false, cancellable = true)
	private static void vcma$cancelIfElder(boolean onlyIfAdult, MutableLong time, Long2ObjectMap<?> map,
										   Predicate<Holder<PoiType>> acquirablePois, MemoryAccessor<Const.Mu<Unit>, GlobalPos> memoryAccessor,
										   Optional<Byte> entityEventId, ServerLevel serverLevel, PathfinderMob mob, long ticks,
										   CallbackInfoReturnable<Boolean> cir) {
		if(mob instanceof IAgingMCAEntity agingEntity && agingEntity.vcma$isElder() && MemoryModuleType.POTENTIAL_JOB_SITE == memoryAccessor.memoryType) {
			cir.setReturnValue(false);
		}
	}
}
