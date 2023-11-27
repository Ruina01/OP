
package com.example.task3;

public class MediaPlayer extends AbstractMusicDevice {
    @Override
    public void playMusic() {
        System.out.println("MediaPlayer is playing music");
    }

    @Override
    public void stopMusic() {
        System.out.println("MediaPlayer stopped playing music");
    }
}
