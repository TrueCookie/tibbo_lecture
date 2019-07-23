package com.tibbo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final Server INSTANCE = new Server();
    private ServerSocket serverSocket = null;
    private static int messageCounter = 0;
    MessageThread[] threads = new MessageThread[3];

    public static void main(String[] args) throws Exception {
        //INSTANCE.launch(args );
    }

    public void launch(String[] args) throws Exception {
        //инициализация происходит в потоке
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(4000));
        serverSocket.setSoTimeout(5000);
        connection();
    }

    public void close() throws IOException {
        serverSocket.close();
        stopIt();
    }

    public int getMessageCounter() {
        return messageCounter;
    }

    public static synchronized void increaseMessageCounter() {
        ++messageCounter;
    }

    private static Socket clientSocket;

    public void connection() throws IOException, InterruptedException {
        System.out.println("waiting for accept...");
        Thread acceptThread = new Thread() {
            public void run() {
                clientSocket = null;
                try {
                    for (int i = 0; i < 3; ++i) {
                        clientSocket = serverSocket.accept();
                        System.out.println("accepted");
                        threads[i] = new MessageThread(clientSocket);
                        threads[i].start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        acceptThread.start();
    }

    public void stopIt(){
        for (int i = 0; i < 3; ++i) {
            threads[i].interrupt();
        }
    }
}
