package com.example;

import net.runelite.client.RuneLite;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;
import com.example.ExamplePlugin;

@ConfigGroup("example")
public interface ExampleConfig extends Config
{
	@ConfigItem(

			keyName = "damageDealt",
			name = "Damage Dealt",
			description = "Displays damage dealt by the player"

	)
	default int damageDealt()
	{
		ExamplePlugin plugin = new ExamplePlugin(); // Get the plugin instance
		return plugin.getMyDamageDealt(); // Access the damage dealt field
	}


	@ConfigItem(
			keyName = "greeting",
			name = "Welcome Greeting",
			description = "The message to show to the user when they login"
	)
	default String greeting()
	{
		return "SHHHHHHHHHH";
	}

	@ConfigItem(
			position = 1,
			keyName = "booleanConfig",
			name = "Boolean Checkbox",
			description = "Simple example of a checkbox to store a boolean value"
	)
	default boolean booleanConfig() {
		return false;
	}

	@ConfigItem(
			position = 2,
			keyName = "intConfig",
			name = "Int Spinner",
			description = "Simple example of a spinner to store integers"
	)
	default int intConfig() {
		return 1;
	}

	@ConfigItem(
			position = 3,
			keyName = "stringConfig",
			name = "String Textbox",
			description = "Simple example of a textbox to store strings"
	)
	default String stringConfig() {
		return "";
	}

	@ConfigItem(
			position = 4,
			keyName = "colorConfig",
			name = "Color Selector",
			description = "Simple example of a color selector"
	)
	default Color colorConfig() {
		return Color.GREEN;
	}

	enum OptionEnum {
		OPTION1,
		OPTION2,
		OPTION3
	}

	@ConfigItem(
			position = 5,
			keyName = "enumConfig",
			name = "Combobox Enum",
			description = "Simple example of a combobox to select from a list"
	)
	default OptionEnum enumConfig() {
		return OptionEnum.OPTION1;
	}

	@ConfigItem(
			position = 6,
			keyName = "dimensionConfig",
			name = "Int Dimension",
			description = "Simple example of a dimension to select to integer values"
	)
	default Dimension dimensionConfig() {
		return new Dimension(765, 503);
	}
}
