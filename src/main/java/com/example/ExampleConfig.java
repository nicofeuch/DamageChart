package com.example;

import net.runelite.client.RuneLite;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.example.ExamplePlugin;

@ConfigGroup("example")
public interface ExampleConfig extends Config
{


//	// Config item for the button
	@ConfigItem(
			keyName = "showDPS",
			name = "Show DPS",
			description = "Shows DPS"
	)
	default boolean addDpsButton()
	{
		return true;
	}
	@ConfigItem(
			keyName = "showDamage",
			name = "Show Damage",
			description = "Shows damage"
	)
	default boolean addDamageButton()
	{
		return true;
	}
}


