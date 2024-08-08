package net.mcreator.brokenblocktooltip.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.brokenblocktooltip.network.BrokenBlockTooltipModVariables;

public class ToogleBrokenBlockMessageProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			boolean _setval = !(entity.getCapability(BrokenBlockTooltipModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BrokenBlockTooltipModVariables.PlayerVariables())).show_broken_block_message;
			entity.getCapability(BrokenBlockTooltipModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.show_broken_block_message = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if ((entity.getCapability(BrokenBlockTooltipModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new BrokenBlockTooltipModVariables.PlayerVariables())).show_broken_block_message) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Broken block message \u00A72Activated\u00A7r"), true);
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Broken block message \u00A74Deactivated\u00A7r"), true);
		}
	}
}
