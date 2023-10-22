package com.notely;

import javax.swing.*;

public class Note extends JButton {
    private String fileName;
    private String content;

    public Note(String fileName) {
        this.fileName = fileName;
    }

    public Note(String fileName, String content) {
            this.fileName = fileName;
            this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContent() {
        return content;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
