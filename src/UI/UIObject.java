package UI;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by AlexVR on 7/1/2018.
 */
public abstract class UIObject {

    protected int width,heith;
    protected float x,y;
    protected Rectangle bounds;
    protected boolean hovering = false;
    protected boolean active=false;


    public UIObject(float x, float y,int width,int height){
        this.heith=height;
        this.width=width;
        this.x=x;
        this.y=y;
        bounds = new Rectangle((int)x,(int)y,width,height);

    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void onClick();


    public void onMousePressed(MouseEvent e) {
        active=true;
    }



    public void onMouseMove(MouseEvent e){
        if(bounds.contains(e.getX(),e.getY())){
            hovering=true;
        }else hovering=false;

    }
    public void onMouseRelease(MouseEvent e){

        if(hovering&& e.getButton()==MouseEvent.BUTTON1){
            onClick();
        }
        active=false;

    }





//get set


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeith() {
        return heith;
    }

    public void setHeith(int heith) {
        this.heith = heith;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isHovering() {
        return hovering;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }
}
