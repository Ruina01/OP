package com.example.task2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


interface AudioDevice {
    void play();
}

class Headphones implements AudioDevice {
    @Override
    public void play() {
        System.out.println("Playing through headphones");
    }
}

class Speakers implements AudioDevice {
    @Override
    public void play() {
        System.out.println("Playing through speakers");
    }
}

class Microphone implements AudioDevice {
    @Override
    public void play() {
        System.out.println("Recording through microphone");
    }
}


interface AudioDeviceFactory {
    AudioDevice createAudioDevice();
}

class HeadphonesFactory implements AudioDeviceFactory {
    @Override
    public AudioDevice createAudioDevice() {
        return new Headphones();
    }
}

class SpeakersFactory implements AudioDeviceFactory {
    @Override
    public AudioDevice createAudioDevice() {
        return new Speakers();
    }
}

class MicrophoneFactory implements AudioDeviceFactory {
    @Override
    public AudioDevice createAudioDevice() {
        return new Microphone();
    }
}


public class AudioDeviceControlApp extends JFrame {
    private JComboBox<String> deviceTypeComboBox;
    private JButton playButton;

    private AudioDeviceFactory audioDeviceFactory;

    public AudioDeviceControlApp() {
        setTitle("Audio Device Control App");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
        initListeners();
    }

    private void initUI() {
        setLayout(new FlowLayout());

        deviceTypeComboBox = new JComboBox<>(new String[]{"Headphones", "Speakers", "Microphone"});
        playButton = new JButton("Play/Record");

        add(deviceTypeComboBox);
        add(playButton);
    }

    private void initListeners() {
        deviceTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDevice = (String) deviceTypeComboBox.getSelectedItem();
                switch (selectedDevice) {
                    case "Headphones":
                        audioDeviceFactory = new HeadphonesFactory();
                        break;
                    case "Speakers":
                        audioDeviceFactory = new SpeakersFactory();
                        break;
                    case "Microphone":
                        audioDeviceFactory = new MicrophoneFactory();
                        break;
                    default:
                        break;
                }
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (audioDeviceFactory != null) {
                    AudioDevice audioDevice = audioDeviceFactory.createAudioDevice();
                    audioDevice.play();
                } else {
                    System.out.println("Please select a device type first.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AudioDeviceControlApp().setVisible(true);
            }
        });
    }
}
