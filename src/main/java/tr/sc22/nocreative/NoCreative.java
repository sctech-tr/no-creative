package tr.sc22.nocreative;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.minecraft.network.chat.Component;

public class NoCreative implements ModInitializer {

	@Override
	public void onInitialize() {

		ServerTickEvents.END_SERVER_TICK.register(server -> {

			GameType worldMode = server.getWorldData().getGameType();
			boolean hardcore = server.getWorldData().isHardcore();

			if (worldMode == GameType.SURVIVAL || hardcore) {

				for (ServerPlayer player : server.getPlayerList().getPlayers()) {

					if (player.gameMode.getGameModeForPlayer() == GameType.CREATIVE) {

						player.setGameMode(GameType.SURVIVAL);

						player.sendSystemMessage(
								Component.literal("Creative disabled in this world."),
								false
						);
					}
				}
			}
		});
	}
}