package com.hexagram2021.vcma.fabric.network;

import com.hexagram2021.vcma.network.ServerboundTeleportVillagerHerePacket;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

import static com.hexagram2021.vcma.VillagersComeMoreAlive.MODID;

/**
 * Fabric 网络包，用于客户端请求将指定村民传送至玩家位置喵~
 *
 * @author liudongyu
 */
public record FabricServerboundTeleportVillagerHerePacket(ServerboundTeleportVillagerHerePacket packet) implements FabricPacket {
	/**
	 * Fabric 包类型标识喵~
	 */
	public static final PacketType<FabricServerboundTeleportVillagerHerePacket> TYPE = PacketType.create(
			new ResourceLocation(MODID, "teleport_villager_here"),
			FabricServerboundTeleportVillagerHerePacket::new
	);

	/**
	 * 通过村民 UUID 构造传送包喵~
	 *
	 * @param villager 目标村民的 UUID 喵~
	 */
	public FabricServerboundTeleportVillagerHerePacket(UUID villager) {
		this(new ServerboundTeleportVillagerHerePacket(villager));
	}

	/**
	 * 从网络缓冲区反序列化传送包喵~
	 *
	 * @param buf 网络数据缓冲区喵~
	 */
	public FabricServerboundTeleportVillagerHerePacket(FriendlyByteBuf buf) {
		this(new ServerboundTeleportVillagerHerePacket(buf));
	}

	/**
	 * 将包数据序列化至网络缓冲区喵~
	 *
	 * @param buf 网络数据缓冲区喵~
	 */
	@Override
	public void write(FriendlyByteBuf buf) {
		this.packet.write(buf);
	}

	/**
	 * 获取包类型标识喵~
	 *
	 * @return Fabric 包类型标识喵~
	 */
	@Override
	public PacketType<FabricServerboundTeleportVillagerHerePacket> getType() {
		return TYPE;
	}
}
