import java.io.*;
import java.net.*;

public class Prog18 {
    public static void main(String[] args) {
        try {
            // Server socket setup
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started. Waiting for clients to connect...");

            // Client socket setup
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // Start sender and receiver threads
            Thread senderThread = new Thread(new Sender(clientSocket));
            Thread receiverThread = new Thread(new Receiver(clientSocket));
            senderThread.start();
            receiverThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Sender implements Runnable {
    private Socket socket;

    public Sender(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while (true) {
                message = reader.readLine();
                out.println(message);
                if (message.equalsIgnoreCase("bye")) {
                    break;
                }
            }
            out.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Receiver implements Runnable {
    private Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Client: " + message);
                if (message.equalsIgnoreCase("bye")) {
                    break;
                }
            }
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
