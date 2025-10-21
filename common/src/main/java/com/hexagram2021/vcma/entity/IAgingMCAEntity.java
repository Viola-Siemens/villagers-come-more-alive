package com.hexagram2021.vcma.entity;

import com.hexagram2021.vcma.config.VCMACommonConfig;

public interface IAgingMCAEntity {
	default int vcma$getMaxAge() {
		return VCMACommonConfig.MAX_AGE.get();
	}

	int vcma$getAge();

	void vcma$setAge(int age);

	boolean vcma$isImmuneToAging();

	void vcma$setImmuneToAging();

	void vcma$setImmuneToAging(boolean immuneToAging);
}
