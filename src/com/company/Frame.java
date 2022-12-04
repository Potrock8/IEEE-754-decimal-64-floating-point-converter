package com.company;
import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private JPanel screen;

    public Frame(int width, int height) {
        this.setTitle("IEEE-754 Decimal 64 Floating-Point Converter");
        this.setForeground(Color.blue);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.screen = new JPanel();
        this.add(this.screen);
        repaint();
        this.setVisible(true);
    }

    /*public void setScreen(JPanel screen) {
        if(this.screen != null)
            this.remove(this.screen);

        this.screen = screen;
        this.add(this.screen);
        repaint();
        this.setVisible(true);
    }*/

    public void setScreen() {
        this.screen = new JPanel();
        this.add(this.screen);
        repaint();
        this.setVisible(true);
    }

    /*public JPanel getView(){
        return this.screen;
    }*/
}
