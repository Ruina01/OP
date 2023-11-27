
import com.example.task3.MediaPlayer;
import com.example.task3.MusicRemoteControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicPlayerApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Music Player App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MusicRemoteControl remoteControl = new MusicRemoteControl();
        MediaPlayer mediaPlayer = new MediaPlayer();
        remoteControl.setDevice(mediaPlayer);

        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remoteControl.playMusic();
            }
        });

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remoteControl.stopMusic();
            }
        });

        JButton powerOnButton = new JButton("Power On");
        powerOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remoteControl.powerOn();
            }
        });

        JButton powerOffButton = new JButton("Power Off");
        powerOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remoteControl.powerOff();
            }
        });

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(playButton);
        frame.add(stopButton);
        frame.add(powerOnButton);
        frame.add(powerOffButton);

        frame.pack();
        frame.setVisible(true);
    }
}
