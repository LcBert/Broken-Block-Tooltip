
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.brokenblocktooltip.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.mcreator.brokenblocktooltip.network.BrokenBlockToogleMessageKeybindMessage;
import net.mcreator.brokenblocktooltip.BrokenBlockTooltipMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class BrokenBlockTooltipModKeyMappings {
	public static final KeyMapping BROKEN_BLOCK_TOOGLE_MESSAGE_KEYBIND = new KeyMapping("key.broken_block_tooltip.broken_block_toogle_message_keybind", GLFW.GLFW_KEY_L, "key.categories.ui") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				BrokenBlockTooltipMod.PACKET_HANDLER.sendToServer(new BrokenBlockToogleMessageKeybindMessage(0, 0));
				BrokenBlockToogleMessageKeybindMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(BROKEN_BLOCK_TOOGLE_MESSAGE_KEYBIND);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				BROKEN_BLOCK_TOOGLE_MESSAGE_KEYBIND.consumeClick();
			}
		}
	}
}
