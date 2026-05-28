package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

public class WaitingRoomFrame extends JFrame {

    JLabel title;
    JLabel searchingLabel;
    JLabel loadingLabel;

    JButton cancelButton;

    public WaitingRoomFrame(String username) {
        setTitle("Waiting Room");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        title = new JLabel("TicArena");
        title.setFont(new Font("Arial", Font.BOLD, 35));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        searchingLabel = new JLabel("Searching for a player...");
        searchingLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        searchingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loadingLabel = new JLabel("Loading...");
        loadingLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        loadingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension buttonsize = new Dimension(180, 40);

        cancelButton = new JButton("Cancel");
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.setMaximumSize(buttonsize);
        cancelButton.addActionListener(
                e -> {
                    new LobbyFrame(username);
                    dispose();
                });
        
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(searchingLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(loadingLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(cancelButton);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);
        
        setVisible(true);
    }

}