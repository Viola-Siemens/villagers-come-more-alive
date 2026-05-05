package com.hexagram2021.vcma.services;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * 平台辅助接口，用于在不同模组加载器（Forge / Fabric）之间提供统一的平台抽象喵~
 * <p>
 * 各平台需提供此接口的具体实现，以便 common 模块中的代码
 * 能够以平台无关的方式调用特定于加载器的功能喵~
 * </p>
 *
 * @author liudongyu
 */
public interface IPlatformHelper {
	/**
	 * 发送传送到村民的包，请保证仅在客户端被调用喵~
	 * @param villager	目标村民
	 */
	void sendTeleportToVillagerPacket(@Nullable UUID villager);

	/**
	 * 发送传送村民到此处的包，请保证仅在客户端被调用喵~
	 * @param villager	目标村民
	 */
	void sendTeleportVillagerHerePacket(@Nullable UUID villager);
}
