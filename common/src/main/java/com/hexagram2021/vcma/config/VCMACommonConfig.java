package com.hexagram2021.vcma.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class VCMACommonConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

	public static final ForgeConfigSpec.IntValue MAX_AGE;

	private VCMACommonConfig() {
	}

	public static ForgeConfigSpec get() {
		return BUILDER.build();
	}

	static {
		BUILDER.push("vcma-common-config");
		MAX_AGE = BUILDER.comment("The maximum age (in ticks) of a villager. Set to 0 to disable villager aging.").defineInRange("MAX_AGE", 1200, 0, Integer.MAX_VALUE);
		BUILDER.pop();
	}
}
