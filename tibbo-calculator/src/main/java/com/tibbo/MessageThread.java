package com.tibbo;

import java.io.*;
import java.net.Socket;

import static com.tibbo.Server.increaseMessageCounter;

class MessageThread extends Thread {
    Thread thread;
    private static Socket clientSocket;

    public MessageThread(Socket socket) {
        clientSocket = socket;
        thread = new Thread(this, "new thread");
    }

    public void run() {
        System.out.println("Thread is running!");
        String word = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            increaseMessageCounter();
            word = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Message accepted: " + word);
    }
}