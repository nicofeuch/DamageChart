package com.example;

import com.google.inject.Provides;
import net.runelite.api.events.OverheadTextChanged;
import net.runelite.client.RuneLite;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.externalplugins.ExternalPluginManager;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import java.awt.*;

@PluginDescriptor(
		name = "Config Example",
		description = "A simple example plugin to illustrate how to write plugin config menus",
		tags = {"config", "menu"},
		loadWhenOutdated = true,
		enabledByDefault = false
)
public class ExamplePluginTest
{
	@Inject
	private ExamplePluginTest config;

	public static void main(String[] args) throws Exception
	{

		ExternalPluginManager.loadBuiltin(ExamplePlugin.class);
		RuneLite.main(args);


	}

}