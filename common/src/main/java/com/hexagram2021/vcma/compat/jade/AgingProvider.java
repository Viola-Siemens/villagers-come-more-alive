package com.hexagram2021.vcma.compat.jade;

import com.hexagram2021.vcma.config.VCMACommonConfig;
import com.hexagram2021.vcma.entity.IAgingMCAEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AgeableMob;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

import static com.hexagram2021.vcma.VillagersComeMoreAlive.MODID;

/**
 * Jade 兼容模块的年龄信息提供器，用于在 Jade/Hwyla 的实体工具提示中显示村民的年龄阶段信息喵~
 * <p>
 * 该提供器同时实现了服务端数据提供和客户端工具提示渲染功能，
 * 通过单例模式（{@link #INSTANCE}）进行注册和使用喵~
 *
 * @author liudongyu
 */
public enum AgingProvider implements IEntityComponentProvider, IServerDataProvider<EntityAccessor> {
	/**
	 * 单例实例喵~
	 */
	INSTANCE;

	/**
	 * 该提供器的唯一标识符喵~
	 */
	public static final ResourceLocation ID = new ResourceLocation(MODID, "aging");
	/**
	 * NBT 标签键：存储年龄比例（当前年龄 / 最大年龄）喵~
	 */
	private static final String TAG_AGING = "AgingRatio";
	/**
	 * NBT 标签键：标记实体是否免疫年龄增长喵~
	 */
	private static final String TAG_IS_IMMUNE_TO_AGING = "IsImmuneToAging";

	/**
	 * 将年龄信息追加到 Jade 工具提示中喵~
	 * <p>
	 * 根据年龄比例将实体划分为四个阶段：幼年、成年、中年、老年，
	 * 如果实体免疫年龄增长，则显示特殊的免疫提示喵~
	 *
	 * @param iTooltip       Jade 工具提示对象喵~
	 * @param entityAccessor 实体访问器，用于获取服务端数据喵~
	 * @param iPluginConfig  插件配置喵~
	 */
	@Override
	public void appendTooltip(ITooltip iTooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {
		CompoundTag serverData = entityAccessor.getServerData();
		if(serverData.contains(TAG_AGING)) {
			if(serverData.getBoolean(TAG_IS_IMMUNE_TO_AGING)) {
				iTooltip.add(Component.translatable("jade.vcma.immune_to_aging"));
				return;
			}
			double ratio = serverData.getDouble(TAG_AGING);
			MutableComponent component;
			if (ratio < 0) {
				component = Component.translatable("jade.vcma.childhood");
			} else if (ratio < VCMACommonConfig.MIDLIFE_AGE_RATIO.get()) {
				component = Component.translatable("jade.vcma.adulthood");
			} else if (ratio < VCMACommonConfig.ELDER_AGE_RATIO.get()) {
				component = Component.translatable("jade.vcma.midlife");
			} else {
				component = Component.translatable("jade.vcma.elder");
			}
			iTooltip.add(Component.translatable("jade.vcma.bracket", component));
		}
	}

	/**
	 * 在服务端收集实体的年龄数据并写入 NBT 喵~
	 * <p>
	 * 仅当实体实现了 {@link IAgingMCAEntity} 接口且最大年龄大于 0 时才会写入数据喵~
	 *
	 * @param compoundTag    用于存储数据的 NBT 标签喵~
	 * @param entityAccessor 实体访问器，用于获取目标实体喵~
	 */
	@Override
	public void appendServerData(CompoundTag compoundTag, EntityAccessor entityAccessor) {
		AgeableMob mob = (AgeableMob) entityAccessor.getEntity();
		if(mob instanceof IAgingMCAEntity agingEntity) {
			int maxAge = agingEntity.vcma$getMaxAge();
			if(maxAge > 0) {
				compoundTag.putDouble(TAG_AGING, (double) agingEntity.vcma$getAge() / maxAge);
				if (agingEntity.vcma$isImmuneToAging()) {
					compoundTag.putBoolean(TAG_IS_IMMUNE_TO_AGING, true);
				}
			}
		}
	}

	/**
	 * 获取该提供器的唯一标识符喵~
	 *
	 * @return 提供器的 {@link ResourceLocation} 标识符喵~
	 */
	@Override
	public ResourceLocation getUid() {
		return ID;
	}
}
