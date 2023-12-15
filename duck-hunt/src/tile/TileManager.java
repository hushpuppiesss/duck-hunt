// ==========================================
//               TileManager Class
//  Author: Rachel Quedding
//  Purpose: handles the loading  and drawing of tiles...
// ==========================================

package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    // tile manager constructor
    public TileManager(GamePanel gp)
    {
        this.gp = gp;

        tile = new Tile[40]; // creating n number of tiles
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/map.txt");
    }

    // ----------------------- LOADING IMAGE METHOD -----------------------
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

    // ----------------------- GETTING TILE IMAGES -----------------------
    public void getTileImage()
    {
        try {

            tile[0] = new Tile();
            tile[0].image = loadImage("/res/tiles/crate blueberries.png");
            tile[0].collision = true;

            tile[1] = new Tile();
            tile[1].image = loadImage("/res/tiles/crate herb.png");

            tile[2] = new Tile();
            tile[2].image = loadImage("/res/tiles/crate_apples.png");

            tile[3] = new Tile();
            tile[3].image = loadImage("/res/tiles/crate_oranges.png");

            tile[4] = new Tile();
            tile[4].image = loadImage("/res/tiles/grass bush flower.png");

            tile[5] = new Tile();
            tile[5].image = loadImage("/res/tiles/grass bush.png");

            tile[6] = new Tile();
            tile[6].image = loadImage("/res/tiles/grass flowers.png");

            tile[7] = new Tile();
            tile[7].image = loadImage("/res/tiles/grass shrubs.png");

            tile[8] = new Tile();
            tile[8].image = loadImage("/res/tiles/grass stone path.png");

            tile[9] = new Tile();
            tile[9].image = loadImage("/res/tiles/grass.png");

            tile[10] = new Tile();
            tile[10].image = loadImage("/res/tiles/pot.png");

            tile[11] = new Tile();
            tile[11].image = loadImage("/res/tiles/soil center.png");

            tile[12] = new Tile();
            tile[12].image = loadImage("/res/tiles/soil left.png");

            tile[13] = new Tile();
            tile[13].image = loadImage("/res/tiles/soil right.png");

            tile[14] = new Tile();
            tile[14].image = loadImage("/res/tiles/water0.png");

            tile[15] = new Tile();
            tile[15].image = loadImage("/res/tiles/water1.png");

            tile[16] = new Tile();
            tile[16].image = loadImage("/res/tiles/water10.png");

            tile[17] = new Tile();
            tile[17].image = loadImage("/res/tiles/water11.png");

            tile[18] = new Tile();
            tile[18].image = loadImage("/res/tiles/water12.png");

            tile[19] = new Tile();
            tile[19].image = loadImage("/res/tiles/water2.png");

            tile[20] = new Tile();
            tile[20].image = loadImage("/res/tiles/water3.png");

            tile[21] = new Tile();
            tile[21].image = loadImage("/res/tiles/water4.png");

            tile[22] = new Tile();
            tile[22].image = loadImage("/res/tiles/water5.png");

            tile[23] = new Tile();
            tile[23].image = loadImage("/res/tiles/water6.png");

            tile[24] = new Tile();
            tile[24].image = loadImage("/res/tiles/water7.png");

            tile[25] = new Tile();
            tile[25].image = loadImage("/res/tiles/water8.png");

            tile[26] = new Tile();
            tile[26].image = loadImage("/res/tiles/water9.png");

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    // ----------------------- LOADING THE MAP -----------------------
    public void loadMap(String filePath)
    {
         try
         {
             // importing the map from the text file
             InputStream is = getClass().getResourceAsStream(filePath);

             // reading the contents of the text file
             BufferedReader br = new BufferedReader(new InputStreamReader(is));

             // initializing column and row variables
             int col = 0;
             int row = 0;

             while (col < gp.maxWorldCol && row < gp.maxWorldRow)
             {
                 String line = br.readLine();

                 while (col < gp.maxWorldCol)
                 {
                     String[] numbers = line.split(" ");

                     int num = Integer.parseInt(numbers[col]);

                     mapTileNum[col][row] = num;
                     col++;
                 }

                 if (col == gp.maxWorldCol)
                 {
                     col = 0;
                     row++;
                 }
             }

             // closing the reader
             br.close();
         } catch (IOException e)
         {
             e.printStackTrace();
         }
    }

    // ----------------------- DRAWING METHOD -----------------------

    public void draw(Graphics2D g2)
    {
        int worldCol = 0; // column
        int worldRow = 0; // row

        /*
        This while loop while keep drawing a tile as long as we haven't met the max world column and row.
        It will add column by one, then move the x coordinate by the tile size. if we have met the max column number,
        we will reset and move on to the next column, starting a new row by way of moving a y value
         */
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
        {
            int tileNum = mapTileNum[worldCol][worldRow];

            /* check the tile's worldX/Y to know where to draw it ON THE WORLD
            by calculating what row/column we're on and multiplying it by the tilesize.
            ex. worldCol = 0 and worldRow = 0 multiplied by tileSize will draw the first tile at (0,0).
            worldCol = 1 and worldRow = 0 multiplied by tileSize will draw the next tile at (64, 0).
             */
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

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
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

}
