//jennie 
package object;

import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

public class OBJ_Broccoli extends SuperObject{
    //constructor
    public OBJ_Broccoli(){
        name = "Attack Broccoli";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/buffs/broccoli.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
        //add to game panel SuperObject obj[]=new SuperObject[10];//prepares a maximum 10 objects on display


    }
    
}
