package com.hexagram2021.vcma.compat.jade;

import net.minecraft.world.entity.AgeableMob;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

/**
 * Jade/Hwyla 模组的插件注册类，负责将 {@link AgingProvider} 注册到 Jade 的客户端和服务端喵~
 * <p>
 * 通过 {@link WailaPlugin} 注解自动被 Jade 发现和加载喵~
 *
 * @author liudongyu
 */
@WailaPlugin
public class WailaHelper implements IWailaPlugin {
	/**
	 * 在服务端注册实体数据提供器，使得 {@link AgingProvider} 能够为 {@link AgeableMob} 实体提供年龄数据喵~
	 *
	 * @param registration Jade 通用注册接口喵~
	 */
	@Override
	public void register(IWailaCommonRegistration registration) {
		registration.registerEntityDataProvider(AgingProvider.INSTANCE, AgeableMob.class);
	}

	/**
	 * 在客户端注册实体组件提供器，使得 {@link AgingProvider} 能够为 {@link AgeableMob} 实体渲染年龄工具提示喵~
	 *
	 * @param registration Jade 客户端注册接口喵~
	 */
	@Override
	public void registerClient(IWailaClientRegistration registration) {
		registration.registerEntityComponent(AgingProvider.INSTANCE, AgeableMob.class);
	}
}
