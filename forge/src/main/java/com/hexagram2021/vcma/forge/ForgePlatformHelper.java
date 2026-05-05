package com.hexagram2021.vcma.forge;

import com.hexagram2021.vcma.network.ServerboundTeleportToVillagerPacket;
import com.hexagram2021.vcma.network.ServerboundTeleportVillagerHerePacket;
import com.hexagram2021.vcma.services.IPlatformHelper;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Forge 平台的抽象接口实现类
 * @author liudongyu
 */
public class ForgePlatformHelper implements IPlatformHelper {
	@Override
	public void sendTeleportToVillagerPacket(@Nullable UUID villager) {
		if(villager != null) {
			VillagersComeMoreAliveForge.PACKET_HANDLER.sendToServer(new ServerboundTeleportToVillagerPacket(villager));
		}
	}

	@Override
	public void sendTeleportVillagerHerePacket(@Nullable UUID villager) {
		if(villager != null) {
			VillagersComeMoreAliveForge.PACKET_HANDLER.sendToServer(new ServerboundTeleportVillagerHerePacket(villager));
		}
	}
}
