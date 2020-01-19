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
	
	//Game Background
	public static BufferedImage background, floor;
	
	//Main Menu
	public static BufferedImage titleIcon, startButton, scoreButton;
	public static BufferedImage[] buttonStart;
	
	//Get Ready Screen
	public static BufferedImage readyIcon, tapIcon, flapIcon, arrowIcon;
	
	//Player and objects
	public static BufferedImage pipeUp, pipeDown, onePoint;
	public static BufferedImage[] yPlayer;
	public static BufferedImage[] rPlayer;
	
	public Images() {
		
		//Initialize BufferedImage Arrays Here
		
		yPlayer = new BufferedImage[3];
		rPlayer = new BufferedImage[3];
		
		buttonStart = new BufferedImage[2];

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
			
			//Pipes
			pipeDown = pipeSheet.crop(40, 0, 26, 300);
			pipeUp = pipeSheet.crop(464, 212, 26, 300);
			
			//One Point
			onePoint = gameSprite.crop(136, 455, 8, 18);
			
			yPlayer[0] = gameSprite.crop(3, 491, 17, 12);
			yPlayer[1] = gameSprite.crop(31, 491, 17, 12);
			yPlayer[2] = gameSprite.crop(59, 491, 17, 12);
			
			rPlayer[0] = gameSprite.crop(115, 381, 17, 12);
			rPlayer[1] = gameSprite.crop(115, 407, 17, 12);
			rPlayer[2] = gameSprite.crop(115, 433, 17, 12);

			
			background = gameSprite.crop(0, 0, 145, 257);
			floor = gameSprite.crop(292, 0, 168, 56);
			
			//Main Menu
			titleIcon = gameSprite.crop(351, 91, 89, 24);
			startButton = gameSprite.crop(354, 118, 52, 29);
			scoreButton = gameSprite.crop(414, 118, 52, 29);
			
			buttonStart[0] = startButton;
			buttonStart[1] = startButton;
			
			//Get Ready Screen
			readyIcon = gameSprite.crop(295, 59, 92, 25);
			tapIcon = gameSprite.crop(292, 121, 57, 20);
			flapIcon = gameSprite.crop(310, 91, 19, 16);
			arrowIcon = gameSprite.crop(316, 110, 7, 7);

		
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
