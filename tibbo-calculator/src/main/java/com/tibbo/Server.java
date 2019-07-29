package com.tibbo;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final Server INSTANCE = new Server();
    private ServerSocket serverSocket = null;
    private int messageCounter = 0;
    private List<MessageThread> threads = new ArrayList<>();
    private Thread acceptThreadStarter;
    private static Integer port = 1025;//early 5000

    public static void main(String[] args) throws Exception {
        port = Integer.parseInt(args[0]);
        INSTANCE.launch(port);
    }

    public void launch(Integer port) throws Exception {
        //инициализация происходит в потоке
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));
        serverSocket.setSoTimeout(10000);
        connection();
    }

    public void close() throws IOException {
        serverSocket.close();
        stopIt();
    }

    public int getMessageCounter() {
        return messageCounter;
    }

    public synchronized void increaseMessageCounter() {
        ++messageCounter;
    }

    private void connection() throws IOException, InterruptedException {
        System.out.println("waiting for accept...");
        AcceptThread acceptThread = new AcceptThread(serverSocket, threads, this);
        acceptThreadStarter = new Thread(acceptThread);
        acceptThreadStarter.start();
    }

    private void stopIt(){
        for (MessageThread t : getThreads()) {
            t.interrupt();
        }
        acceptThreadStarter.interrupt();
    }

    private List<MessageThread> getThreads() {
        return threads;
    }
}
