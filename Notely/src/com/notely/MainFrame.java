package com.notely;

import java.io.*;
import java.net.URL;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    private NoteBar noteBar;

    private URL resourceUrl = MainFrame.class.getResource("/");
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

        if (resourceUrl != null) {
            File notesFolder = new File(resourceUrl.getFile(), "notes");
            String filePath = notesFolder.getAbsolutePath();
            noteBar = new NoteBar(filePath);
        }   else {
            System.exit(1);
        }

        add(noteBar, BorderLayout.LINE_START);
        add(mainPanel, BorderLayout.CENTER);
    }
}
