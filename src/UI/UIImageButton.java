package UI;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class UIImageButton extends UIObject{
    private BufferedImage[] images;
    private ClickListlener clicker;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images,ClickListlener clicker ) {
        super(x, y, width, height);
        this.images=images;
        this.clicker=clicker;
    }


    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(active){
            if(images.length==3) {
                g.drawImage(images[2], (int) x, (int) y, width, heith, null);
            }
        }
        else if(hovering){
            g.drawImage(images[1],(int)x,(int)y,width,heith,null);
        }else{
            g.drawImage(images[0],(int)x,(int)y,width,heith,null);

        }
    }


    @Override
    public void onClick()
    {
        clicker.onClick();
    }
}
