package com.notely;

import java.awt.*;

public class NoteArea extends TextArea {
    private StringBuilder content;
    private int rows = 100;
    private int columns = 100;

    public NoteArea(StringBuilder content, int rows, int columns) {
        super(content.toString(), rows, columns);
        this.content = new StringBuilder(content);

//        getDocument().addDocumentListener(new MyDocumentListener());
//        getDocument().putProperty("name", "Text Area");
    }

    public String getNoteContent() {
        return content.toString();
    }
}
