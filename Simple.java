package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Simple extends JFrame {
    private JButton button1 = new JButton("JButton1");
    private JButton button2 = new JButton("JButton2");
    private JTextField input = new JTextField("",9);
    JPanel buttons = new JPanel();

    public Simple(){
        super("The first task");
        this.setBounds(500,250,350,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttons.add(input);
        buttons.add(button1);
        buttons.add(button2);

        button1.addActionListener(new Button1EventListener ());
        button2.addActionListener(new Button2EventListener ());

        this.add(buttons, BorderLayout.NORTH);
    }
    class Button1EventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            button2.setText(input.getText());
        }
    }
    class Button2EventListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            String text = "";
            text = button1.getText();
            button1.setText(button2.getText());
            button2.setText(text);
        }
    }
}
