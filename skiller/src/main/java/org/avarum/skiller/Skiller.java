package org.avarum.skiller;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import org.pf4j.Extension;

@Extension
@PluginDescriptor(
	name = "Skiller",
	description = "does skill things"
)
@Slf4j
public class Skiller extends Plugin {
	// Injects our config
	@Inject
	private SkillerConfig config;

	@Inject
	private Client client;

	// Provides our config
	@Provides
	SkillerConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(SkillerConfig.class);
	}

	@Override
	protected void startUp() {
		// runs on plugin startup
		log.info("Plugin started");

		// example how to use config items
		if (config.example()) {
			// do stuff
			log.info("The value of 'config.example()' is ${config.example()}");
		}
	}

	@Override
	protected void shutDown() {
		// runs on plugin shutdown
		log.info("Plugin stopped");
	}

	@Subscribe
	private void onGameTick(GameTick gameTick) {
		// runs every gametick
		log.info("Gametick");
	}

	@Subscribe
	private void onGameStateChanged(GameStateChanged gameStateChanged){
		if(gameStateChanged.getGameState() == GameState.LOGGED_IN) {
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Skiller is loaded", null);
		}
	}
}