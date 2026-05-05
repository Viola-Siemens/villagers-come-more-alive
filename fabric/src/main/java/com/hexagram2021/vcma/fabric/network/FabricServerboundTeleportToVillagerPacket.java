package com.hexagram2021.vcma.fabric.network;

import com.hexagram2021.vcma.network.ServerboundTeleportToVillagerPacket;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

import static com.hexagram2021.vcma.VillagersComeMoreAlive.MODID;

/**
 * Fabric 网络包，用于客户端请求将玩家传送至指定村民的位置喵~
 *
 * @author liudongyu
 */
public record FabricServerboundTeleportToVillagerPacket(ServerboundTeleportToVillagerPacket packet) implements FabricPacket {
	/**
	 * Fabric 包类型标识喵~
	 */
	public static final PacketType<FabricServerboundTeleportToVillagerPacket> TYPE = PacketType.create(
			new ResourceLocation(MODID, "teleport_to_villager"),
			FabricServerboundTeleportToVillagerPacket::new
	);

	/**
	 * 通过村民 UUID 构造传送包喵~
	 *
	 * @param villager 目标村民的 UUID 喵~
	 */
	public FabricServerboundTeleportToVillagerPacket(UUID villager) {
		this(new ServerboundTeleportToVillagerPacket(villager));
	}

	/**
	 * 从网络缓冲区反序列化传送包喵~
	 *
	 * @param buf 网络数据缓冲区喵~
	 */
	public FabricServerboundTeleportToVillagerPacket(FriendlyByteBuf buf) {
		this(new ServerboundTeleportToVillagerPacket(buf));
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
	public PacketType<FabricServerboundTeleportToVillagerPacket> getType() {
		return TYPE;
	}
}
