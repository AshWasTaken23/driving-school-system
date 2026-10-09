package backend.src;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Server {

    public static void main(String[] args) throws IOException {
        // 1. Initialize the HTTP Server on port 8080
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("🚀 Driving School Server started on port " + port);

        // 2. Define Context Routes (Endpoints)
        server.createContext("/api/status", new StatusHandler());
        server.createContext("/api/register", new RegistrationHandler());

        // 3. Start the server background threads
        server.setExecutor(null); 
        server.start();
    }

    // --- HANDLER 1: Basic Health Check ---
    static class StatusHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Setup response configuration
            String response = "{\"status\": \"UP\", \"message\": \"Java backend is running cleanly\"}";
            
            // Add custom header constraints to handle CORS browser issues
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    // --- HANDLER 2: Placeholder registration endpoint ---
    static class RegistrationHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            
            String response = "{\"message\": \"Registration endpoint placeholder hit ready for Stage 1 logic\"}";
            
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
// Java HttpServer Entry Point 
