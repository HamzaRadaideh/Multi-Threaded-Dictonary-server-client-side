package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class App {
    private static final String SERVER_IP = "127.0.0.1"; // Assuming server is running on localhost
    private static final int SERVER_PORT = 8888;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Connected to server.");

            String word;
            do {
                System.out.print("Enter a word (or type 'exit' to quit): ");
                word = userInput.readLine();
                out.println(word);

                String meaning = in.readLine();
                System.out.println("Meaning: " + meaning);
            } while (!"exit".equalsIgnoreCase(word));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
