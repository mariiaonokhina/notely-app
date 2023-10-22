package com.notely;

import javax.swing.*;
import java.awt.*;

public class CreateFilePanel extends JPanel {
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton createBtn;

    public CreateFilePanel() {
        nameLabel = new JLabel("Name: ");
        nameField = new JTextField(50);
        createBtn = new JButton("Create");

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(nameLabel);
        add(nameField);
        add(createBtn);
    }
}
