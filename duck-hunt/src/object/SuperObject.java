// ==========================================
//              SuperObject Class
//  Author: Liyuan Hu
//  Purpose: Creates objects
// ==========================================

package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image,image2,image3;

    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    //sets how big the object is
    public Rectangle solidArea = new Rectangle(0,0,64,64);
    public int SolidAreaDefaultx = 0;
    public int SolidAreaDefaulty = 0;
    
    public void draw(Graphics2D g2, GamePanel gp) {
        // WHERE ON THE SCREEN we need to draw it by calculating the distance
            // relative to the player
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // improving rendering efficiency to only draw tiles that are within our game window (with margin area_
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
            {
                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
        

    }
    /*add in tile manager
    OBJECT
    for(int i = 0; i <obj.length;i++){
        if(obj[i] != null){
            obj[i].draw(g2,this);
        }
    }
    */

}
