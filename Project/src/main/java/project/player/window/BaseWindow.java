/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.player.window;

import project.settings.Configuration;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class BaseWindow extends JFrame{
    Configuration cf = Configuration.getInstance();
    JPanel contentPanel;
    JLabel label;
    ImageIcon image;
    Boolean sized = false;
    
    public BaseWindow(){
        contentPanel = new JPanel();
        label =  new JLabel();
        contentPanel.add(label, BorderLayout.CENTER);
        this.add(contentPanel);
        this.addListeners();
        
    }

    public void draw(BufferedImage img){
        if(!sized){
            this.setSize(img.getWidth(), img.getHeight());
            label.setSize(img.getWidth(), img.getHeight());
            sized = true;
        }
        label.setIcon(new ImageIcon(img));
        
    }
    
    @Override
    public void setTitle(String title){
        super.setTitle(title);
    }
    
    private void addListeners(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                BaseWindow.this.dispose();
                
                if(cf.EXIT_ON_CLOSE_WINDOW) System.exit(0);
                
             }
            }
        );
    }
    
}
