package com.hexagram2021.vcma.fabric;

import com.hexagram2021.vcma.fabric.network.FabricServerboundTeleportToVillagerPacket;
import com.hexagram2021.vcma.fabric.network.FabricServerboundTeleportVillagerHerePacket;
import com.hexagram2021.vcma.services.IPlatformHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Fabric 平台的抽象接口实现类
 * @author liudongyu
 */
public class FabricPlatformHelper implements IPlatformHelper {
	@Override
	public void sendTeleportToVillagerPacket(@Nullable UUID villager) {
		if(villager != null) {
			ClientPlayNetworking.send(new FabricServerboundTeleportToVillagerPacket(villager));
		}
	}

	@Override
	public void sendTeleportVillagerHerePacket(@Nullable UUID villager) {
		if(villager != null) {
			ClientPlayNetworking.send(new FabricServerboundTeleportVillagerHerePacket(villager));
		}
	}
}
