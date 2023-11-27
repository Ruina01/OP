
package com.example.task3;

public class Headphones extends AbstractMusicDevice {
    @Override
    public void playMusic() {
        System.out.println("Headphones are playing music");
    }

    @Override
    public void stopMusic() {
        System.out.println("Headphones stopped playing music");
    }
}
