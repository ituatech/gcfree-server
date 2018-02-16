package ru.klimakov.hello;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * -verbose:gc
 * -XX:+PrintGCDetails -XX:+PrintGCDateStamps
 */
public class JdkHttpServer {


    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/generate", new PasswordGeneratorHandler());
        server.setExecutor(null);
        server.start();
    }

    private static class PasswordGeneratorHandler implements HttpHandler {
        private HtmlGenerator htmlGenerator = new HtmlGenerator();

//        @Override
//        public void handle(HttpExchange httpExchange) throws IOException {
//            String response = htmlGenerator.generatePasswordPage();
//            httpExchange.sendResponseHeaders(200, response.length());
//            OutputStream os = httpExchange.getResponseBody();
//            os.write(response.getBytes());
//            os.close();
//        }

        public static final byte[] BYTES = "Hello world".getBytes();

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            httpExchange.sendResponseHeaders(200, BYTES.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(BYTES);
            os.close();
        }

    }
}
