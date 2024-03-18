package com.example.ui;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.example.ExamplePlugin;

import lombok.Getter;

import javax.swing.*;

@Getter
public class ControlsPanel extends JPanel {

    JButton createParty = new JButton("Create Party");
    JButton joinParty = new JButton("Join Party");
    JButton leaveParty = new JButton("Leave Party");
    JButton copyPassphrase = new JButton("Copy Passphrase");

    ExamplePlugin plugin;

    public ControlsPanel(ExamplePlugin plugin){
        this.plugin = plugin;
        this.setLayout(new GridLayout());

        this.add(createParty);
        this.add(joinParty);
        this.add(leaveParty);
        this.add(copyPassphrase);

    }



}
