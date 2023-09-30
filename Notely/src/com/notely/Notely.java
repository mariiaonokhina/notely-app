package com.notely;

import javax.swing.*;
import java.awt.*;

/**
Note-taking app
 */
public class Notely {
    // Get user's screen dimensions to make the app fullscreen
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int width = (int) screenSize.getWidth();
    private static int height = (int) screenSize.getHeight();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Set up the window
            new MainFrame("Notely", 500, 500);
        });
    }
}