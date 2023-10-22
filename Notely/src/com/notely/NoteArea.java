package com.notely;

import javax.swing.*;
import java.awt.*;

public class NoteArea extends JTextArea {
    private StringBuilder content;
    private int rows = 100;
    private int columns = 100;


    public NoteArea(StringBuilder content, int rows, int columns) {
        super(content.toString(), rows, columns);
        this.content = new StringBuilder(content);

        setFont(new Font("Serif", Font.ITALIC, 16));
        setLineWrap(true);
        setWrapStyleWord(true);

        // Changes have been made to a note
//        addActionListener
    }



    public String getNoteContent() {
        System.out.println(content.toString());
        return content.toString();
    }
}
