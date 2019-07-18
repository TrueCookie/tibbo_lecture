package com.tibbo;

import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class Server
{
  private static final Server INSTANCE = new Server();
  
  public static void main(String[] args) throws Exception
  {
    INSTANCE.launch(args);
  }
  
  private void launch(String[] args) throws Exception
  {
    ServerSocket serverSocket = new ServerSocket();
    serverSocket.bind(new InetSocketAddress(15555));
    serverSocket.setSoTimeout(5000);
  }
}
