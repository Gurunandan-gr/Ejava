import java.io.*;
import java.net.URL;
import java.net.URLConnection;

class DisplayURL {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://172.16.2.10/index.html/");
            URLConnection conn = url.openConnection();

            System.out.println("Host name: " + url.getHost());
            System.out.println("Port: " + url.getPort());
            System.out.println("Protocol: " + url.getProtocol());

           // Read the content from the URL
            BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            System.out.println("Content:");
            while ((line = r.readLine()) != null) {
                System.out.println(line);
            }
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

