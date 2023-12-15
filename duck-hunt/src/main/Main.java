// ==========================================
//               Main Class
//  Author: Rachel Quedding
//  Purpose: Solely contains the main method...
// ==========================================

package main;

import main.GamePanel;
import javax.swing.*;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        // ----------------------- INITIALIZING THE OBJECTS -----------------------
        JFrame window = new JFrame(); // creates a frame
        GamePanel gamePanel = new GamePanel(); // creates game panel
        String frameIconPath = "/res/menu/frame icon.png"; // path for the frame icon
        ImageIcon frameIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource(frameIconPath))); // creating icon for the frame
        //

        // ----------------------- WINDOW SETTINGS -----------------------
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // lets the user close the window when you hit x
        window.setResizable(false); // forbids user from resizing window
        window.setTitle("Duck Hunt"); /// sets title
        window.setIconImage(frameIcon.getImage()); // setting the icon of the frame

        window.add(gamePanel); // displays everything
        window.pack(); // causes window to be sized to fit the preferred size and layouts of its subcomponents (game panel)
        window.setLocationRelativeTo(null); // displays window at the center of the screen
        window.setVisible(true); // can see the window

        // calling start game thread method to start the game
        gamePanel.setupGame();
        gamePanel.startGameThread();
       
        
    }
}