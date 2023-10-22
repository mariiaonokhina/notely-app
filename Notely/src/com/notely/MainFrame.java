package com.notely;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainFrame extends JFrame {
    private WelcomePanel welcomePanel;
    private NoteBar noteBar;

    private CreateFilePanel createFilePanel;
    private NoteArea noteArea;

    private boolean newNoteWasCreated = false;
    private String fileOpened = "";
    private String currFileChanges = "";

    private URL resourceUrl = MainFrame.class.getResource("/");
    private File notesFolder = new File(resourceUrl.getFile(), "notes");
    private String filePath = notesFolder.getAbsolutePath();
    /**
     Constructor for the MainFrame of Notely app setting all necessary settings, such as window title, size, layout, etc.
     */
    public MainFrame(String title, int width, int height) {
        super(title);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);

        setLayout(new BorderLayout());
        welcomePanel = new WelcomePanel();

        if (resourceUrl != null) {
            noteBar = new NoteBar(filePath);
        }   else {
            System.exit(1);
        }

        createFilePanel = new CreateFilePanel();

        // The user has created a new note
        noteBar.setNewNoteListener(b -> {
            newNoteWasCreated = b;

            if (newNoteWasCreated) {
                getContentPane().removeAll();
                add(noteBar, BorderLayout.LINE_START);
                add(createFilePanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        // The user has opened a file
        noteBar.setFileOpenedListener(fileName -> {
            fileOpened = fileName;
            try {
                File openedNote = new File(filePath + "/" + fileName);
                FileReader fr = new FileReader(openedNote);
                BufferedReader br = new BufferedReader(fr);
                StringBuilder noteContent = new StringBuilder();

                while(br.ready()){
                    noteContent.append(br.readLine() + "\n");
                }

                noteArea = new NoteArea(noteContent, 100, 100);

                if (!Objects.equals(fileOpened, "")) {
                    getContentPane().removeAll();
                    add(noteBar, BorderLayout.LINE_START);
                    add(noteArea, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                }
            }   catch (FileNotFoundException e) {
                System.err.println(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        add(noteBar, BorderLayout.LINE_START);
        add(welcomePanel, BorderLayout.CENTER);

        // The user closed the application
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent event)
            {
                if (!Objects.equals(fileOpened, "")) {
                    File openedNote = new File(filePath + "/" + fileOpened);
                    try{
                        FileWriter fw = new FileWriter(openedNote,false);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.newLine();
                        bw.write(noteArea.getNoteContent());
                        bw.flush();
                        bw.close();
                    }
                    catch(Exception err){
                        System.out.println(err);
                    }
                }
                event.getWindow().dispose();
            }
        });
    }
}
