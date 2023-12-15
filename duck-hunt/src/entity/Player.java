
// ==========================================
//               Player Class
//  Author: Marty McGill
//  Purpose:
// ==========================================

package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Player extends Entity {

  GamePanel gp;
  KeyHandler keyH;

  // positions on the screen
  public final int screenX;
  public final int screenY;

  // counting the items
  int hasBoba = 0;
  int hasPud = 0;
  int hasBroco = 0;

  public Player(GamePanel gp, KeyHandler keyH) 
  {
    this.gp = gp;
    this.keyH = keyH;

    // camera set up
    // gp.screenWidth or gp.screenHeight ensure that the player will always be halfway vertically
    // and horizontally on the screen (the middle.) subtracting the gp.tileSize by 2 will ensure that
    // the player is actually in the middle of the screen rather than the left corner of the sprite
    // is in the middle of the screen.
    screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
    screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

    // player rectangle for collisions
    solidArea = new Rectangle();
    // top left coordinate of rectangle
    solidArea.x = 10;
    solidArea.y = 18;
    //record the default values 
    SolidAreaDefaultx = solidArea.x;
    SolidAreaDefaulty = solidArea.y;
    // dimensions of rectangle
    solidArea.width = 14 * gp.scale;
    solidArea.height = 15 * gp.scale;

    setDefaultValues();
    getPlayerImage();
  }

  public void setDefaultValues() 
  {
    // spawning player in the middleish of the map
    worldX = gp.tileSize * 25;
    worldY = 0;
    speed = 4;
    direction = "down";
  }

  // ==========================================
  //             loadImage(path) method
  //  Author: Rachel Quedding
  //  Purpose: The object, stream, is a way of
  //  loading images. It's stored into
  //  a singular method, so we call this method each
  //  time we want to load an image.
  // ==========================================
  private BufferedImage loadImage(String path) throws IOException
  {
    InputStream stream = Objects.requireNonNull(getClass().getResourceAsStream(path));
    /*
    objects.requirenonnull(), checks if something is null and throws an exception instead
    getclass() gets the player class
    getresource as stream(path) obtains smth at the specified path
     */
    return ImageIO.read(stream);
    // imageio.read() reads the image
  }

  public void getPlayerImage() {

    try
    {
      //up
      up0 = loadImage("/res/player/gooseback_walk0.png");
      up1 = loadImage("/res/player/gooseback_walk1.png");
      //down
      down0 = loadImage("/res/player/goosefront_walk0.png");
      down1 = loadImage("/res/player/goosefront_walk1.png");
      //left
      left0 = loadImage("/res/player/gooseleft_walk0.png");
      left1 = loadImage("/res/player/gooseleft_walk1.png");
      //right
      right0 = loadImage("/res/player/gooseright_walk0.png");
      right1 = loadImage("/res/player/gooseright_walk1.png");
      //up idle
      up0_idle = loadImage("/res/player/gooseback_idle0.png");
      up1_idle = loadImage("/res/player/gooseback_idle1.png");
      //down idle
      down0_idle = loadImage("/res/player/goosefront_idle0.png");
      down1_idle = loadImage("/res/player/goosefront_idle1.png");
      //left idle
      left0_idle = loadImage("/res/player/gooseleft_idle0.png");
      left1_idle = loadImage("/res/player/gooseleft_idle1.png");
      //right idle
      right0_idle = loadImage("/res/player/gooseright_idle0.png");
      right1_idle = loadImage("/res/player/gooseright_idle1.png");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void update() {

    if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)
    {
      // MOVING ANIMATION

      // DIAGONAL MOVEMENT
//      if (keyH.downPressed && keyH.leftPressed) {
//        direction = "left";
//        worldY += speed;
//        worldX -= speed;
//      } else if (keyH.downPressed && keyH.rightPressed) {
//        direction = "right";
//        worldY += speed;
//        worldX += speed;
//      } else if (keyH.upPressed && keyH.leftPressed) {
//        direction = "left";
//        worldY -= speed;
//        worldX -= speed;
//      } else if (keyH.upPressed && keyH.rightPressed) {
//        direction = "right";
//        worldY -= speed;
//        worldX += speed;
//      }
      // HORIZONTAL AND VERTICAL MOVEMENT
      if (keyH.upPressed) {
        direction = "up";
      } else if (keyH.leftPressed) {
        direction = "left";
      } else if (keyH.downPressed) {
        direction = "down";
      } else if (keyH.rightPressed) {
        direction = "right";
      }

      // ----------------------- CHECKING TILE COLLISION -----------------------
      collisionOn = false;
      // passing this (player class) as the entity for checking whether we are colliding with a tile
      gp.cChecker.checkTile(this);
      //check object collision
      int objIndex = gp.cChecker.checkObject(this, true);
      pickUpObject(objIndex);

      // if collision is false, player can move
      if (!collisionOn)
      {
        switch (direction)
        {
          case "up":
            worldY -= speed;
            break;
          case "down":
            worldY += speed;
            break;
          case "left":
            worldX -= speed;
            break;
          case "right":
            worldX += speed;
            break;
        }
      }

      // ----------------------- ANIMATING THE SPRITES -----------------------
      spriteCounter++;

      if (spriteCounter > 10)
      {
        if (spriteNum == 0)
        {
          spriteNum = 1;
        }
        else if (spriteNum == 1)
        {
          spriteNum = 0;
        }

        spriteCounter = 0;
      }
    }
    else // IDLE ANIMATION
    {
      if (Objects.equals(direction, "up"))
      {
        direction = "up_idle";
      }
      else if (Objects.equals(direction, "left"))
      {
        direction = "left_idle";
      }
      else if (Objects.equals(direction, "down"))
      {
        direction = "down_idle";
      }
      else if (Objects.equals(direction, "right"))
      {
        direction = "right_idle";
      }

      spriteCounter++;

      if (spriteCounter > 10)
      {
        if (spriteNum == 0)
        {
          spriteNum = 1;
        }
        else if (spriteNum == 1)
        {
          spriteNum = 0;
        }

        spriteCounter = 0;
      }
    }

  }
  // ----------------------- PICKING UP THE ITEMS -----------------------
  // ==========================================
  //               pickUpObject Method
  //  Author: Liyuan Hu
  //  Purpose: method for picking up objects and modifying the stats of player
  // ==========================================
  public void pickUpObject(int i){
    if (i != 999){
      String objectName = gp.obj[i].name;

      switch (objectName){
        case "Speedy Boba":
          gp.playerSFX(2);    // sound effect
          speed += 1;           // stat modifier
          gp.obj[i] = null;
          gp.ui.showMessage("Speed up!");
          break;

        case "Attack Broccoli":
          gp.playerSFX(1);
          attack += 2;
          gp.obj[i] = null;
          gp.ui.showMessage("Attack up!");
          break;

        case "Defense Pudding":
          gp.playerSFX(2);
          defenseValue += 2;
          gp.obj[i] = null;
          gp.ui.showMessage("Defense up!");
          break;
      }

    }

  }

  public void draw(Graphics2D g2)
  {
    BufferedImage image = null;

    switch (direction) 
    {
      case "up":
        if (spriteNum == 0) {
          image = up0;
        }
        if (spriteNum == 1) {
          image = up1;
        }
        break;
      case "down":
        if (spriteNum == 0) {
          image = down0;
        }
        if (spriteNum == 1) {
          image = down1;
        }
        break;
      case "left":
        if (spriteNum==0) {
          image = left0;
        }
        if (spriteNum == 1) {
          image = left1;
        }
        break;
      case "right":
        if(spriteNum == 0) {
          image = right0;
        }
        if(spriteNum == 1) {
          image = right1;
        }
        break;
      case "up_idle":
        if (spriteNum == 0) {
          image = up0_idle;
        }
        if (spriteNum == 1) {
          image = up1_idle;
        }
        break;
      case "down_idle":
        if (spriteNum == 0) {
          image = down0_idle;
        }
        if (spriteNum == 1) {
          image = down1_idle;
        }
        break;
      case "left_idle":
        if (spriteNum==0) {
          image = left0_idle;
        }
        if (spriteNum == 1) {
          image = left1_idle;
        }
        break;
      case "right_idle":
        if(spriteNum == 0) {
          image = right0_idle;
        }
        if(spriteNum == 1) {
          image = right1_idle;
        }
        break;
      }

      g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
  }
