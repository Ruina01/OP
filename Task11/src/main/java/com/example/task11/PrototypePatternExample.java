package com.example.task11;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;


interface UIElement extends Cloneable {
    UIElement cloneElement();
}


class UIButton extends JButton implements UIElement {

    public UIButton(String text) {
        super(text);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Clicked: " + text);
            }
        });
    }


    @Override
    public UIElement cloneElement() {
        return new UIButton(getText());
    }
}


class UIManager {
    private Map<String, UIElement> prototypes = new HashMap<>();


    public void addPrototype(String key, UIElement prototype) {
        prototypes.put(key, prototype);
    }


    public UIElement createUIElement(String key) {
        UIElement prototype = prototypes.get(key);
        if (prototype != null) {
            return prototype.cloneElement();
        } else {
            return null;
        }
    }
}


public class PrototypePatternExample {
    public static void main(String[] args) {

        UIManager uiManager = new UIManager();


        UIButton buttonPrototype1 = new UIButton("Button 1");
        uiManager.addPrototype("Button1", buttonPrototype1);

        UIButton buttonPrototype2 = new UIButton("Button 2");
        uiManager.addPrototype("Button2", buttonPrototype2);


        UIElement clonedButton1 = uiManager.createUIElement("Button1");
        UIElement clonedButton2 = uiManager.createUIElement("Button2");


        JFrame frame = new JFrame("Prototype Pattern Example");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (clonedButton1 != null) {
            frame.add((JButton) clonedButton1);
        }

        if (clonedButton2 != null) {
            frame.add((JButton) clonedButton2);
        }

        frame.pack();
        frame.setVisible(true);
    }
}
