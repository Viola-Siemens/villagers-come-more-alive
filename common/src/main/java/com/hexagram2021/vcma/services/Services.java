package com.hexagram2021.vcma.services;

import com.hexagram2021.vcma.utils.VCMALogger;

import java.util.ServiceLoader;

/**
 * 服务加载器，用于加载服务实现
 * @author liudongyu
 */
public final class Services {
	/** 平台抽象接口实例 */
	public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

	/**
	 * 加载服务
	 * @param clazz	服务接口
	 * @return 服务实例
	 * @param <T> 服务接口类型
	 */
	public static <T> T load(Class<T> clazz) {
		final T loadedService = ServiceLoader.load(clazz, clazz.getClassLoader())
				.findFirst()
				.orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
		VCMALogger.info("Loaded {} for service {}", loadedService, clazz);
		return loadedService;
	}

	private Services() {
	}
}
