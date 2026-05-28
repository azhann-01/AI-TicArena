package gui;

import java.awt.*;
import javax.swing.*;

public class LobbyFrame extends JFrame {

    JButton findMatchButton;
    JButton friendButton;
    JButton leaderboardButton;
    JButton logoutButton;

    public LobbyFrame(String username) {

        setTitle("TicArena");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("TICARENA");
        title.setFont(new Font("Arial", Font.BOLD, 35));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel welcome = new JLabel("Welcome, " + username);
        welcome.setFont(new Font("Arial", Font.PLAIN, 15));
        welcome.setForeground(Color.DARK_GRAY);
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension buttonSize = new Dimension(180, 40);

        findMatchButton = new JButton("Find Match");
        findMatchButton.addActionListener(
                e -> {
                    new WaitingRoomFrame(username);
                    dispose();
                });

        friendButton = new JButton("Play With Friend");

        leaderboardButton = new JButton("Leaderboard");

        logoutButton = new JButton("Logout");

        JButton buttons[] = { findMatchButton, friendButton, leaderboardButton, logoutButton };

        for (JButton b : buttons) {
            b.setMaximumSize(buttonSize);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        logoutButton.addActionListener(
                e -> {
                    new WelcomeFrame();
                    dispose();
                });

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(welcome);
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(findMatchButton);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(friendButton);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(leaderboardButton);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(logoutButton);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);

        setVisible(true);
    }
}