package com.notely;

public class Note {
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
}
