package com.tibbo;

import com.tibbo.database.DatabaseDAO;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Server {
    private static final Server INSTANCE = new Server();
    private ServerSocket serverSocket = null;
    private static int messageCounter = 0;
    MessageThread[] threads = new MessageThread[3];

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
                //MessageThread[] threads = new MessageThread[3];
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

    /*public void connection() throws IOException, InterruptedException {
        System.out.println("waiting for accept...");
        Thread acceptThread = new Thread(() -> {
            try {
                run();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        acceptThread.start();
    }

    private void run() throws ExecutionException, InterruptedException, IOException {
        clientSocket = serverSocket.accept();
        System.out.println("accepted");
        Callable<Integer> task = new callableTask(clientSocket);
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; ++i) {
            Future result = service.submit(task);
            //System.out.println(result.get());
            messageCounter = (int)result.get();
        }

        service.shutdown();
    }*/
}
