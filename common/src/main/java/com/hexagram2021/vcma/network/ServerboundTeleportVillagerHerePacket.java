package com.hexagram2021.vcma.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;

import java.util.UUID;

/**
 * 客户端向服务端发送的传送村民到此处的请求包
 * @author liudongyu
 */
public record ServerboundTeleportVillagerHerePacket(UUID villager) implements IServerboundVCMAPacket {
	/**
	 * 读取数据包
	 * @param buf	缓冲区
	 */
	public ServerboundTeleportVillagerHerePacket(FriendlyByteBuf buf) {
		this(buf.readUUID());
	}

	@Override
	public void write(FriendlyByteBuf buf) {
		buf.writeUUID(this.villager);
	}

	@Override
	public void handle(ServerLevel level, ServerPlayer player) {
		Entity entity = level.getEntity(this.villager);
		if(entity instanceof Villager && entity.level() == player.level()) {
			entity.teleportTo(player.getX(), player.getY(), player.getZ());
		} else {
			player.sendSystemMessage(Component.translatable("message.vcma.teleport.fail"));
		}
	}
}
