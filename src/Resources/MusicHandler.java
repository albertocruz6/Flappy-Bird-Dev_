package Resources;


import Main.Handler;

import javax.sound.sampled.*;

import java.io.File;

public class MusicHandler {

	/*
	 * THIS IS MARIO MAKERS MUSIC HANDLER, IT'S EASIER TO USE
	 */
	
    //Res.music
    private File audioFile;
    private AudioInputStream audioStream;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip audioClip;


    //Inspired By ahmetcandiroglu' Music Handler

    private Handler handler;
    private Clip background;
    private long clipTime = 0;

    public MusicHandler(Handler handler){
        this.handler = handler;
        background = getClip(loadAudio("background"));
    }

    private AudioInputStream loadAudio(String url) {
        try {
            audioFile = new File("res/music/" + url + ".wav");
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            format = audioStream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);

            if(url.equals("background")){
                audioClip.loop(-1);
            }else{
                audioClip.loop(0);
            }

            return audioStream;

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    private Clip getClip(AudioInputStream stream) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(stream);

            return clip;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void resumeBackground(){
        background.setMicrosecondPosition(clipTime);
        background.start();
    }

    public void pauseBackground(){
        clipTime = background.getMicrosecondPosition();
        background.stop();
    }

    public void restartBackground() {
        clipTime = 0;
        resumeBackground();
    }
    public boolean ended(){
        return background.getMicrosecondLength()-10<=background.getMicrosecondPosition();
    }

    ///Example to play a special effect
    public void playWing() {
        Clip clip = getClip(loadAudio("wing"));
        clip.start();
    }

    public void play(String str) {
        Clip clip = getClip(loadAudio(str));
        clip.start();
    }

    public void playPoint() {
        Clip clip = getClip(loadAudio("point"));
        clip.start();
    }
    public void playSwoosh() {
        Clip clip = getClip(loadAudio("swooshing"));
        clip.start();
    }
    public void playHit() {
        Clip clip = getClip(loadAudio("hit"));
        clip.start();
    }
    public void playDead() {
        background.stop();
        background = getClip(loadAudio("die"));
        background.start();
    }


}
