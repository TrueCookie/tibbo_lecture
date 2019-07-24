package com.tibbo;

import java.io.*;
import java.net.Socket;

import static com.tibbo.Server.increaseMessageCounter;

class MessageThread extends Thread {
    private Socket clientSocket;

    public MessageThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        System.out.println("Thread is running!");
        DataInputStream in;
        while (!isInterrupted() && !clientSocket.isClosed()) {
            try {
                in = new DataInputStream(clientSocket.getInputStream());
                if (in.available() <= 0) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        break;
                    }
                    continue;
                }
                String word = in.readUTF();
                increaseMessageCounter();
                System.out.println("Message accepted: " + word);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}