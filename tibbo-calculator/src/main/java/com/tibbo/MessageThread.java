package com.tibbo;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

import java.io.*;
import java.net.Socket;

import static com.tibbo.Server.increaseMessageCounter;

class MessageThread extends Thread {
    private Socket clientSocket;

    MessageThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        System.out.println("Thread is running!");
        while (!isInterrupted() && !clientSocket.isClosed()) {
            try {
                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                if (in.available() <= 0) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        break;
                    }
                    continue;
                }
                String word = in.readUTF();
                String result = solve(word);
                System.out.println("Message accepted: " + word);
                increaseMessageCounter();

                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                out.writeUTF(result);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String solve(String expression) {
        Evaluator evaluator = new Evaluator();
        String result = null;
        try {
            result = evaluator.evaluate(expression);
        } catch (EvaluationException e) {
            System.out.println("Invalid Expression was accepted");
            return ServerMessagesHelper.MESSAGE_ERROR;
        }
        //increaseMessageCounter();
        return parseFloatingPoint(result);
    }

    private String parseFloatingPoint(String str){
        String strEnd = str.substring(str.length()-2);
        if(strEnd.equals(".0")){
            return str.substring(0, str.length()-2);
        }else{
            return str;
        }
    }
}