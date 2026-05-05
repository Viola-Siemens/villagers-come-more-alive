package com.hexagram2021.vcma.utils;

import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.Villager;

/**
 * POI 相关工具类
 * @author liudongyu
 */
public final class PoiUtils {
	/**
	 * 释放村民的工作站和潜在工作站 POI
	 * @param villager	释放 POI 的村民
	 */
	public static void releasePoi(Villager villager) {
		villager.releasePoi(MemoryModuleType.JOB_SITE);
		villager.releasePoi(MemoryModuleType.POTENTIAL_JOB_SITE);
	}

	private PoiUtils() {
	}
}
