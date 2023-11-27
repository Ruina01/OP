
package com.example.task3;

public class Speakers extends AbstractMusicDevice {
    @Override
    public void playMusic() {
        System.out.println("Speakers are playing music");
    }

    @Override
    public void stopMusic() {
        System.out.println("Speakers stopped playing music");
    }
}
