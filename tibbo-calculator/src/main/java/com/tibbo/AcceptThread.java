package com.tibbo;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.List;

public class AcceptThread implements Runnable{
    private ServerSocket serverSocket;
    private List<MessageThread> threads;
    private Server currentServer;

    AcceptThread(ServerSocket serverSocket, List<MessageThread> threads, Server server){
        currentServer = server;
        this.serverSocket = serverSocket;
        this.threads = threads;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && !serverSocket.isClosed()) {
            try {
                System.out.println("waiting for request from client");
                Socket clientSocket = serverSocket.accept();
                if (clientSocket == null) {
                    continue;
                }
                System.out.println("Client request accepted");
                MessageThread thread = new MessageThread(clientSocket, currentServer);
                threads.add(thread);
                thread.start();
            } catch (SocketException ignored) {
                System.out.println("Socket was closed before its recieve smth");
            } catch (SocketTimeoutException ignored) {
                System.out.println("timeout");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
