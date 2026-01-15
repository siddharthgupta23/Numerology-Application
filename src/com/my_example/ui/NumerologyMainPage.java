package com.my_example.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.my_example.dao.NumerologyReportDao;
import com.my_example.dto.NumerologyDTO;
import com.my_example.service.NumberCalculator;
import com.my_example.service.NumerologyInterpretationService;
import com.my_example.service.PDFGeneratorService;

public class NumerologyMainPage extends JFrame {
    private String loggedInUsername;
    private JTextField nameField;
    private JTextField dobField;
    private NumberCalculator numberCalculator;
    private NumerologyInterpretationService interpretationService;
    private PDFGeneratorService pdfGeneratorService;
    private NumerologyReportDao reportDao;

    public NumerologyMainPage(String loggedInUsername) {
        this.loggedInUsername = loggedInUsername;
        this.numberCalculator = new NumberCalculator();
        this.interpretationService = new NumerologyInterpretationService();
        this.pdfGeneratorService = new PDFGeneratorService(interpretationService);
        this.reportDao = new NumerologyReportDao();

        setSize(700, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Numerology Calculator - Numerology App");
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(245, 245, 250));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 700, 80);
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setLayout(null);
        add(headerPanel);

        JLabel titleLabel = new JLabel("Numerology Calculator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(200, 20, 300, 40);
        headerPanel.add(titleLabel);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setBounds(50, 100, 600, 300);
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(null);
        add(formPanel);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nameLabel.setBounds(50, 40, 120, 30);
        formPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameField.setBounds(180, 40, 350, 35);
        formPanel.add(nameField);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dobLabel.setBounds(50, 100, 120, 30);
        formPanel.add(dobLabel);

        dobField = new JTextField();
        dobField.setFont(new Font("Arial", Font.PLAIN, 14));
        dobField.setBounds(180, 100, 350, 35);
        dobField.setToolTipText("Format: DD-MM-YYYY (e.g., 15-05-1990)");
        formPanel.add(dobField);

        JLabel formatLabel = new JLabel("Format: DD-MM-YYYY");
        formatLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        formatLabel.setForeground(Color.GRAY);
        formatLabel.setBounds(180, 135, 200, 20);
        formPanel.add(formatLabel);

        JButton generateBtn = new JButton("Generate Report");
        generateBtn.setFont(new Font("Arial", Font.BOLD, 16));
        generateBtn.setBounds(200, 200, 200, 50);
        generateBtn.setBackground(new Color(70, 130, 180));
        generateBtn.setForeground(Color.WHITE);
        formPanel.add(generateBtn);

        // Navigation buttons
        JButton backBtn = new JButton("Back to Dashboard");
        backBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        backBtn.setBounds(50, 420, 150, 30);
        add(backBtn);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        logoutBtn.setBounds(500, 420, 100, 30);
        add(logoutBtn);

        generateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport();
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Dashboard(loggedInUsername).setVisible(true);
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

    private void generateReport() {
        String name = nameField.getText().trim();
        String dobStr = dobField.getText().trim();

        // Validation
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your full name!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (dobStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your date of birth!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Parse date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dateOfBirth = null;
        try {
            dateOfBirth = sdf.parse(dobStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format! Please use DD-MM-YYYY format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Calculate numerology numbers
            NumerologyDTO numerologyDTO = new NumerologyDTO();
            numerologyDTO.setUsername(name);
            numerologyDTO.setDateOfBirth(dateOfBirth);
            numerologyDTO.setLifePathNumber(numberCalculator.calculateLifePathNumber(dateOfBirth));
            numerologyDTO.setDestinyNumber(numberCalculator.calculateDestinyNumber(name));
            numerologyDTO.setSoulNumber(numberCalculator.calculateSoulNumber(name));
            numerologyDTO.setPersonalityNumber(numberCalculator.calculatePersonalityNumber(name));

            // Generate PDF
            String filePath = pdfGeneratorService.generatePDF(numerologyDTO, name, dateOfBirth);

            // Save report to database
            try {
                boolean saved = reportDao.saveReport(loggedInUsername, numerologyDTO);
                if (saved) {
                    System.out.println("Report saved to database successfully");
                } else {
                    System.out.println("Failed to save report to database");
                }
            } catch (Exception dbEx) {
                System.err.println("Error saving report to database: " + dbEx.getMessage());
                // Continue even if database save fails - PDF is already generated
            }

            // Show success message and ask to open file
            int option = JOptionPane.showConfirmDialog(
                this,
                "Report generated successfully!\nFile: " + filePath + "\n\nWould you like to open the file?",
                "Success",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE
            );

            if (option == JOptionPane.YES_OPTION) {
                try {
                    File file = new File(filePath);
                    if (file.exists()) {
                        java.awt.Desktop.getDesktop().open(file);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Could not open file automatically. File saved at: " + filePath, "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            // Show numerology summary
            showNumerologySummary(numerologyDTO);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error generating report: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void showNumerologySummary(NumerologyDTO numerologyDTO) {
        StringBuilder summary = new StringBuilder();
        summary.append("Your Numerology Numbers:\n\n");
        summary.append("Life Path Number: ").append(numerologyDTO.getLifePathNumber()).append("\n");
        summary.append("Destiny Number: ").append(numerologyDTO.getDestinyNumber()).append("\n");
        summary.append("Soul Number: ").append(numerologyDTO.getSoulNumber()).append("\n");
        summary.append("Personality Number: ").append(numerologyDTO.getPersonalityNumber()).append("\n");

        JOptionPane.showMessageDialog(this, summary.toString(), "Your Numbers", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        NumerologyMainPage page = new NumerologyMainPage("TestUser");
        page.setVisible(true);
    }
}



