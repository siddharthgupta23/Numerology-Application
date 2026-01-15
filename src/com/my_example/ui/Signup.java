package com.my_example.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.my_example.dao.UserDao;
import com.my_example.dto.UserDTO;

public class Signup extends JFrame {
    private JTextField userid;
    private JPasswordField password;
    private JPasswordField confirmPassword;
    private UserDao userDao;

    public Signup() {
        userDao = new UserDao();
        setSize(450, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Sign Up - Numerology App");
        getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("Create New Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(120, 20, 250, 30);
        add(titleLabel);

        JLabel userJLabel = new JLabel("Username:");
        userJLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userJLabel.setBounds(30, 80, 100, 30);
        add(userJLabel);
        
        userid = new JTextField();
        userid.setBounds(150, 80, 250, 30);
        add(userid);

        JLabel passwordJLabel = new JLabel("Password:");
        passwordJLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordJLabel.setBounds(30, 140, 100, 30);
        add(passwordJLabel);
        
        password = new JPasswordField();
        password.setBounds(150, 140, 250, 30);
        add(password);

        JLabel confirmPasswordJLabel = new JLabel("Confirm Password:");
        confirmPasswordJLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        confirmPasswordJLabel.setBounds(30, 200, 120, 30);
        add(confirmPasswordJLabel);
        
        confirmPassword = new JPasswordField();
        confirmPassword.setBounds(150, 200, 250, 30);
        add(confirmPassword);

        JButton signupBtn = new JButton("SIGN UP");
        signupBtn.setFont(new Font("Arial", Font.BOLD, 14));
        signupBtn.setBounds(150, 270, 120, 40);
        add(signupBtn);

        JButton backBtn = new JButton("Back to Login");
        backBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        backBtn.setBounds(150, 330, 120, 30);
        add(backBtn);

        signupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doSignup();
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login().setVisible(true);
            }
        });
    }

    private void doSignup() {
        String uid = userid.getText().trim();
        char[] pwd = password.getPassword();
        char[] confirmPwd = confirmPassword.getPassword();
        String passwordStr = new String(pwd);
        String confirmPasswordStr = new String(confirmPwd);

        // Validation
        if (uid.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username cannot be empty!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (passwordStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password cannot be empty!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (passwordStr.length() < 4) {
            JOptionPane.showMessageDialog(this, "Password must be at least 4 characters long!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!passwordStr.equals(confirmPasswordStr)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Check if user already exists
            if (userDao.userExists(uid)) {
                JOptionPane.showMessageDialog(this, "Username already exists! Please choose another.", "Signup Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Register new user
            UserDTO userDTO = new UserDTO();
            userDTO.setUserid(uid);
            userDTO.setPassword(passwordStr);

            if (userDao.register(userDTO)) {
                JOptionPane.showMessageDialog(this, "Account created successfully! Please login.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new Login().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to create account. Please try again.", "Signup Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Signup signup = new Signup();
        signup.setVisible(true);
    }
}



