package com.hexagram2021.vcma.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

/**
 * 网络包接口，用于定义网络包的写入和处理逻辑
 * @author liudongyu
 */
public interface IServerboundVCMAPacket {
	/**
	 * 将网络包写入字节缓冲区
	 * @param buf	缓冲区
	 */
	void write(FriendlyByteBuf buf);

	/**
	 * 处理网络包
	 * @param level		玩家所在的世界
	 * @param player	处理网络包的玩家
	 */
	void handle(ServerLevel level, ServerPlayer player);
}
