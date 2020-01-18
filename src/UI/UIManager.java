package UI;

import Main.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class UIManager {

    private Handler handler;
    private ArrayList<UIObject> objects;

    public UIManager(Handler handler){
        this.handler=handler;
        objects = new ArrayList<UIObject>();
    }

    public void tick(){
        for(UIObject o: objects){
            o.tick();
        }
    }

    public void Render(Graphics g){
        for(UIObject o: objects){
            o.render(g);
        }
    }

    public void onMousePressed(MouseEvent e) {
        for(UIObject o: objects){
            o.onMousePressed(e);
        }
    }

        public void onMouseMove(MouseEvent e){
        for(UIObject o: objects){
            o.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e){
        for(UIObject o: objects){
            o.onMouseRelease(e);
        }
    }

    public void addObjects (UIObject o){
        objects.add(o);
    }

    public void removeObsjects(UIObject o){
        objects.remove(o);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<UIObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<UIObject> objects) {
        this.objects = objects;
    }

    public void isActive(ArrayList<UIObject> o, Boolean active){
        for(UIObject i: o){
            i.active=active;
        }
    }
}
