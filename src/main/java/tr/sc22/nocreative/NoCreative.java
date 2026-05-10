package tr.sc22.nocreative;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;

public class NoCreative implements ModInitializer {

	@Override
	public void onInitialize() {

		ServerTickEvents.END_SERVER_TICK.register(server -> {

			for (ServerPlayer player : server.getPlayerList().getPlayers()) {

				if (player.gameMode.getGameModeForPlayer() == GameType.CREATIVE) {

					player.setGameMode(GameType.SURVIVAL);

					player.sendSystemMessage(
							net.minecraft.network.chat.Component.literal(
									"Creative mode disabled."
							)
					);
				}
			}
		});
	}
}