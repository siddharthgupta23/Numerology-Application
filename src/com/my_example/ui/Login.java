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

public class Login extends JFrame {
    private JTextField userid;
    private JPasswordField password;
    private UserDao userDao;

    public Login() {
        userDao = new UserDao();
        setSize(450, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login - Numerology App");
        getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("Welcome to Numerology App");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(80, 30, 300, 30);
        add(titleLabel);

        JLabel userJLabel = new JLabel("Username:");
        userJLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userJLabel.setBounds(50, 100, 100, 30);
        add(userJLabel);
        
        userid = new JTextField();
        userid.setBounds(150, 100, 200, 35);
        add(userid);

        JLabel passwordJLabel = new JLabel("Password:");
        passwordJLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordJLabel.setBounds(50, 160, 100, 30);
        add(passwordJLabel);
        
        password = new JPasswordField();
        password.setBounds(150, 160, 200, 35);
        add(password);

        JButton loginBtn = new JButton("LOGIN");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loginBtn.setBounds(120, 230, 100, 40);
        add(loginBtn);

        JButton signupBtn = new JButton("SIGN UP");
        signupBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        signupBtn.setBounds(240, 230, 100, 40);
        add(signupBtn);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doLogin();
            }
        });

        signupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Signup().setVisible(true);
            }
        });
    }

    private void doLogin() {
        String uid = userid.getText().trim();
        char[] pwd = password.getPassword();
        String passwordStr = new String(pwd);

        // Validation
        if (uid.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (passwordStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter password!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserid(uid);
            userDTO.setPassword(passwordStr);

            String result = userDao.auth(userDTO);
            
            if (result.startsWith("Welcome")) {
                JOptionPane.showMessageDialog(this, result, "Login Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new Dashboard(uid).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, result, "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
    }
}
