package com.tibbo;

import sun.rmi.runtime.Log;

import java.io.*;
import java.net.Socket;

class MessageThread implements Runnable {
    Thread thread;
    private static int counter;
    private static Socket clientSocket;

    public MessageThread(Socket socket, int messageCounter) {
        int counter = messageCounter;
        clientSocket = socket;
        thread = new Thread(this, "new thread");
        thread.start();
    }

    public void run() {
        System.out.println("Thread is running!");
        String word = null;
        try {
            //clientSocket.getInputStream().read();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            word = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        counter++;
        System.out.println("Message accepted: "+word);
    }

    public static int getCounter(){
        return counter;
    }
}