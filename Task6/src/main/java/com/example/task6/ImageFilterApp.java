package com.example.task6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


abstract class ImageFilter {
    public abstract BufferedImage applyFilter(BufferedImage image);
}


class BlackAndWhiteFilter extends ImageFilter {
    @Override
    public BufferedImage applyFilter(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                Color newColor = new Color(gray, gray, gray);
                result.setRGB(x, y, newColor.getRGB());
            }
        }
        return result;
    }
}


class SepiaFilter extends ImageFilter {
    @Override
    public BufferedImage applyFilter(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                int red = (int) (color.getRed() * 0.393 + color.getGreen() * 0.769 + color.getBlue() * 0.189);
                int green = (int) (color.getRed() * 0.349 + color.getGreen() * 0.686 + color.getBlue() * 0.168);
                int blue = (int) (color.getRed() * 0.272 + color.getGreen() * 0.534 + color.getBlue() * 0.131);
                red = Math.min(255, red);
                green = Math.min(255, green);
                blue = Math.min(255, blue);
                Color newColor = new Color(red, green, blue);
                result.setRGB(x, y, newColor.getRGB());
            }
        }
        return result;
    }
}

public class ImageFilterApp extends JFrame {
    private BufferedImage originalImage;
    private JLabel originalLabel;
    private JLabel filteredLabel;

    public ImageFilterApp() {
        originalLabel = new JLabel();
        filteredLabel = new JLabel();

        JButton loadImageButton = new JButton("Выбрать изображение");
        loadImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAndDisplayImage();
            }
        });

        JButton blackAndWhiteButton = new JButton("Черно-белый");
        JButton sepiaButton = new JButton("Сепия");

        blackAndWhiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyFilter(new BlackAndWhiteFilter());
            }
        });

        sepiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyFilter(new SepiaFilter());
            }
        });

        JPanel panel = new JPanel();
        panel.add(loadImageButton);
        panel.add(blackAndWhiteButton);
        panel.add(sepiaButton);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(originalLabel, BorderLayout.WEST);
        contentPane.add(filteredLabel, BorderLayout.CENTER);
        contentPane.add(panel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadAndDisplayImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().toLowerCase().endsWith(".jpg")
                        || file.getName().toLowerCase().endsWith(".jpeg")
                        || file.isDirectory();
            }

            @Override
            public String getDescription() {
                return "JPEG Images (*.jpg, *.jpeg)";
            }
        });

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                originalImage = ImageIO.read(selectedFile);
                originalLabel.setIcon(new ImageIcon(originalImage));
                filteredLabel.setIcon(null);
                pack();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void applyFilter(ImageFilter filter) {
        if (originalImage != null) {
            BufferedImage filteredImage = filter.applyFilter(originalImage);
            filteredLabel.setIcon(new ImageIcon(filteredImage));
            filteredLabel.repaint();
            pack();
        } else {
            JOptionPane.showMessageDialog(this, "Выберите изображение сначала.", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ImageFilterApp();
            }
        });
    }
}
