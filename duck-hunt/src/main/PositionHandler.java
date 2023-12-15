// ==========================================
//          Position Handler Class
//  Author: Liyuan Hu
//  Purpose:
// ==========================================

package main;

import object.OBJ_Boba;
import object.OBJ_Broccoli;
import object.OBJ_Pot;
import object.OBJ_Pudding;

public class PositionHandler {
    GamePanel gp;
// receive game panel
    public PositionHandler(GamePanel gp){
        this.gp = gp;
    }
    // in game panel also initiate this class
    // public PositionHandler PosH = new PositionHandler(this)
    public void setObject(){
        //pulling it from the subclass into an array
        gp.obj[0]=new OBJ_Boba();
        //placing it in the coordinates on the map
        //maybe change to random in the future?
        gp.obj[0].worldX = 19 * gp.tileSize;
        gp.obj[0].worldY = 18 * gp.tileSize;

        //a pudding
        gp.obj[2]=new OBJ_Pudding();
        gp.obj[2].worldX = 43 * gp.tileSize;
        gp.obj[2].worldY = 44 * gp.tileSize;

        // a broccoli
        gp.obj[1]=new OBJ_Broccoli();
        gp.obj[1].worldX = 15 * gp.tileSize;
        gp.obj[1].worldY = 6 * gp.tileSize;

        // a pot
        gp.obj[3] = new OBJ_Pot();
        gp.obj[3].worldX = 12 * gp.tileSize;
        gp.obj[3].worldY = 46 * gp.tileSize;
    }
}


    /*Call setObject in game pannel
    public void setupGame(){
        PosH.PositionHandler()；
    }
    run  gamePanel.setupGame() in main method before the panel
    */


