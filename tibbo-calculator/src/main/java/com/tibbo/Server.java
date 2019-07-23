package com.tibbo;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final Server INSTANCE = new Server();
    private ServerSocket serverSocket = null;
    private static int messageCounter = 0;
    private List<MessageThread> threads = new ArrayList<>();
    private Thread acceptThread = null;

    public static void main(String[] args) throws Exception {
        //INSTANCE.launch(args );
    }

    public void launch(String[] args) throws Exception {
        //инициализация происходит в потоке
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(4000));
        serverSocket.setSoTimeout(50000);
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

    private void connection() throws IOException, InterruptedException {
        System.out.println("waiting for accept...");
        acceptThread = new Thread() {
            public void run() {
                while (!isInterrupted() && !serverSocket.isClosed()) {
                    try {
                        System.out.println("wait ");
                        Socket clientSocket = serverSocket.accept();
                        if (clientSocket == null) {
                            continue;
                        }
                        System.out.println("accepted");
                        MessageThread thread = new MessageThread(clientSocket);
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
        };
        acceptThread.start();
    }

    private void stopIt(){
        for (MessageThread t : getThreads()) {
            t.interrupt();
        }
        acceptThread.interrupt();
    }

    private List<MessageThread> getThreads() {
        return threads;
    }
}
