package com.example.task9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AsyncTaskManager extends JFrame {
    private JTextArea textArea;
    private JButton startButton;
    private JButton stopButton;
    private TaskWorker taskWorker;

    public AsyncTaskManager() {
        setTitle("Async Task Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        startButton = new JButton("Start Task");
        stopButton = new JButton("Stop Task");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTask();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTask();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void startTask() {
        if (taskWorker == null || taskWorker.isDone()) {
            taskWorker = new TaskWorker();
            taskWorker.execute();
        }
    }

    private void stopTask() {
        if (taskWorker != null && !taskWorker.isDone()) {
            taskWorker.cancel(true);
        }
    }

    private class TaskWorker extends SwingWorker<Void, String> {
        @Override
        protected Void doInBackground() throws Exception {
            for (int i = 1; i <= 10; i++) {
                if (isCancelled()) {
                    break;
                }
                publish("Task progress: " + i);
                Thread.sleep(1000);
            }
            return null;
        }

        @Override
        protected void process(java.util.List<String> chunks) {
            for (String chunk : chunks) {
                textArea.append(chunk + "\n");
            }
        }

        @Override
        protected void done() {
            textArea.append("Task completed.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AsyncTaskManager().setVisible(true);
            }
        });
    }
}
