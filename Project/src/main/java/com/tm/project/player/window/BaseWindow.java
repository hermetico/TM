/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.project.player.window;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BaseWindow extends JFrame{
    JPanel contentPanel;
    JLabel label;
    ImageIcon image;
    
    public BaseWindow(){
        contentPanel = new JPanel();
        label =  new JLabel();
        contentPanel.add(label, BorderLayout.CENTER);
        this.addListeners();
    }

    public void draw(BufferedImage img){
        label.setIcon(new ImageIcon(img));
        label.setSize(img.getWidth(), img.getHeight());
    }
    
    private void addListeners(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                //TODO CHECK THIS
                System.exit(0);
             }
            }
        );
    }
    
}
