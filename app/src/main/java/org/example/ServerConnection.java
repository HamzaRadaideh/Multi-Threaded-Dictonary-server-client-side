package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection {

    private static boolean isConnected = false;

    private static final String SERVER_IP = "127.0.0.1"; // Assuming server is running on localhost
    private static final int SERVER_PORT = 8888;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public String connectToServer() {
        while (!isConnected) {
            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                if (socket.isConnected()) {
                    isConnected = true;
                }

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                return "Connected to server.\n";
            } catch (IOException e) {
                isConnected = false;
                return "Error connecting to server: " + e.getMessage() + "\n";
            }
        }

        return "";
    }

    public String sendMessage(String word) {
        connectToServer();

        if (!word.isEmpty()) {
            out.println(word);
            try {
                String meaning = in.readLine();
                return ("Meaning: " + meaning + "\n");
            } catch (IOException e) {
                isConnected = false;
                return ("Error receiving message: " + e.getMessage() + "\n");
            }
        }

        return "";
    }

}
