package com.hexagram2021.vcma.gui.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import static com.hexagram2021.vcma.VillagersComeMoreAlive.MODID;

/**
 * 下拉选择组件，用于在 GUI 中展示可选项列表并处理用户选择喵~
 *
 * @author liudongyu
 */
public class DropdownWidget extends AbstractWidget {
	private static final ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/gui/dropdown.png");
	private static final int WIDTH = 80;
	private static final int OPTION_HEIGHT = 12;
	private static final int TEXT_Y_OFFSET = 1;

	private final List<DropdownOption> options;
	@Nullable
	private UUID villager;

	/**
	 * 构造一个下拉选择组件喵~
	 *
	 * @param options         下拉列表中可选的文本选项列表喵~
	 * @param x               组件在屏幕上的 X 坐标喵~
	 * @param y               组件在屏幕上的 Y 坐标喵~
	 * @param narrationTitle  无障碍叙述标题喵~
	 */
	public DropdownWidget(List<DropdownOption> options, int x, int y, Component narrationTitle) {
		super(x, y, WIDTH, OPTION_HEIGHT * options.size(), narrationTitle);
		this.options = options;
	}

	/**
	 * 渲染下拉组件，根据鼠标悬停状态和激活状态绘制不同背景色喵~
	 *
	 * @param transform    GUI 渲染上下文喵~
	 * @param mouseX       鼠标 X 坐标喵~
	 * @param mouseY       鼠标 Y 坐标喵~
	 * @param partialTick  部分 tick 时间喵~
	 */
	@Override
	protected void renderWidget(GuiGraphics transform, int mouseX, int mouseY, float partialTick) {
		PoseStack pose = transform.pose();
		pose.pushPose();
		transform.pose().translate(0.0F, 0.0F, 400.0F);
		for(int i = 0; i < this.options.size(); ++i) {
			int difference = 0;
			if(mouseX >= this.getX() && mouseY >= this.getY() + i * OPTION_HEIGHT &&
					mouseX < this.getX() + this.width &&
					mouseY < this.getY() + i * OPTION_HEIGHT + OPTION_HEIGHT) {
				difference = OPTION_HEIGHT;
			} else if(!this.active) {
				difference = OPTION_HEIGHT * 2;
			}
			transform.blit(
					TEXTURE, this.getX(), this.getY() + i * OPTION_HEIGHT,
					0.0F, difference,
					this.width, OPTION_HEIGHT,
					128, 64
			);
			transform.drawString(Minecraft.getInstance().font, this.options.get(i).getText(), this.getX(), this.getY() + i * OPTION_HEIGHT + TEXT_Y_OFFSET, 0xFFFFFF);
		}
		pose.popPose();
	}

	/**
	 * 更新无障碍叙述信息，当前无需实现喵~
	 *
	 * @param narrationElementOutput 叙述输出喵~
	 */
	@Override
	protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
		// do nothing
	}

	/**
	 * 处理鼠标点击事件，选中对应选项并执行其动作，随后隐藏下拉组件喵~
	 *
	 * @param mouseX 鼠标 X 坐标喵~
	 * @param mouseY 鼠标 Y 坐标喵~
	 */
	@Override
	public void onClick(double mouseX, double mouseY) {
		super.onClick(mouseX, mouseY);

		if(this.isMouseOver(mouseX, mouseY)) {
			for (int i = 0; i < this.options.size(); ++i) {
				if (mouseY >= this.getY() + i * OPTION_HEIGHT && mouseY <= this.getY() + i * OPTION_HEIGHT + OPTION_HEIGHT){
					this.options.get(i).run(this);
					break;
				}
			}
		}
		this.active = false;
		this.visible = false;
	}

	/**
	 * 设置当前关联的村民 UUID 喵~
	 *
	 * @param villager 村民 UUID，可为空喵~
	 */
	public void setVillager(@Nullable UUID villager) {
		this.villager = villager;
	}

	/**
	 * 获取当前关联的村民 UUID 喵~
	 *
	 * @return 村民 UUID，可能为空喵~
	 */
	@Nullable
	public UUID villager() {
		return this.villager;
	}

	/**
	 * 下拉选项，包含展示文本和点击后的回调动作喵~
	 */
	public static class DropdownOption {
		private final Component text;
		private final Consumer<DropdownWidget> action;

		/**
		 * 构造一个下拉选项喵~
		 *
		 * @param text   展示文本喵~
		 * @param action 点击回调喵~
		 */
		private DropdownOption(Component text, Consumer<DropdownWidget> action) {
			this.text = text;
			this.action = action;
		}

		/**
		 * 获取选项展示文本喵~
		 *
		 * @return 展示文本组件喵~
		 */
		public Component getText() {
			return this.text;
		}

		/**
		 * 执行选项的回调动作喵~
		 *
		 * @param parent 所属的下拉组件喵~
		 */
		public void run(DropdownWidget parent) {
			this.action.accept(parent);
		}

		/**
		 * 创建下拉选项的静态工厂方法喵~
		 *
		 * @param text   展示文本喵~
		 * @param action 点击回调喵~
		 * @return 创建的下拉选项喵~
		 */
		public static DropdownOption of(Component text, Consumer<DropdownWidget> action) {
			return new DropdownOption(text, action);
		}
	}
}
