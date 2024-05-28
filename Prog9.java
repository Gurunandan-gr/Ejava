/*
// UDPChatServer.java
import java.io.*;
import java.net.*;

public class UDPChatServer {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9876);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, 
   receiveData.length);
                socket.receive(receivePacket);

                String clientMessage = new String(receivePacket.getData(), 0, 
  receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Client (" + clientAddress.getHostAddress() + ":" + clientPort + 
   "): " + clientMessage);

BufferedReader serverReader = new BufferedReader(new InputStreamReader  
(System.in));
                System.out.print("Server: ");
                String serverMessage = serverReader.readLine();
                byte[] sendData = serverMessage.getBytes();

DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,  
clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


// UDPChatClient.java
import java.io.*;
import java.net.*;

public class UDPChatClient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();

            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("You: ");
                String message = reader.readLine();
                byte[] sendData = message.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,	
   serverAddress, serverPort);
                socket.send(sendPacket);

                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, 
   receiveData.length);
                socket.receive(receivePacket);

                String serverMessage = new String(receivePacket.getData(), 0, 
   receivePacket.getLength()).trim();
                System.out.println("Server: " + serverMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


 */