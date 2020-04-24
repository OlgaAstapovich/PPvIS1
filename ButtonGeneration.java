package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonGeneration extends JFrame{
    private JTextField input = new JTextField(5);
    private JButton button1, button2, button3;
    private JPanel panel = new JPanel();
    private ButtonGroup group;
    boolean stat = true;
    int num = 0;
    SetSelected setting;

    public ButtonGeneration(){
        super("The second task");
        this.setBounds(500,250,500,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button1 = new JButton("Button 1");
        button2 = new JButton("Button 2");
        button3 = new JButton("Button 3");
        group = new ButtonGroup();
        panel.add(input);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        this.add(panel);

        button1.addActionListener(new Button1EventListener());
        button2.addActionListener(new Button2EventListener()); 
        button3.addActionListener(new Button3EventListener());
    }
    class Button1EventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Component component: panel.getComponents()){
                if (component instanceof JRadioButton) {
                    panel.remove((JRadioButton) component);
                }
            }
            try {
                int number = Integer.parseInt(input.getText());
                for (int i =0; i< number; i++){
                    JRadioButton radio = new JRadioButton("" + (i+1));
                    panel.add(radio);
                    group.add(radio);
                }
            } catch (Exception exception){
                System.out.println("Something wrong\n Try again\n Maybe wrong type, input only numbers");
            }
            panel.updateUI();
        }
    }
    class Button2EventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setting = new SetSelected();
            setting.start();
        }
    }
    class Button3EventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            stat = false;
        }
    }
    class SetSelected extends Thread {
        @Override
        public void run(){
            stat = true;
            if (num != 0){
                for (num -= 1; num < panel.getComponentCount(); num++) {
                    if(panel.getComponent(num) instanceof JRadioButton) {
                        if (stat) {
                            ((JRadioButton) panel.getComponent(num)).setSelected(true);
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                            }
                        }else{
                            break;
                        }
                    }
                }
            }
            label: while (true) {
                for (Component c : panel.getComponents()) {
                    if (c instanceof JRadioButton) {
                        if(stat) {
                            ((JRadioButton) c).setSelected(true);
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                            }
                        }else{
                            num = panel.getComponentZOrder(c);
                            break label;
                        }
                    }
                }
            }
        }
    }
}
