package com.hexagram2021.vcma.mixin;

import com.hexagram2021.vcma.entity.IAgingMCAEntity;
import net.mca.entity.VillagerEntityMCA;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

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
}
