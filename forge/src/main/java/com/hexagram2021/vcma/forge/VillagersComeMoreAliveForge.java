package com.hexagram2021.vcma.forge;

import com.hexagram2021.vcma.config.VCMACommonConfig;
import com.hexagram2021.vcma.network.IServerboundVCMAPacket;
import com.hexagram2021.vcma.network.ServerboundTeleportToVillagerPacket;
import com.hexagram2021.vcma.network.ServerboundTeleportVillagerHerePacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static com.hexagram2021.vcma.VillagersComeMoreAlive.MODID;

/**
 * Forge entry point
 * @author liudongyu
 */
@Mod(MODID)
public final class VillagersComeMoreAliveForge {
	private static final String VERSION = ModList.get().getModFileById(MODID).versionString();

	static final SimpleChannel PACKET_HANDLER = NetworkRegistry.ChannelBuilder
			.named(new ResourceLocation(MODID, "main"))
			.networkProtocolVersion(() -> VERSION)
			.serverAcceptedVersions(VERSION::equals)
			.clientAcceptedVersions(VERSION::equals)
			.simpleChannel();

	/**
	 * Forge mod entry point
	 */
	public VillagersComeMoreAliveForge() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, VCMACommonConfig.get());
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::setup);
	}

	private int messageId = 0;
	@SuppressWarnings("SameParameterValue")
	private <T extends IServerboundVCMAPacket> void registerMessage(Class<T> packetType,
																	Function<FriendlyByteBuf, T> constructor,
																	NetworkDirection direction) {
		PACKET_HANDLER.registerMessage(this.messageId++, packetType, IServerboundVCMAPacket::write, constructor, (packet, ctx) -> {
			NetworkEvent.Context context = ctx.get();
			packet.handle(Objects.requireNonNull(context.getSender()).serverLevel(), context.getSender());
		}, Optional.of(direction));
	}

	/**
	 * Common setup 事件处理器，注册网络包
	 * @param event	事件
	 */
	public void setup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			this.registerMessage(ServerboundTeleportToVillagerPacket.class, ServerboundTeleportToVillagerPacket::new, NetworkDirection.PLAY_TO_SERVER);
			this.registerMessage(ServerboundTeleportVillagerHerePacket.class, ServerboundTeleportVillagerHerePacket::new, NetworkDirection.PLAY_TO_SERVER);
		});
	}
}
