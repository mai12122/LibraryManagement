package user;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class userprofile {

    private int accountId; 
    private JFrame frame;
    private JTextField nameField, emailField, phoneField;
    private JPasswordField passwordField;
    private JButton editButton, updateButton;

    public userprofile(int accountId) {
        this.accountId = accountId;
        SwingUtilities.invokeLater(this::createGUI);
    }

    private void createGUI() {
        frame = new JFrame("User Profile");
        frame.setSize(450, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel() {
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
        panel.setLayout(null);

        JLabel title = new JLabel("User Profile", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBounds(50, 20, 350, 40);
        panel.add(title);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(50, 80, 120, 25);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(180, 80, 180, 25);
        nameField.setEditable(false);
        panel.add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(50, 120, 120, 25);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(180, 120, 180, 25);
        emailField.setEditable(false); 
        panel.add(emailField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setForeground(Color.WHITE);
        phoneLabel.setBounds(50, 160, 120, 25);
        panel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(180, 160, 180, 25);
        phoneField.setEditable(false); 
        panel.add(phoneField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(50, 200, 120, 25);
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 200, 180, 25);
        passwordField.setEditable(false); 
        panel.add(passwordField);

        
        editButton = new JButton("Edit");
        editButton.setBounds(50, 260, 100, 35);
        styleButton(editButton, new Color(52, 152, 219), Color.WHITE);
        panel.add(editButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(180, 260, 120, 35);
        styleButton(updateButton, new Color(46, 204, 113), Color.WHITE);
        updateButton.setEnabled(false); 
        panel.add(updateButton);

        editButton.addActionListener(e -> enableEditing(true));
        updateButton.addActionListener(e -> updateProfile());

        frame.setContentPane(panel);
        frame.setVisible(true);

        loadUserInfo(); 
    }

    private void enableEditing(boolean enable) {
        nameField.setEditable(enable);
        emailField.setEditable(enable);
        phoneField.setEditable(enable);
        passwordField.setEditable(enable);
        updateButton.setEnabled(enable);
    }

    private void loadUserInfo() {
        String query = "SELECT username, email, phone_number, user_password FROM Accounts WHERE id = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nameField.setText(rs.getString("username"));
                emailField.setText(rs.getString("email"));
                phoneField.setText(rs.getString("phone_number"));
                passwordField.setText(rs.getString("user_password"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Failed to load user info: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateProfile() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields are required!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String query = "UPDATE Accounts SET username = ?, email = ?, phone_number = ?, user_password = ? WHERE id = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, password);
            ps.setInt(5, accountId);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            enableEditing(false); 

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Failed to update profile: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { button.setBackground(bgColor.darker()); }
            @Override
            public void mouseExited(MouseEvent e) { button.setBackground(bgColor); }
        });
    }

    public static void main(String[] args) {
        new userprofile(1); 
    }
}
