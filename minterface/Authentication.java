package minterface;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import user.Lecturer;
import user.Student;
import user.users;

public abstract class Authentication {

    public static void main(String[] args) {
        loginGUI();
    }

    public abstract void register();

    public static void loginGUI() {
        JFrame frame = new JFrame("Login");
        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background panel with gradient
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
        backgroundPanel.setLayout(null); // absolute positioning
        frame.setContentPane(backgroundPanel);

        // Title
        JLabel title = new JLabel("Welcome Back!", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setBounds(50, 20, 350, 40);
        backgroundPanel.add(title);

        // Username
        JLabel userLabel = new JLabel("Username (email):");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(50, 80, 150, 25);
        backgroundPanel.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(200, 80, 180, 25);
        backgroundPanel.add(userText);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(50, 120, 150, 25);
        backgroundPanel.add(passLabel);

        JPasswordField passText = new JPasswordField();
        passText.setBounds(200, 120, 180, 25);
        backgroundPanel.add(passText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50, 170, 100, 30);
        backgroundPanel.add(loginButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(200, 170, 100, 30);
        backgroundPanel.add(resetButton);

        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setBounds(50, 220, 330, 25); 
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
                System.out.println("Login successful for user: " + username);

                StringBuilder info = new StringBuilder();
                info.append("ID: ").append(loggedUser.getId()).append("\n");
                info.append("Name: ").append(loggedUser.getName()).append("\n");
                info.append("Email: ").append(loggedUser.getEmail()).append("\n");
                info.append("Phone: ").append(loggedUser.getPhoneNumber()).append("\n");
                

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
                System.out.println("Failed login attempt for user: " + username);
            }
        });

        resetButton.addActionListener(e -> {
            userText.setText("");
            passText.setText("");
            resultLabel.setText("");
        });

        frame.setVisible(true);
    }
}

