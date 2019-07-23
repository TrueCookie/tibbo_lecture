package com.tibbo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class callableTask implements Callable<Integer>{
    private static Integer count = 0;
    private Socket clientSocket = null;

    callableTask(Socket val){
        clientSocket = val;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Thread is running!");
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        count++;
        String word = in.readLine();
        System.out.println("Message accepted: " + word);
        return count;
    }
}
