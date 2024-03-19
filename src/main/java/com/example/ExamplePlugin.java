package com.example;

import com.example.data.events.PartyBatchedChange;
import com.google.inject.Provides;

import javax.inject.Inject;
import javax.swing.*;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Actor;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.SpriteManager;
import net.runelite.client.party.PartyService;
import net.runelite.client.party.WSClient;
import net.runelite.client.party.messages.PartyMessage;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginManager;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.util.ImageUtil;
import net.runelite.client.party.messages.PartyMemberMessage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Instant;
import java.util.*;

import static java.lang.Math.round;

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

	@Inject
	private ClientThread clientThread;
	@Inject
	private ClientToolbar clientToolbar;

	@Inject
	private PluginManager pluginManager;

	@Inject
	SpriteManager spriteManager;

	@Inject
	private WSClient wsClient;
	@Inject
	PartyService wsClientPartyService;
	@Inject
	GroupPanel groupPanel;

	private int myDamageDealt;
	private String userName;
	private Map<String, Integer> partyMembers = new HashMap<>();
	String passphrase = "";
	NavigationButton navButton;
	//boolean addedButton = true;



	Instant lastLogout;
	private PluginPanel panel;

	public ExamplePlugin() {
	}


	final BufferedImage ICON = ImageUtil.loadImageResource(ExamplePlugin.class, "/images/newHitSplat.png");

	@Override
	protected void startUp() throws Exception
	{
		panel = new GroupPanel(this);
		navButton = NavigationButton.builder()
				.tooltip("p panel")
				.icon(ICON)
				.priority(1)
				.panel(panel)
				.build();

		clientToolbar.addNavigation(navButton);

		wsClient.registerMessage(PartyMemberMessage.class);

	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
		clientToolbar.removeNavigation(navButton);
	}


	@Provides
	ExampleConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ExampleConfig.class);
	}
	@Subscribe
	public void onGameTick(final GameTick tick){
		if (!wsClientPartyService.isInParty()){
			return;
		}

		if (client.getTickCount() % 5 != 0){
			return;
		}

		PartyBatchedChange stuff = new PartyBatchedChange(); // Initialize stuff
		stuff.setPlayerName("RawwrRob");
		stuff.setDamageDealt(69);
		stuff.setMemberId(54545454);
		wsClientPartyService.send(stuff);

		clientThread.invokeLater(() -> {
			client.getLocalPlayer().setOverheadText(String.valueOf(stuff.getMemberId()));
		});


	}

	@Subscribe
	public void onHitsplatApplied(HitsplatApplied event) {

		Actor player = client.getLocalPlayer();
		Actor hitSplatSource = event.getActor();



		// Check if hitsplat is applied to the player
		if (((NPC) hitSplatSource).getInteracting() == player) {
			int hitsplatValue = event.getHitsplat().getAmount();
			int hitsplatType = event.getHitsplat().getHitsplatType();
			myDamageDealt += hitsplatValue;

			double dps = round(myDamageDealt / (client.getTickCount()*0.6) * 100.0)/100.0;

			//client.getLocalPlayer().setOverheadText(String.valueOf(myDamageDealt));
			//client.getLocalPlayer().setOverheadText(String.valueOf(event.getActor().getName()));

			clientThread.invokeLater(() -> {
				JLabel dpsLabel = ((GroupPanel) panel).getDpsLabel();
				dpsLabel.setText("1. Stealthshad0 DPS: " + dps + " " + "Damage: " + myDamageDealt);


				// Repaint the panel to reflect changes
				panel.repaint();
			});

		}
	}
	void createParty() {
		clientThread.invokeLater(() -> {
			passphrase = wsClientPartyService.generatePassphrase(); // Generate passphrase
			JLabel passphraseLabel = ((GroupPanel) panel).getPassphraseLabel();
			passphraseLabel.setText(passphrase);
			// Repaint the panel to reflect changes
			panel.repaint();
		});
	}

	public String getPartyPassphrase() {
		return passphrase;
	}
	public int getMyDamageDealt(){
		return myDamageDealt;
	}
	public String getUserName(){
		return userName;
	}
	public void addPartyMember(String userName, int myDamageDealt){
		partyMembers.put(userName, myDamageDealt);
	}
}
