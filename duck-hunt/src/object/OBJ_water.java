// ==========================================
//               OBJ_water Class
//  Author: Marty McGill
//  Purpose: Projectile that the duck shoots
// ==========================================


package object;

import entity.Projectile;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class OBJ_water extends Projectile {

    GamePanel gp;
    public OBJ_water(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "water";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();

    }

    public void getImage() {
        down1 = setup("/res/projectile/water.png", gp.tileSize,gp.tileSize);
        left1 = setup("/res/projectile/waterleft.png", gp.tileSize,gp.tileSize);
        right1 = setup("/res/projectile/waterright.png", gp.tileSize,gp.tileSize);
        up1 = setup("/res/projectile/waterup.png", gp.tileSize,gp.tileSize);
    }

    private BufferedImage setup(String s, int tileSize, int tileSize1){

        return null;
    }

}
