package com.tibbo;

import com.tibbo.database.DatabaseDAO;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final Server INSTANCE = new Server();
    private ServerSocket serverSocket;
    private static int messageCounter = 0;

    public static void main(String[] args) throws Exception {
        //INSTANCE.launch(args);

        //DatabaseDAO.registerUser("third user", "3");
        //boolean isExist = DatabaseDAO.checkUserExist("first user", "1111");
        //System.out.println(isExist);

        //INSTANCE.connection();
    }

    public void launch(String[] args) throws Exception {
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(4000));
        //serverSocket.setSoTimeout(5000);
        connection();
        messageCounter = MessageThread.getCounter();
        //когда получен accept создать поток, зациклить поток и перейти обратно на получение accept
    }

    public void close() throws IOException {
        serverSocket.close();
    }

    public int getMessageCounter() {
        return messageCounter;
    }

    private static Socket clientSocket;

    public void connection() throws IOException {
        System.out.println("waiting for accept...");
        clientSocket = serverSocket.accept();
        System.out.println("accepted");

        MessageThread thread = new MessageThread(clientSocket, messageCounter);

        /*Thread t = new Thread() {
            public void run() {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String word = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                messageCounter++;
            }
        };
        t.start();*/
    }
}
