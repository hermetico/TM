/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visorImagen;

import java.awt.BorderLayout;
import java.awt.Image;
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
    BufferedImage img;
    
    public Visor(BufferedImage img){
        
        JPanel contentPanel = new JPanel();
        JLabel labelImage = new JLabel();
        
        contentPanel.add(labelImage, BorderLayout.CENTER);
        
        ImageIcon icon = new ImageIcon(img);
        
        labelImage.setIcon(icon);
        this.setSize(img.getWidth(),img.getHeight());
        this.add(contentPanel);
        
    }
}
