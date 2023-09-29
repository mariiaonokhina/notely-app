package com.notely;

import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    private NoteBar noteBar;
    private JButton addNewBtn;

    /**
     Constructor for the MainFrame of Notely app setting all necessary settings, such as window title, size, layout, etc.
     */
    public MainFrame(String title, int width, int height) {
        super(title);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);

        setLayout(new BorderLayout());
        mainPanel = new MainPanel();
        noteBar = new NoteBar();
        addNewBtn = new JButton("Add New");

        add(noteBar, BorderLayout.LINE_START);
        add(mainPanel, BorderLayout.CENTER);
    }

}
