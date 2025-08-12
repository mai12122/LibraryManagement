package minterface;

import java.awt.*;
import javax.swing.*;
import user.Lecturer;
import user.Student;
import user.users;

public abstract class Authentication {

    public abstract void register();

    public static void loginGUI() {
        JFrame frame = new JFrame("Login");
        frame.setSize(420, 300);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        JLabel userLabel = new JLabel("Username (email):");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(50, 50, 150, 25);
        frame.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(200, 50, 150, 25);
        frame.add(userText);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(50, 100, 150, 25);
        frame.add(passLabel);

        JPasswordField passText = new JPasswordField();
        passText.setBounds(200, 100, 150, 25);
        frame.add(passText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50, 160, 100, 30);
        frame.add(loginButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(200, 160, 100, 30);
        frame.add(resetButton);

        JLabel resultLabel = new JLabel("");
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setBounds(50, 210, 300, 25);
        frame.add(resultLabel);

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
                    info.append("Recommendation: ").append(l.getRecommendation() == null ? "None" : l.getRecommendation()).append("\n");
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

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
