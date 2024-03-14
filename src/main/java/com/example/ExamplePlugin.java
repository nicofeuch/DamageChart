package com.example;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Hitsplat;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.api.events.OverheadTextChanged;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.awt.*;

@Slf4j
@PluginDescriptor(
	name = "pp"
)
public class ExamplePlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ExampleConfig config;

	private int myDamageDealt;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");
		myDamageDealt = 0;
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
		System.out.println("Total damage dealt: " + myDamageDealt);
	}

	@Subscribe
	public void onPlayerHit(HitsplatApplied event)
	{
		//Check if the hit was dealt by the player
		if (event.getActor() != client.getLocalPlayer()) {
			return;
		}
		final Hitsplat hitSplat = event.getHitsplat();


		int damage = hitSplat.getAmount();
		myDamageDealt += damage;
		// You can do further processing with the damage value here
		System.out.println("Player dealt " + damage + " damage.");

	}

	// Getter method for myDamageDealt
	public int getMyDamageDealt()
	{
		return myDamageDealt;
	}



	@Provides
	ExampleConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ExampleConfig.class);
	}
}
