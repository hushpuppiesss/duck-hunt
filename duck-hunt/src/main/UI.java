package main;

import object.OBJ_Boba;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    // font
    Font arial_40;

    // images
    BufferedImage bobaImage;

    // whether we are showing message or not
    public boolean messageOn = false;
    public String message = "";
    // for message disappearing
    int messageCounter = 0;


    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        OBJ_Boba boba = new OBJ_Boba();
        bobaImage = boba.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw (Graphics2D g2) {

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        // ----------------------- DRAWING MESSAGE -----------------------
        if (messageOn) {

            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message,gp.tileSize/2, gp.tileSize * 5);

            messageCounter++;

            if (messageCounter > 120)
            {
                messageCounter = 0;
                messageOn = false;
            }
        }


    }
}
