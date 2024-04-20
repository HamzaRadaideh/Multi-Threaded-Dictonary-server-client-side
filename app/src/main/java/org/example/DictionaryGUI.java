package org.example;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DictionaryGUI extends JFrame {

    public static ServerConnection sercon = new ServerConnection();

    private JTextArea outputArea;
    private JTextField inputField;

    public DictionaryGUI() {
        super("Dictionary Client");

        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        inputField = new JTextField(20);
        JButton sendButton = new JButton("Send");

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.append(sercon.sendMessage(inputField.getText()));
                inputField.setText("");
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter a word:"));
        inputPanel.add(inputField);
        inputPanel.add(sendButton);

        // Add ActionListener to the sendButton
        sendButton.addActionListener(e -> {
            // Perform your desired action here
            System.out.println("Send button clicked!");
        });

        // Add KeyListener to the inputField
        inputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Simulate a click on the sendButton when Enter is pressed
                    sendButton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);

        outputArea.append(sercon.connectToServer());

    }

}
