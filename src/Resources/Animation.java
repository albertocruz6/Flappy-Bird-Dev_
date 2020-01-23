package Resources;

import java.awt.image.BufferedImage;

/**
 * Created by bermed5 on 12/29/2019.
 */
public class Animation {
    private int speed,index;
    private long lastTime,timer;
    private BufferedImage[] frames;

    public Animation(int speed,BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();

    }

    public Animation(int speed,BufferedImage[] frames,int index){
        this.speed=speed;
        this.frames=frames;
        this.index=index;
        timer = 0;
        lastTime = System.currentTimeMillis();

    }

    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed){
            index++;
            timer = 0;
            if(index >= frames.length){
                index = 0;
            }
        }

    }

    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

    public BufferedImage getSpecificFrame(int frame){
    	if(frame >= frames.length || frame < 0) {
    		return frames[0];
    	}
        return frames[frame];
    }
    
    public int getIndex() {
    	return index;
    }
    
    public static String getLtr() {
    	return "M";
    }

}