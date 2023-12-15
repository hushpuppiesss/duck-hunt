package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    // opens audio file
    Clip clip;
    // array containing the file paths of sound files
    URL[] soundURL = new URL[5];

    // ----------------------- CONSTRUCTOR -----------------------
    public Sound()
    {
        soundURL[0] = getClass().getResource("/sound/The Mountain Village.wav");
        soundURL[1] = getClass().getResource("/sound/nom.wav");
        soundURL[2] = getClass().getResource("/sound/slurp.wav");


    }

    // -----------------------
    public void setFile(int i) {

        try {
            // format to opening audio files in java
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e)
        {
            // do nothing
        }

    }

    // ----------------------- PLAYING THE MUSIC -----------------------
    public void play() {
        clip.start();
    }

    // ----------------------- LOOPING THE BG MUSIC -----------------------
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // ----------------------- STOPS THE MUSIC -----------------------
    public void stop() {
        clip.stop();
    }
}
