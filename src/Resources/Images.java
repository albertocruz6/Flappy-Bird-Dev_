package Resources;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
	//Put all Image/SpriteSheet/Image Array fields here
	
	//BufferedImage[] arr = new BufferedImage[capacity]
	//BufferedImage img = new BufferedImage();
	
	public static SpriteSheet gameSprite;
	public static SpriteSheet pipeSheet;
	public static BufferedImage background;
	
	public Images() {
		
		//Initialize BufferedImage Arrays Here
		
		
		try {
			//Initialize Sprites and assign Images Here
			/*
			 * For Sprites:
			 * 		ss = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("path")));
			 * For Images:
			 * 		img = ImageIO.read(getClass().getResourceAsStream("path");
			 * For images of Arrays:
			 * 		img[0] = ImageIO.read(getClass().getResourceAsStream("path");
			 */
			
			gameSprite = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/gameSprite.png")));
			pipeSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/pipes.png")));
			
			background = gameSprite.crop(0, 0, 145, 257);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage loadImage(String path) { //Not used atm
        try {
            return ImageIO.read(Images.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
