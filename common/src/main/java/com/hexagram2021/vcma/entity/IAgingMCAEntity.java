package com.hexagram2021.vcma.entity;

import com.hexagram2021.vcma.config.VCMACommonConfig;

/**
 * 定义了具有老化机制的 MCA 实体的通用接口喵~
 * <p>
 * 实现此接口的实体将获得年龄追踪和老化免疫的能力，
 * 最大年龄由 {@link VCMACommonConfig#MAX_AGE} 配置项决定喵~
 * </p>
 *
 * @author liudongyu
 */
public interface IAgingMCAEntity {
	/**
	 * 获取该实体的最大年龄值，默认从配置中读取喵~
	 *
	 * @return 最大年龄（以 tick 为单位）喵~
	 */
	default int vcma$getMaxAge() {
		return VCMACommonConfig.MAX_AGE.get();
	}

	/**
	 * 获取该实体的当前年龄喵~
	 *
	 * @return 当前年龄值（以 tick 为单位）喵~
	 */
	int vcma$getAge();

	/**
	 * 设置该实体的当前年龄喵~
	 *
	 * @param age 新的年龄值（以 tick 为单位）喵~
	 */
	void vcma$setAge(int age);

	/**
	 * 检查该实体是否免疫老化喵~
	 *
	 * @return 如果实体免疫老化则返回 {@code true}，否则返回 {@code false} 喵~
	 */
	boolean vcma$isImmuneToAging();

	/**
	 * 将该实体标记为免疫老化喵~
	 */
	void vcma$setImmuneToAging();

	/**
	 * 设置该实体的老化免疫状态喵~
	 *
	 * @param immuneToAging 如果为 {@code true} 则实体免疫老化，否则不免疫喵~
	 */
	void vcma$setImmuneToAging(boolean immuneToAging);

	/**
	 * 是否处于老年状态
	 * @return 如果为 {@code true} 则为老年状态，否则为青/中年状态
	 */
	default boolean vcma$isElder() {
		return (double)this.vcma$getAge() / this.vcma$getMaxAge() > VCMACommonConfig.ELDER_AGE_RATIO.get();
	}
}
