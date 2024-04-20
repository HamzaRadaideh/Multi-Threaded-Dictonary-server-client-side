package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class App extends JFrame {
    private static final String SERVER_IP = "127.0.0.1"; // Assuming server is running on localhost
    private static final int SERVER_PORT = 8888;

    private JTextArea outputArea;
    private JTextField inputField;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public App() {
        super("Dictionary Client");

        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        inputField = new JTextField(20);
        JButton sendButton = new JButton("Send");

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter a word:"));
        inputPanel.add(inputField);
        inputPanel.add(sendButton);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);

        connectToServer();
    }

    private void connectToServer() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            outputArea.append("Connected to server.\n");
        } catch (IOException e) {
            outputArea.append("Error connecting to server: " + e.getMessage() + "\n");
        }
    }

    private void sendMessage() {
        String word = inputField.getText().trim();
        if (!word.isEmpty()) {
            out.println(word);
            inputField.setText(""); // Clear the input field
            try {
                String meaning = in.readLine();
                outputArea.append("Meaning: " + meaning + "\n");
            } catch (IOException e) {
                outputArea.append("Error receiving message: " + e.getMessage() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });
    }
}
