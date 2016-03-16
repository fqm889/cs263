package orz;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * Created by sicongfeng on 16/3/16.
 */
public class Server implements Runnable {
    public DebugHandler debugHandler;

    public Server() {

    }

    public Server(DebugHandler dh) {
        debugHandler = dh;
    }

    @Override
    public void run() {
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(9000), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.createContext("/next", new MyHandler(debugHandler));
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        public DebugHandler debugHandler;
        public MyHandler(DebugHandler dh) {
            debugHandler = dh;
        }
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
            debugHandler.next = true;
        }
    }
}
