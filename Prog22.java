import java.io.*;

public class Prog22 {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("source.txt");
            FileWriter fw = new FileWriter("destination.txt");

            int data;
            while ((data = fr.read()) != -1) {
                fw.write(data);
            }

            System.out.println("File copied successfully!");

            fr.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
