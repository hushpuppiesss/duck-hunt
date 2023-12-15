package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Pot extends SuperObject {

    public OBJ_Pot() {
        name = "Cooking Pot";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/pot.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }
}

