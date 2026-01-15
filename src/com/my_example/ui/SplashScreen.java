package com.my_example.ui;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;

import java.awt.*;

public class SplashScreen extends JWindow {
    JProgressBar progressBar;
    public SplashScreen()
    {
    setSize(900,700);
    // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    getContentPane().setBackground(Color.BLACK);
    ImageIcon image=new ImageIcon("images/pvr_image.png");
    JLabel lb;
    if(image.getIconWidth() == -1) {
        // If image not found, create a simple label
        lb = new JLabel("Numerology App", JLabel.CENTER);
        lb.setFont(new Font("Arial", Font.BOLD, 36));
        lb.setForeground(Color.WHITE);
    } else {
        lb=new JLabel(image);
    }
    getContentPane().add(lb,BorderLayout.NORTH);
    progressBar=new JProgressBar(1,100);
    progressBar.setStringPainted(true);
    this.getContentPane().add(progressBar,BorderLayout.SOUTH);
    loadProgress();
    }
    Timer timer;
    void loadProgress()
    {
        timer =new Timer(200,e->{
           int val= progressBar.getValue();
            if(val<100)
            {
                progressBar.setValue(val+1);
            }
            else
            {
                timer.stop();
                dispose();//current screen dispose
                Login login=new Login();
                login.setVisible(true);
            }
        });
        timer.start();


    }

    public static void main(String[] args) {
        SplashScreen splashScreen=new SplashScreen();
        splashScreen.setVisible(true);
        
        
    }
}
