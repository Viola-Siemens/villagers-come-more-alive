package com.hexagram2021.vcma.fabric.mixin;

import com.hexagram2021.vcma.gui.widget.DropdownWidget;
import com.hexagram2021.vcma.services.Services;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import fabric.net.mca.client.gui.BlueprintScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

/**
 * 对 MCA 蓝图界面（{@link BlueprintScreen}）的 Mixin，用于扩展村民的右键操作喵~
 * <p>
 * 当玩家在蓝图界面中右键点击村民时，拦截默认的界面切换行为，
 * 转而显示额外的村民操作选项喵~
 * </p>
 *
 * @author liudongyu
 */
@Mixin(BlueprintScreen.class)
public class BlueprintScreenMixin extends Screen {
	@Shadow(remap = false) @Nullable
	private UUID selectedVillager;

	@SuppressWarnings("NotNullFieldNotInitialized")
	@Unique
	private DropdownWidget vcma$dropdownWidget;

	protected BlueprintScreenMixin(Component title) {
		super(title);
	}

	@Inject(method = "setPage", at = @At(value = "FIELD", target = "Lfabric/net/mca/client/gui/BlueprintScreen;buttonPage:Lfabric/net/mca/util/compat/ButtonWidget;", opcode = Opcodes.PUTFIELD, remap = false), remap = false)
	private void vcma$initDropdownWidget(CallbackInfo ci) {
		this.vcma$dropdownWidget = this.addRenderableWidget(new DropdownWidget(
				List.of(
						DropdownWidget.DropdownOption.of(
								Component.translatable("gui.vcma.blueprint.option.teleport"),
								parent -> {
									Services.PLATFORM.sendTeleportVillagerHerePacket(parent.villager());
									BlueprintScreenMixin.this.onClose();
								}
						),
						DropdownWidget.DropdownOption.of(
								Component.translatable("gui.vcma.blueprint.option.teleport_to"),
								parent -> {
									Services.PLATFORM.sendTeleportToVillagerPacket(parent.villager());
									BlueprintScreenMixin.this.onClose();
								}
						)
				),
				0, 0,
				Component.translatable("gui.vcma.blueprint.option.title")
		));
		this.vcma$dropdownWidget.active = false;
		this.vcma$dropdownWidget.visible = false;
	}

	@WrapOperation(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;setScreen(Lnet/minecraft/client/gui/screens/Screen;)V"))
	private void vcma$showExtraVillagerOperations(Minecraft minecraft, Screen screen, Operation<Void> original,
												  @Local(ordinal = 0, argsOnly = true) double mouseX,
												  @Local(ordinal = 1, argsOnly = true) double mouseY,
												  @Local(argsOnly = true) int button) {
		if(button == 1) {
			// 右键后显示对村民的操作
			this.vcma$dropdownWidget.setX((int) mouseX);
			this.vcma$dropdownWidget.setY((int) mouseY);
			this.vcma$dropdownWidget.active = true;
			this.vcma$dropdownWidget.visible = true;
			this.vcma$dropdownWidget.setVillager(this.selectedVillager);
		} else {
			original.call(minecraft, screen);
		}
	}

	@WrapOperation(method = "renderVillagers", at = @At(value = "FIELD", target = "Lfabric/net/mca/client/gui/BlueprintScreen;selectedVillager:Ljava/util/UUID;", opcode = Opcodes.PUTFIELD, ordinal = 0, remap = false), remap = false)
	private void vcma$selectVillager(BlueprintScreen instance, UUID uuid, Operation<Void> original) {
		if(!this.vcma$dropdownWidget.visible) {
			original.call(instance, uuid);
		}
	}

	@WrapOperation(method = "renderVillagers", at = @At(value = "INVOKE", target = "Lfabric/net/mca/client/gui/BlueprintScreen;isMouseWithin(IIII)Z", remap = false), remap = false)
	private boolean vcma$renderExtraVillagerOperations(BlueprintScreen instance, int x, int y, int w, int h, Operation<Boolean> original) {
		return original.call(instance, x, y, w, h) && !this.vcma$dropdownWidget.visible;
	}
}
