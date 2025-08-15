package minterface;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import javax.swing.*;
import user.Lecturer;
import user.Student;
import user.users;

public abstract class Authentication {

    public static void main(String[] args) {
        loginGUI();
    }

    public abstract void register();

    // --- LOGIN GUI ---
    public static void loginGUI() {
        JFrame frame = new JFrame("Library Management System - Login");
        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(58, 123, 213),
                        0, getHeight(), new Color(58, 213, 176)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(null);
        frame.setContentPane(backgroundPanel);

        JLabel title = new JLabel("Welcome Back!", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setBounds(50, 20, 350, 40);
        backgroundPanel.add(title);

        JLabel userLabel = new JLabel("Username (email):");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(50, 80, 150, 25);
        backgroundPanel.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(200, 80, 180, 25);
        backgroundPanel.add(userText);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(50, 120, 150, 25);
        backgroundPanel.add(passLabel);

        JPasswordField passText = new JPasswordField();
        passText.setBounds(200, 120, 180, 25);
        backgroundPanel.add(passText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50, 170, 120, 35);
        styleButton(loginButton, new Color(46, 204, 113), Color.WHITE);
        backgroundPanel.add(loginButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(200, 170, 120, 35);
        styleButton(resetButton, new Color(231, 76, 60), Color.WHITE);
        backgroundPanel.add(resetButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(125, 220, 180, 35);
        styleButton(registerButton, new Color(52, 152, 219), Color.WHITE);
        backgroundPanel.add(registerButton);

        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setBounds(50, 270, 330, 25);
        backgroundPanel.add(resultLabel);

        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passText.getPassword());

            users loggedUser = null;
            for (users acc : users.accountList) {
                if (username.equals(acc.getUsername()) && password.equals(acc.getPassword())) {
                    loggedUser = acc;
                    break;
                }
            }

            if (loggedUser != null) {
                resultLabel.setForeground(Color.GREEN);
                resultLabel.setText("Login successful");

                StringBuilder info = new StringBuilder();
                info.append("ID: ").append(loggedUser.getId()).append("\n");
                info.append("Name: ").append(loggedUser.getName()).append("\n");
                info.append("Email: ").append(loggedUser.getEmail()).append("\n");

                if (loggedUser instanceof Student) {
                    Student s = (Student) loggedUser;
                    info.append("Student Class: ").append(s.getStudentClass()).append("\n");
                    info.append("Enrollment Date: ").append(s.getEnrollmentDate()).append("\n");
                } else if (loggedUser instanceof Lecturer) {
                    Lecturer l = (Lecturer) loggedUser;
                    info.append("Department: ").append(l.getDepartment()).append("\n");
                    info.append("Recommendation: ")
                        .append(l.getRecommendation() == null ? "None" : l.getRecommendation())
                        .append("\n");
                }

                JOptionPane.showMessageDialog(frame, "Welcome " + username + "!\n\n" + info.toString());
                frame.dispose();
            } else {
                resultLabel.setForeground(Color.RED);
                resultLabel.setText("Invalid credentials");
            }
        });

        resetButton.addActionListener(e -> {
            userText.setText("");
            passText.setText("");
            resultLabel.setText("");
        });

        registerButton.addActionListener(e -> showRegisterGUI());

        frame.setVisible(true);
    }

    public static void showRegisterGUI() {
        JDialog registerDialog = new JDialog((Frame) null, "Register", true);
        registerDialog.setSize(450, 400);
        registerDialog.setLocationRelativeTo(null);
        registerDialog.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(58, 123, 213));
        registerDialog.setContentPane(panel);

        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setBounds(50, 20, 350, 40);
        panel.add(title);

        JLabel typeLabel = new JLabel("Account Type:");
        typeLabel.setForeground(Color.WHITE);
        typeLabel.setBounds(50, 80, 120, 25);
        panel.add(typeLabel);

        String[] types = {"Student", "Lecturer"};
        JComboBox<String> typeCombo = new JComboBox<>(types);
        typeCombo.setBounds(180, 80, 180, 25);
        panel.add(typeCombo);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setForeground(Color.WHITE);
        idLabel.setBounds(50, 120, 120, 25);
        panel.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(180, 120, 180, 25);
        panel.add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(50, 160, 120, 25);
        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(180, 160, 180, 25);
        panel.add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(50, 200, 120, 25);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(180, 200, 180, 25);
        panel.add(emailField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(50, 240, 120, 25);
        panel.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(180, 240, 180, 25);
        panel.add(passField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 290, 120, 35);
        styleButton(registerButton, new Color(46, 204, 113), Color.WHITE);
        panel.add(registerButton);

        registerButton.addActionListener(e -> {
            String type = (String) typeCombo.getSelectedItem();
            String id = idField.getText();
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passField.getPassword());

            if (id.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(registerDialog, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            users newUser = null;
            if (type.equals("Student")) {
                newUser = new Student(id, name, email, password, "Class A", LocalDate.now());
            } else if (type.equals("Lecturer")) {
                newUser = new Lecturer(id, name, email, password, "CS Department", null); 
            }

            users.accountList.add(newUser);
            JOptionPane.showMessageDialog(registerDialog, "Registration successful!");
            registerDialog.dispose();
        });

        registerDialog.setVisible(true);
    }
    private static void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
    }
}
