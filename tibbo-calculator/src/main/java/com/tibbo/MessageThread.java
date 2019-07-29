package com.tibbo;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import java.io.*;
import java.net.Socket;

class MessageThread extends Thread {
    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private Evaluator evaluator;
    private Server currentServer;

    private static final String START_MSG = "/start";
    private static final String START_MSG_REPLY = "Just type your ariphmetical expression below\uD83D\uDE0C";
    private static final String HELP_OPERATOR_1 = "-h";
    private static final String HELP_OPERATOR_2 = "--help";
    private static final String LIFE_MEANING_QUESTION = "What's the meaning of life?";
    private static final String LIFE_MEANING = "42";
    private static final String INTEGER_END = ".0";
    private static final String HELP_MSG = "It's calculator program, built using jEval library.\n For more info go http://jeval.sourceforge.net/docs/api/net/sourceforge/jeval/Evaluator.html";
    private static final String ERROR_MSG = "Something went wrong. For help type \"--help\" or \"-h\"";
    MessageThread(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        currentServer = server;
        try {
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        evaluator = new Evaluator();
    }

    public void run() {
        System.out.println("Thread is running!");
        while (!isInterrupted() && !clientSocket.isClosed()) {
            try {
                if (in.available() <= 0) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        break;
                    }
                    continue;
                }
                String word = in.readUTF();
                String result;
                if(word.equals(START_MSG)){
                    result = START_MSG_REPLY;
                }else if (word.equals(HELP_OPERATOR_1) || word.equals(HELP_OPERATOR_2)) {
                    result = HELP_MSG;
                } else if(word.equals(LIFE_MEANING_QUESTION)) {
                    result = LIFE_MEANING;
                } else {
                    result = solve(word);
                }
                System.out.println("Message accepted: " + word);
                currentServer.increaseMessageCounter();
                out.writeUTF(result);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String solve(String expression) {
        String result;
        try {
            result = evaluator.evaluate(expression);
        } catch (EvaluationException e) {
            return ServerMessagesHelper.MESSAGE_ERROR;
        }
        return parseFloatingPoint(result);
    }

    private String parseFloatingPoint(String str) {
        if (str.endsWith(INTEGER_END)) {
            return str.substring(0, str.length() - 2);
        } else {
            return str;
        }
    }
}