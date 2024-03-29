package com.example;
import net.runelite.client.party.PartyService;
import net.runelite.client.ui.DynamicGridLayout;
import net.runelite.client.ui.PluginPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import com.google.inject.Inject;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import net.runelite.client.callback.ClientThread;


import net.runelite.api.Client;

public class GroupPanel extends PluginPanel {
    @Inject
    private Client client;
    private final ExamplePlugin plugin;
    private final JPanel basePanel = new JPanel();

    private final JPanel dpsPanel = new JPanel();
    private final JLabel dpsLabel = new JLabel();
    JLabel passphraseLabel = new JLabel();

    // Buttons
    JButton createPartyButton = new JButton("Create Party");
    JButton joinParty = new JButton("Join Party");
    JButton leaveParty = new JButton("Leave Party");
    JButton copyPassphrase = new JButton("Copy Passphrase");

    PartyService wsClient;


    @Inject
    public GroupPanel(ExamplePlugin plugin) {
        super(false);
        this.plugin = plugin;
        this.setLayout(new BorderLayout());


        basePanel.setBorder(new EmptyBorder(BORDER_OFFSET, BORDER_OFFSET, BORDER_OFFSET, BORDER_OFFSET));
        basePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = GridBagConstraints.RELATIVE; // Align to the left
        gbc.fill = GridBagConstraints.HORIZONTAL;




        gbc.gridy = 0;
        basePanel.add(createPartyButton, gbc);
        gbc.gridy = 1;
        basePanel.add(joinParty, gbc);
        gbc.gridy = 3;
        basePanel.add(leaveParty, gbc);
        gbc.gridy = 4;
        basePanel.add(copyPassphrase, gbc);

        gbc.gridy = 5;
        passphraseLabel.setText("Passphrase will generate here");
        basePanel.add(passphraseLabel, gbc);

        gbc.gridy = 7;
        gbc.weightx = 1.0; // Make the base panel horizontally resizable
        gbc.weighty = 0.0; // Make the base panel's height fixed

        dpsPanel.setBorder(new EmptyBorder(4, 0, 0, 0));
        dpsPanel.setLayout(new DynamicGridLayout(0, 1, 0, 5));
        dpsPanel.setBackground(Color.BLACK);

        dpsLabel.setText("1. Stealthshad0 DPS: 0  Damage: 0");
        dpsPanel.add(dpsLabel);

        basePanel.add(dpsPanel, gbc);



        this.add(basePanel, BorderLayout.CENTER);

        // Add event listeners
        createPartyButton.addActionListener(e -> plugin.createParty());
        joinParty.addActionListener(e -> {
            String s = (String) JOptionPane.showInputDialog(
                    joinParty,
                    "Enter Passphrase",
                    "Party Passphrase",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "");

            if (s == null)
            {
                return;
            }
            plugin.wsClientPartyService.changeParty(s);
        });
        copyPassphrase.addActionListener(e -> {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(new StringSelection(plugin.getPartyPassphrase()), null);
        });
        leaveParty.addActionListener(e -> {
            plugin.wsClientPartyService.changeParty(null);
        });


    }
    public JLabel getDpsLabel() { return dpsLabel; }
    public JPanel getBasePanel(){ return basePanel; }
    public JLabel getPassphraseLabel(){ return passphraseLabel; }



}
