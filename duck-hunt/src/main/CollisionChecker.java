// ==========================================
//         CollisionChecker Class
//  Author: Rachel Quedding
//  Purpose: Checks for collisions
// ==========================================

package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    // CONSTRUCTOR
    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }

    // ----------------------- CHECKS FOR ENTITY COLLISION -----------------------
    public void checkTile(Entity entity)
    {
        // coordinates of the rectangle to check for collision
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        // finding out the column and row numbers
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        // checking two corners for every direction we are moving
        int tileNum1, tileNum2;

        switch (entity.direction)
        {
            case "up":
                // predicting where, in terms of tile number, the player will be after he moves
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                // checking whether these tiles are "solid" tiles
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                {
                    // cannot move in this direction
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                {
                    entity.collisionOn = true;
                }
                break;
        }
    }
    //check if entity is player or not, check if the player is hitting anything, if it is
    //return index
    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        for (int i=0;i<gp.obj.length;i++){
            if(gp.obj[i] != null){
                //Get entity's solid area position
                entity.solidArea.x = entity.worldX+entity.solidArea.x;
                entity.solidArea.y = entity.worldY+entity.solidArea.y;
                //Get object solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
                //simulating entity's movement and check where it is after it is moved 
                switch(entity.direction){
                    case"up":
                    entity.solidArea.y -= entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision==true){
                            entity.collisionOn = true;

                        }
                        if(player == true){
                            index = i;
                        }
                    }
                    case"down":
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision==true){
                            entity.collisionOn = true;

                        }
                        if(player == true){
                            index = i;
                        }
                       
                    }
                    case "left":
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision==true){
                            entity.collisionOn = true;

                        }
                        if(player == true){
                            index = i;
                        }
                       
                    }
                    case "right":
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision==true){
                            entity.collisionOn = true;

                        }
                        if(player == true){
                            index = i;
                        }
                     
                    }
                    entity.solidArea.x= entity.SolidAreaDefaultx;
                    entity.solidArea.y = entity.SolidAreaDefaulty;
                    gp.obj[i].solidArea.x = gp.obj[i].SolidAreaDefaultx;
                    gp.obj[i].solidArea.y = gp.obj[i].SolidAreaDefaulty;
                }
            }

        }
        return index;

    }

}
