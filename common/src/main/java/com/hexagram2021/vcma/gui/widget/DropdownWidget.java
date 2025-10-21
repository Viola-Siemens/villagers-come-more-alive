package com.hexagram2021.vcma.gui.widget;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.util.List;

public class DropdownWidget extends AbstractWidget {
	private final List<Component> options;

	public DropdownWidget(List<Component> options, int x, int y, int width, int height, Component narrationTitle) {
		super(x, y, width, height, narrationTitle);
		this.options = options;
	}

	@Override
	protected void renderWidget(GuiGraphics transform, int mouseX, int mouseY, float partialTick) {

	}

	@Override
	protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

	}
}
