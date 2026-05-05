package com.hexagram2021.vcma.fabric;

import com.hexagram2021.vcma.config.VCMACommonConfig;
import com.hexagram2021.vcma.fabric.network.FabricServerboundTeleportToVillagerPacket;
import com.hexagram2021.vcma.fabric.network.FabricServerboundTeleportVillagerHerePacket;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraftforge.fml.config.ModConfig;

import static com.hexagram2021.vcma.VillagersComeMoreAlive.MODID;

/**
 * Fabric entry point
 * @author liudongyu
 */
public class VillagersComeMoreAliveFabric implements ModInitializer {
	/**
	 * Fabric mod entry point
	 */
	public VillagersComeMoreAliveFabric() {
		ForgeConfigRegistry.INSTANCE.register(MODID, ModConfig.Type.COMMON, VCMACommonConfig.get());
	}

	@Override
	public void onInitialize() {
		ServerPlayNetworking.registerGlobalReceiver(FabricServerboundTeleportToVillagerPacket.TYPE, (packet, player, responseSender) -> packet.packet().handle(player.serverLevel(), player));
		ServerPlayNetworking.registerGlobalReceiver(FabricServerboundTeleportVillagerHerePacket.TYPE, (packet, player, responseSender) -> packet.packet().handle(player.serverLevel(), player));
	}
}
