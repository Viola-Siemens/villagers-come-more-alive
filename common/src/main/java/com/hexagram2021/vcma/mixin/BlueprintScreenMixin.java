package com.hexagram2021.vcma.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.mca.client.gui.BlueprintScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlueprintScreen.class)
public class BlueprintScreenMixin {
	@WrapOperation(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;setScreen(Lnet/minecraft/client/gui/screens/Screen;)V"))
	private void vcma$showExtraVillagerOperations(Minecraft minecraft, Screen screen, Operation<Void> original, @Local(argsOnly = true) int button) {
		if(button == 1) {
			// 右键后显示对村民的操作

		} else {
			original.call(minecraft, screen);
		}
	}
}
