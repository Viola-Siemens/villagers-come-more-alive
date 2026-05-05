package com.hexagram2021.vcma.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;

import static com.hexagram2021.vcma.VillagersComeMoreAlive.MODID;

/**
 * 伤害源注册类
 * @author liudongyu
 */
public final class VCMADamageSources {
	private static final ResourceKey<DamageType> DIE_IN_BED = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(MODID, "vcma_die_in_bed"));

	private VCMADamageSources() {
	}

	/**
	 * 衰老而死
	 * @param victim	死亡者
	 * @return 伤害源
	 */
	public static DamageSource dieInBed(LivingEntity victim) {
		return victim.damageSources().source(DIE_IN_BED);
	}
}
