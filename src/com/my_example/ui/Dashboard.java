package com.my_example.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Dashboard extends JFrame {
    private String username;

    public Dashboard(String username) {
        this.username = username;
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Dashboard - Numerology App");
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(240, 248, 255));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 800, 100);
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setLayout(null);
        add(headerPanel);

        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBounds(30, 20, 500, 40);
        headerPanel.add(welcomeLabel);

        JLabel subtitleLabel = new JLabel("Discover Your Numerology Insights");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setBounds(30, 55, 400, 30);
        headerPanel.add(subtitleLabel);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(50, 120, 700, 350);
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(null);
        add(contentPanel);

        JLabel infoLabel = new JLabel("<html><div style='text-align: center;'>" +
                "<h2>Your Numerology Dashboard</h2>" +
                "<p>Numerology is the study of numbers and their mystical significance in your life.</p>" +
                "<p>Your numerology numbers reveal insights about:</p>" +
                "<ul style='text-align: left;'>" +
                "<li><b>Career:</b> Your professional path and work style</li>" +
                "<li><b>Relationships:</b> How you connect with others</li>" +
                "<li><b>Health:</b> Your physical and mental well-being</li>" +
                "<li><b>Money:</b> Your financial patterns and opportunities</li>" +
                "<li><b>Characteristics:</b> Your core personality traits</li>" +
                "</ul>" +
                "<p>Click below to generate your personalized numerology report!</p>" +
                "</div></html>");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        infoLabel.setBounds(30, 20, 640, 250);
        contentPanel.add(infoLabel);

        // Buttons
        JButton generateReportBtn = new JButton("Generate Numerology Report");
        generateReportBtn.setFont(new Font("Arial", Font.BOLD, 16));
        generateReportBtn.setBounds(200, 280, 300, 50);
        generateReportBtn.setBackground(new Color(70, 130, 180));
        generateReportBtn.setForeground(Color.WHITE);
        contentPanel.add(generateReportBtn);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        logoutBtn.setBounds(650, 500, 100, 30);
        add(logoutBtn);

        generateReportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new NumerologyMainPage(username).setVisible(true);
            }
        });

        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        Dashboard dashboard = new Dashboard("TestUser");
        dashboard.setVisible(true);
    }
}



