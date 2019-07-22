package com.tibbo;

import com.tibbo.database.DatabaseDAO;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final Server INSTANCE = new Server();
    private ServerSocket serverSocket;
    private static int messageCounter = 0;

    public static void main(String[] args) throws Exception {
        //INSTANCE.launch(args );

        //DatabaseDAO.registerUser("third user", "3");
        //boolean isExist = DatabaseDAO.checkUserExist("first user", "1111");
        //System.out.println(isExist);

        //INSTANCE.connection();
    }

    public void launch(String[] args) throws Exception {
        //инициализация происходит в потоке
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(4000));
        //serverSocket.setSoTimeout(5000);
        //connection  мы вызываем в том же потоке
        connection();
        //когда получен accept создать поток, зациклить поток и перейти обратно на получение accept
    }

    public void close() throws IOException {
        serverSocket.close();
    }

    public int getMessageCounter() {
        return messageCounter;
    }
    public static void increaseMessageCounter() {
        ++messageCounter;
    }
    public static void increase(int val) {
        ++val;
    }
    private static Socket clientSocket;

    public synchronized void connection() throws IOException, InterruptedException {
        System.out.println("waiting for accept...");
        Thread acceptThread = new Thread() {
            public void run() {
                clientSocket = null;
                MessageThread[] threads = new MessageThread[3];
                try {
                    for (int i = 0; i < 3; ++i) {
                        clientSocket = serverSocket.accept();
                        System.out.println("accepted");
                        threads[i] = new MessageThread(clientSocket);
                        threads[i].start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();//Вот ты УЖЕ знаешь что clientThread == null
                } /*catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                /*for (int i = 0; i < 3; ++i) {
                    try {
                        threads[i].join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
            }
        };
        acceptThread.start();
    }
}
