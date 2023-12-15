
// ==========================================
//               Entity Class
//  Author: Marty McGill
//  Purpose: All entities will inherit the
//  characteristics (instance fields) in
//  the Entity class
// ==========================================

package entity;

import java.awt.Rectangle; // will use class to create invisible rectangles to store data
import java.awt.image.BufferedImage;

// Will store variables that will be used in player, enemy, turret, and npc classes

public class Entity {

  // X and Y of the entity's position
  // the player will always be at the center of the screen, so this is the coordinates of the player
  // in the world
  public int worldX, worldY;

  // speed of the entity
  public int speed;

  // used for player direction in player.java
  public BufferedImage up0, up1, down0, down1, left0, left1, right0, right1,
          up0_idle, up1_idle,down0_idle, down1_idle, left0_idle, left1_idle, right0_idle, right1_idle;
  public String direction;
  public int spriteCounter = 0;
  public int spriteNum = 0;
  // Jennie

  // for collisions
  public Rectangle solidArea;

  // Jennie
  public int SolidAreaDefaultx, SolidAreaDefaulty;
  public boolean collisionOn = false;

  // ----------------------- STATE OF THE CHARACTER -----------------------
  public boolean alive = true;

  // ----------------------- CHARACTER ATTRIBUTES -----------------------

  public int life;
  public int maxLife;
  public int attack;
  public int water;
  public int maxWater;
  public String name;
  public Projectile projectile;


  // ----------------------- ITEM ATTRIBUTES -----------------------
  public int attackValue;
  public int defenseValue;
  public String description = "";
  public int useCost;


}
