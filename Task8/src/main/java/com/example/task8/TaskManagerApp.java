package com.example.task8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskManagerApp {

    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;

    public TaskManagerApp() {

        JFrame frame = new JFrame("Task Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);


        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setCellRenderer(new TaskListRenderer());


        JButton addButton = new JButton("Добавить задачу");
        JButton removeButton = new JButton("Удалить задачу");
        JTextArea taskTextArea = new JTextArea();
        JCheckBox completedCheckbox = new JCheckBox("Выполнено");


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskDescription = taskTextArea.getText();
                boolean completed = completedCheckbox.isSelected();
                Task task = new Task(taskDescription, completed);
                taskListModel.addElement(task);
                taskTextArea.setText("");
                completedCheckbox.setSelected(false);
            }
        });


        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    taskListModel.remove(selectedIndex);
                }
            }
        });


        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(taskList), BorderLayout.CENTER);


        JPanel inputPanel = new JPanel(new BorderLayout());
        JPanel textAreaPanel = new JPanel(new BorderLayout());
        textAreaPanel.add(new JLabel("Описание задачи:"), BorderLayout.NORTH);
        textAreaPanel.add(new JScrollPane(taskTextArea), BorderLayout.CENTER);
        inputPanel.add(textAreaPanel, BorderLayout.CENTER);
        inputPanel.add(completedCheckbox, BorderLayout.SOUTH);
        panel.add(inputPanel, BorderLayout.PAGE_START);
        panel.add(addButton, BorderLayout.LINE_END);
        panel.add(removeButton, BorderLayout.PAGE_END);


        frame.getContentPane().add(panel);


        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaskManagerApp();
            }
        });
    }

    private class Task {
        private String description;
        private boolean completed;

        public Task(String description, boolean completed) {
            this.description = description;
            this.completed = completed;
        }

        public String getDescription() {
            return description;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        @Override
        public String toString() {
            return description + (completed ? " (Выполнено)" : "");
        }
    }

    private class TaskListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Task) {
                Task task = (Task) value;
                setText(task.getDescription());
                setForeground(task.isCompleted() ? Color.GREEN : Color.BLACK);
            }
            return this;
        }
    }
}
