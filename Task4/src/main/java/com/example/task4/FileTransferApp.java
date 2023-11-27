package com.example.task4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FileTransferApp extends JFrame {

    private JButton selectFileButton;
    private JButton transferButton;
    private JLabel selectedFileLabel;
    private String selectedFilePath;

    public FileTransferApp() {
        setTitle("Приложение для передачи файлов");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        selectFileButton = new JButton("Выбрать файл");
        transferButton = new JButton("Передать файл");
        selectedFileLabel = new JLabel("Выбранный файл: ");

        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Выбор файла");
                int result = fileChooser.showOpenDialog(FileTransferApp.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    selectedFilePath = selectedFile.getAbsolutePath();
                    selectedFileLabel.setText("Выбранный файл: " + selectedFilePath);
                }
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFilePath != null) {
                    transferFile(selectedFilePath);
                } else {
                    JOptionPane.showMessageDialog(FileTransferApp.this, "Пожалуйста, выберите файл.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(selectFileButton);
        panel.add(transferButton);
        panel.add(selectedFileLabel);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(selectFileButton)
                        .addComponent(transferButton))
                .addComponent(selectedFileLabel)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(selectFileButton)
                        .addComponent(transferButton))
                .addComponent(selectedFileLabel)
        );

        pack();
    }

    private void transferFile(String sourceFilePath) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите место для сохранения файла");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showSaveDialog(FileTransferApp.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File destinationDirectory = fileChooser.getSelectedFile();
            File destinationFile = new File(destinationDirectory, new File(sourceFilePath).getName());
            try {
                Path sourcePath = Paths.get(sourceFilePath);
                Path destinationPath = destinationFile.toPath();
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(FileTransferApp.this, "Файл успешно передан.");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(FileTransferApp.this, "Ошибка при передаче файла: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FileTransferApp().setVisible(true);
            }
        });
    }
}

