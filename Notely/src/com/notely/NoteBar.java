package com.notely;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NoteBar extends JPanel {
    private JLabel myNotesLabel;
    private JButton addNewNoteBtn;

    /**
     * Toolbar holds all notes created by the user.
     */
    public NoteBar() {
        myNotesLabel = new JLabel("My Notes:");
        addNewNoteBtn = new JButton("+ New Note");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(myNotesLabel);
        add(addNewNoteBtn);
    }
}
