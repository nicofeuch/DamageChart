package com.example;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;


public class MyControlPanel extends JPanel{

    private static final String BTN_CREATE_TEXT = "Create party";
    private static final String BTN_LEAVE_TEXT = "Leave party";

    private final JButton startButton = new JButton();
    private final JButton joinPartyButton = new JButton();
    private final JButton rejoinPartyButton = new JButton();
    private final JButton copyPartyIdButton = new JButton();

    private final ExamplePlugin plugin;

    public MyControlPanel(ExamplePlugin plugin)
    {

        this.plugin = plugin;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 2, 4, 2);

        c.gridx = 0;
        c.gridy = 0;
        this.add(startButton, c);

        c.gridx = 1;
        c.gridy = 0;
        this.add(joinPartyButton, c);

        c.gridx = 1;
        c.gridy = 0;
        this.add(copyPartyIdButton, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        this.add(rejoinPartyButton, c);

        startButton.setText("yoyo, sup, bro");
        startButton.setFocusable(false);

        joinPartyButton.setText("Join party");
        joinPartyButton.setFocusable(false);

        rejoinPartyButton.setText("Join previous party");
        rejoinPartyButton.setFocusable(false);

        copyPartyIdButton.setText("Copy passphrase");
        copyPartyIdButton.setFocusable(false);

    }
}
