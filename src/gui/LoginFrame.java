package gui;

import database.UserDAO;
import java.awt.*;
import javax.swing.*;

public class LoginFrame extends JFrame {

    JButton loginButton;
    JButton backButton;

    JTextField usernameField;
    JPasswordField passwordField;

    public LoginFrame() {

        setTitle("Login");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("LOGIN");
        title.setFont(new Font("Arial", Font.BOLD, 35));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Welcome Back");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setForeground(Color.DARK_GRAY);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel formWrapper = new JPanel();
        formWrapper.setLayout(new FlowLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setPreferredSize(new Dimension(320, 140));

        JLabel l1 = new JLabel("Username :");
        usernameField = new JTextField();

        JLabel l2 = new JLabel("Password :");
        passwordField = new JPasswordField();

        formWrapper.add(formPanel);
        formPanel.add(l1);
        formPanel.add(usernameField);
        formPanel.add(l2);
        formPanel.add(passwordField);

        loginButton = new JButton("Login");
        backButton = new JButton("Back");

        Dimension buttonSize = new Dimension(150, 40);

        loginButton.setMaximumSize(buttonSize);
        backButton.setMaximumSize(buttonSize);

        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        backButton.addActionListener(
            e -> {
                new WelcomeFrame();
                dispose();
                });

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(subtitle);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(formWrapper);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(loginButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(backButton);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);

        loginButton.addActionListener(
            e -> {
                String username = usernameField.getText();
                String password = String.valueOf(
                passwordField.getPassword());
                    
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(
                    null,
                    "Fields cannot be empty");
                    return;
                }

                UserDAO dao = new UserDAO();

                boolean success = dao.loginUser(
                    username,
                    password);

                if (success) {
                    new LobbyFrame(username);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null,
                        "Invalid Username or Password");
                    }
                });

        setVisible(true);
    }
}