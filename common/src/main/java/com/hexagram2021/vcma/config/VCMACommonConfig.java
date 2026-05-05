package com.hexagram2021.vcma.config;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * 模组的通用配置类，管理村民老化相关的配置项喵~
 *
 * @author liudongyu
 */
public final class VCMACommonConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

	/** 村民的最大年龄（以 tick 为单位）。设置为 0 可禁用村民老化喵~ */
	public static final ForgeConfigSpec.IntValue MAX_AGE;

	/** 中年年龄比例（当前年龄 / 最大年龄），仅用于显示 */
	public static final ForgeConfigSpec.DoubleValue MIDLIFE_AGE_RATIO;

	/** 老年年龄比例（当前年龄 / 最大年龄），用于显示和村民减速、失业行为 */
	public static final ForgeConfigSpec.DoubleValue ELDER_AGE_RATIO;

	private VCMACommonConfig() {
	}

	/**
	 * 获取已构建的 Forge 配置规格，供模组加载器注册使用喵~
	 *
	 * @return 构建完成的 {@link ForgeConfigSpec} 实例喵~
	 */
	public static ForgeConfigSpec get() {
		return BUILDER.build();
	}

	static {
		BUILDER.push("vcma-common-config");
		MAX_AGE = BUILDER.comment("The maximum age (in ticks) of a villager. Set to 0 to disable villager aging.").defineInRange("MAX_AGE", 362000, 0, Integer.MAX_VALUE);
		MIDLIFE_AGE_RATIO = BUILDER.comment("The age ratio (current age / maximum age) of a villager at midlife. Used for display only.").defineInRange("MIDLIFE_AGE_RATIO", 0.4D, 0.0D, 1.0D);
		ELDER_AGE_RATIO = BUILDER.comment("The age ratio (current age / maximum age) of a villager at elderly. Used for display and villager slowing down and unemployment behavior.").defineInRange("ELDER_AGE_RATIO", 0.99D, 0.0D, 1.0D);
		BUILDER.pop();
	}
}
