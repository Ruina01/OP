package com.example.task5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class Новость {
    private String заголовок;
    private String содержание;

    public Новость(String заголовок, String содержание) {
        this.заголовок = заголовок;
        this.содержание = содержание;
    }

    public String получитьЗаголовок() {
        return заголовок;
    }

    public String получитьСодержание() {
        return содержание;
    }
}


class СубъектНовостей {
    private List<НаблюдательНовостей> наблюдатели = new ArrayList<>();

    public void добавитьНаблюдателя(НаблюдательНовостей наблюдатель) {
        наблюдатели.add(наблюдатель);
    }

    public void удалитьНаблюдателя(НаблюдательНовостей наблюдатель) {
        наблюдатели.remove(наблюдатель);
    }

    public void уведомитьНаблюдателей(Новость новость) {
        for (НаблюдательНовостей наблюдатель : наблюдатели) {
            наблюдатель.обновить(новость);
        }
    }
}


class НаблюдательНовостей {
    private String тема;
    private JTextArea текстоваяОбласть;

    public НаблюдательНовостей(String тема, JTextArea текстоваяОбласть) {
        this.тема = тема;
        this.текстоваяОбласть = текстоваяОбласть;
    }

    public String получитьТему() {
        return тема;
    }

    public JTextArea получитьТекстовуюОбласть() {
        return текстоваяОбласть;
    }

    public void обновить(Новость новость) {
        SwingUtilities.invokeLater(() -> {
            текстоваяОбласть.append("Новая статья по теме '" + тема + "':\n");
            текстоваяОбласть.append("Заголовок: " + новость.получитьЗаголовок() + "\n");
            текстоваяОбласть.append("Содержание: " + новость.получитьСодержание() + "\n\n");
        });
    }
}


class АгентствоНовостей {
    private Map<String, СубъектНовостей> темы = new HashMap<>();

    public void добавитьНовость(String тема, Новость новость) {
        СубъектНовостей субъектНовостей = темы.get(тема);
        if (субъектНовостей != null) {
            субъектНовостей.уведомитьНаблюдателей(новость);
        }
    }

    public void добавитьНаблюдателя(String тема, НаблюдательНовостей наблюдатель) {
        СубъектНовостей субъектНовостей = темы.computeIfAbsent(тема, k -> new СубъектНовостей());
        субъектНовостей.добавитьНаблюдателя(наблюдатель);
    }
}

public class ПриложениеНовостей {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Приложение новостей");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JTextArea текстоваяОбластьНовостей = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(текстоваяОбластьНовостей);
            frame.add(scrollPane, BorderLayout.CENTER);

            JPanel панельУправления = new JPanel();
            JTextField полеТемы = new JTextField(10);
            JButton кнопкаПодписки = new JButton("Подписаться");
            панельУправления.add(new JLabel("Тема:"));
            панельУправления.add(полеТемы);
            панельУправления.add(кнопкаПодписки);
            frame.add(панельУправления, BorderLayout.SOUTH);

            АгентствоНовостей агентствоНовостей = new АгентствоНовостей();

            кнопкаПодписки.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String тема = полеТемы.getText();
                    JTextArea текстоваяОбластьТемы = new JTextArea(10, 30);
                    JScrollPane scrollPane = new JScrollPane(текстоваяОбластьТемы);

                    НаблюдательНовостей наблюдательНовостей = new НаблюдательНовостей(тема, текстоваяОбластьТемы);
                    агентствоНовостей.добавитьНаблюдателя(тема, наблюдательНовостей);

                    JFrame окноТемы = new JFrame("Тема: " + тема);
                    окноТемы.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    окноТемы.setLayout(new BorderLayout());
                    окноТемы.add(scrollPane, BorderLayout.CENTER);
                    окноТемы.pack();
                    окноТемы.setVisible(true);
                }
            });

            frame.setSize(500, 300);
            frame.setVisible(true);


            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(5000);
                        String тема = "Технологии";
                        Новость новость = new Новость("Новая технологическая статья", "Содержание технологической статьи...");
                        агентствоНовостей.добавитьНовость(тема, новость);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        });
    }
}
