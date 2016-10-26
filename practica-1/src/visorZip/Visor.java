/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visorZip;

import visorImagen.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author hermetico
 */
public class Visor extends JFrame{
    JPanel contentPanel;
    JLabel labelImage;
    ImageIcon icon;
    
    
    public Visor(){
        
        contentPanel = new JPanel();
        labelImage = new JLabel();
        
        contentPanel.add(labelImage, BorderLayout.CENTER);
        this.add(contentPanel);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                //TODO CHECK THIS
                System.exit(0);
             }
            }
        );

    }
    public void show(BufferedImage img){
        icon = new ImageIcon(img);
        
        labelImage.setIcon(icon);
        this.setSize(img.getWidth(),img.getHeight());
    }
}

