package com.notely;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class NoteBar extends JPanel {
    private JLabel myNotesLabel;
    private JButton addNewNoteBtn;
    private static File files;
    private File[] fileArr;
    private static LinkedList<Note> notes = new LinkedList<>();

    private static int noteNum = 1;

    private NewNoteListener newNoteListener;
    private FileOpenedListener fileOpenedListener;

    /**
     * Toolbar holds all notes created by the user.
     */
    public NoteBar(String path) {
        myNotesLabel = new JLabel("My Notes:");
        addNewNoteBtn = new JButton("+ New Note");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.lightGray);

        add(myNotesLabel);
        add(addNewNoteBtn);

        files = new File(path);

        if (files.exists() && files.isDirectory()) {
            // Get notes
            fileArr = files.listFiles();
            populateNotes(fileArr, 0, 0);

            // Populate all notes
            for(Note note: notes) {
                JButton btn = new JButton(note.getFileName());
                add(btn);
                btn.addActionListener((e -> fileOpenedListener.fileOpened(note.getFileName())));
            }
        }
        else {
            add(new JLabel("No notes yet."));
        }

        // Add new file
        addNewNoteBtn.addActionListener(e -> addNewNote());
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

    /**
     * Adds new note to the note bar.
     */
    public boolean addNewNote() {
        try {
            File newFile = new File(files.toString() + "/" + "text" + noteNum + ".txt");
            if (newFile.createNewFile()) {
                notes.addFirst(new Note("text" + noteNum + ".txt"));
                add(new JLabel("text" + noteNum + ".txt"));

                if (newNoteListener != null) {
                    newNoteListener.newNoteCreated(true);
                }

                revalidate();
                repaint();

                noteNum++;

                return true;
            } else {
                System.out.println("File already exists.");

                if (newNoteListener != null) {
                    newNoteListener.newNoteCreated(false);
                }

                return false;
            }
        } catch (IOException err) {
            System.out.println("An error occurred.");
            err.printStackTrace();
            return false;
        }
    }

    public void setNewNoteListener(NewNoteListener listener) {
        this.newNoteListener = listener;
    }

    public void setFileOpenedListener(FileOpenedListener listener) { this.fileOpenedListener = listener; }
}