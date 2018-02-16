package ru.klimakov.hello;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * -XX:+PrintGCDetails -XX:+PrintGCDateStamps
 */

public class BlockingIOHttpServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);
        HtmlGenerator htmlGenerator = new HtmlGenerator();


        while (true) {
            Socket clientSocket = serverSocket.accept();
            OutputStream out = clientSocket.getOutputStream();
            byte[] page = htmlGenerator.generatePasswordPageBytes();
//            String page ="Hello world";
            out.write("HTTP/1.0 200 OK\r\n".getBytes());
            out.write("Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n".getBytes());
            out.write("Server: Apache/0.8.4\r\n".getBytes());
            out.write("Content-Type: text/html\r\n".getBytes());
            out.write(("Content-Length: "+ page.length + "\r\n").getBytes());
            out.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n".getBytes());
            out.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n".getBytes());
            out.write("\r\n".getBytes());
            out.write(page);
            out.close();
            clientSocket.close();
        }
    }
}
