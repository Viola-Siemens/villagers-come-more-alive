package com.hexagram2021.vcma.forge.mixin;

import com.hexagram2021.vcma.entity.IAgingMCAEntity;
import com.hexagram2021.vcma.register.VCMADamageSources;
import com.hexagram2021.vcma.utils.PoiUtils;
import forge.net.mca.entity.VillagerEntityMCA;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.npc.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 对 MCA 村民实体（{@link VillagerEntityMCA}）的 Mixin，为其添加老化机制支持喵~
 * <p>
 * 通过实现 {@link IAgingMCAEntity} 接口，为村民实体引入年龄追踪和老化免疫功能，
 * 使得村民可以随着年龄增长而发生变化喵~
 * </p>
 *
 * @author liudongyu
 */
@Mixin(VillagerEntityMCA.class)
public class VillagerEntityMCAMixin implements IAgingMCAEntity {
	@Unique
	private boolean vcma$immuneToAging = false;
	@Unique
	private int vcma$age = -1;

	@Override
	public int vcma$getAge() {
		return this.vcma$age;
	}

	@Override
	public void vcma$setAge(int newAge) {
		this.vcma$age = newAge;
	}

	@Override
	public boolean vcma$isImmuneToAging() {
		return this.vcma$immuneToAging;
	}

	@Override
	public void vcma$setImmuneToAging() {
		this.vcma$immuneToAging = true;
	}

	@Override
	public void vcma$setImmuneToAging(boolean immuneToAging) {
		this.vcma$immuneToAging = immuneToAging;
	}

	@Inject(method = "tick", at = @At(value = "TAIL"))
	private void vcma$tick(CallbackInfo ci) {
		VillagerEntityMCA current = (VillagerEntityMCA)(Object)this;
		if(current.level().isClientSide) {
			return;
		}

		// aging tick
		if(!current.isBaby() && !this.vcma$isImmuneToAging()) {
			this.vcma$age += 1;
			if(this.vcma$isElder()) {
				current.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, true, false));
				VillagerProfession profession = current.getProfession();
				if(profession != VillagerProfession.NONE && profession != VillagerProfession.NITWIT) {
					current.setProfession(VillagerProfession.NONE);
					PoiUtils.releasePoi(current);
				}
			}
		}
		if(this.vcma$getMaxAge() > 0 && this.vcma$getAge() >= this.vcma$getMaxAge()) {
			current.hurt(VCMADamageSources.dieInBed(current), 65536.0F);
		}
	}

	@Inject(method = "addAdditionalSaveData", at = @At(value = "TAIL"))
	private void vcma$addAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
		VillagerEntityMCA current = (VillagerEntityMCA)(Object)this;
		if(!current.isBaby()) {
			compound.putInt("VCMA_Age", this.vcma$getAge());
		}
		compound.putBoolean("VCMA_IsImmuneToAging", this.vcma$isImmuneToAging());
	}

	@Inject(method = "readAdditionalSaveData", at = @At(value = "TAIL"))
	private void vcma$readAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
		if(compound.contains("VCMA_Age", Tag.TAG_ANY_NUMERIC)) {
			this.vcma$setAge(compound.getInt("VCMA_Age"));
		} else {
			this.vcma$setAge(-1);
		}
		this.vcma$setImmuneToAging(compound.getBoolean("VCMA_IsImmuneToAging"));
	}
}
