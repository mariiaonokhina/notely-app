package com.notely;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private JLabel notelyLabel;
    private JLabel descriptionLabel;

    public WelcomePanel() {
        notelyLabel = new JLabel("Notely");
        descriptionLabel = new JLabel("Click on an existing note or add a new note to get started.");

        setLayout(new BorderLayout());

        add(notelyLabel, BorderLayout.NORTH);
        add(descriptionLabel, BorderLayout.CENTER);
    }
}