package gui;

import database.UserDAO;
import java.awt.*;
import javax.swing.*;

public class RegisterFrame extends JFrame {

        JButton registerButton;
        JButton backButton;

        JTextField usernameField;
        JPasswordField passwordField;
        JPasswordField confirmField;

        public RegisterFrame() {

                setTitle("Register");
                setSize(500, 500);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

                JLabel title = new JLabel("REGISTER");
                title.setFont(new Font("Arial", Font.BOLD, 35));
                title.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel subtitle = new JLabel("Create your account");
                subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
                subtitle.setForeground(Color.DARK_GRAY);
                subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

                JPanel formWrapper = new JPanel(new FlowLayout());

                JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));

                formPanel.setPreferredSize(new Dimension(320, 140));

                JLabel l1 = new JLabel("Username :");
                usernameField = new JTextField();

                JLabel l2 = new JLabel("Password :");
                passwordField = new JPasswordField();

                JLabel l3 = new JLabel("Confirm Password :");
                confirmField = new JPasswordField();

                formPanel.add(l1);
                formPanel.add(usernameField);

                formPanel.add(l2);
                formPanel.add(passwordField);

                formPanel.add(l3);
                formPanel.add(confirmField);

                formWrapper.add(formPanel);

                registerButton = new JButton("Register");
                backButton = new JButton("Back");

                Dimension buttonSize = new Dimension(150, 40);

                registerButton.setMaximumSize(buttonSize);
                backButton.setMaximumSize(buttonSize);
                
                registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                backButton.addActionListener(
                        e -> {
                        new WelcomeFrame();
                        dispose();
                });

                registerButton.addActionListener(
                        e -> {
                                String username = usernameField.getText();
                                String password = String.valueOf(passwordField.getPassword());
                                String confirmPassword = String.valueOf(confirmField.getPassword());
                                if (username.isEmpty()|| password.isEmpty()) {
                                        JOptionPane.showMessageDialog(null,
                                        "Fields cannot be empty");      
                                        return;
                                        }

                                if (!password.equals(confirmPassword)) {
                                        JOptionPane.showMessageDialog(null,
                                        "Passwords do not match");
                                        return;
                                        }
                                UserDAO dao = new UserDAO();
                                boolean success = dao.registerUser(username,password);
                                if (success) {
                                        JOptionPane.showMessageDialog(null,
                                        "Registration Successful");
                                        usernameField.setText("");
                                        passwordField.setText("");
                                        confirmField.setText("");
                                        }
                                else{
                                JOptionPane.showMessageDialog(null,
                                "Registration Failed");
                        }
                                });

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(subtitle);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(formWrapper);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(registerButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(backButton);
        mainPanel.add(Box.createVerticalGlue());
                
        add(mainPanel);
        setVisible(true);
        }
}