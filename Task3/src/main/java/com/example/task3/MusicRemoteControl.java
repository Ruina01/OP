
package com.example.task3;

public class MusicRemoteControl implements RemoteControl {
    private MusicDevice device;

    @Override
    public void powerOn() {
        System.out.println("Powering on the music device");
    }

    @Override
    public void powerOff() {
        System.out.println("Powering off the music device");
    }

    @Override
    public void setDevice(MusicDevice device) {
        this.device = device;
    }

    public void playMusic() {
        device.playMusic();
    }

    public void stopMusic() {
        device.stopMusic();
    }
}
