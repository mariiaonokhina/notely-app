package com.notely;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class NoteBar extends JPanel {
    private JLabel myNotesLabel;
    private JButton addNewNoteBtn;
    private File files;
    private File[] fileArr;
    private static LinkedList<Note> notes = new LinkedList<>();

    /**
     * Toolbar holds all notes created by the user.
     */
    public NoteBar(String path) {
        myNotesLabel = new JLabel("My Notes:");
        addNewNoteBtn = new JButton("+ New Note");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(myNotesLabel);
        add(addNewNoteBtn);

        files = new File(path);

        if (files.exists() && files.isDirectory()) {
            // Get notes
            fileArr = files.listFiles();
            populateNotes(fileArr, 0, 0);

            // Populate all notes
            for(Note note: notes) {
                add(new JLabel(note.getFileName()));
            }
        }
        else {
            add(new JLabel("No notes yet."));
        }

        // Add new file
        addNewNoteBtn.addActionListener(e -> {
            try {
                String fileName = "newFile.txt";
                File newFile = new File(files.toString() + "/" + fileName);

                if (newFile.createNewFile()) {
                    this.add(new JLabel(fileName));
                    notes.addFirst(new Note(fileName));
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException err) {
                System.out.println("An error occurred.");
                err.printStackTrace();
            }
        });
    }

    /**
     * This function populates notes in the note bar.
     * @param fileArr Array of files in a directory.
     * @param num File number.
     * @param level Directory level.
     */
    public static void populateNotes(File[] fileArr, int num, int level) {
        // Base case, means there are no more files
        if (num == fileArr.length) return;

        StringBuilder currLabel = new StringBuilder();

        for (int j = 0; j < level; level++) {
            currLabel.append('\t');
        }

        if (fileArr[num].isFile()) {
            currLabel.append(fileArr[num].getName());
        }
        else if (fileArr[num].isDirectory()) {
            // Print the name of the directory
            currLabel.append("[" + fileArr[num].getName() + "]");
            // Recursively print all subdirectories
            populateNotes(fileArr[num].listFiles(), 0, level + 1);
        }

        notes.addFirst(new Note(currLabel.toString()));
        populateNotes(fileArr, num + 1, level);
    }
}