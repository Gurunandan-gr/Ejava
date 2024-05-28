/* // Student.java
import java.io.Serializable;
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int[] marks;
    private int total;
    private double percentage;

    public Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
        calculateTotalAndPercentage();
    }

    public String getName() {
        return name;
    }

    public int[] getMarks() {
        return marks;
    }

    public int getTotal() {
        return total;
    }

    public double getPercentage() {
        return percentage;
    }

    private void calculateTotalAndPercentage() {
        int total = 0;
        for (int mark : marks) 
            total += mark;
        this.total = total;
        this.percentage = (total / (double) (marks.length * 100)) * 100;
    }

    public void setName(String name) {
        this.name = name;
    }
}


// Student_Server.java
import java.io.*;
import java.net.*;
public class Student_Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("Server waiting for client...");

            while (true) {
                Socket s = ss.accept();
                System.out.println("Client connected.");

                ObjectOutputStream objout = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream objin = new ObjectInputStream(s.getInputStream());

                Student student = (Student) objin.readObject();

                objout.writeObject(student);
                objout.flush();

                objout.close();
                objin.close();
                s.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


// Student_Client.java
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Student_Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 1234);
            System.out.println("Connected to server.");

            ObjectOutputStream objout = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream objin = new ObjectInputStream(s.getInputStream());

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter student name: ");
            String name = sc.nextLine();
            int[] marks = new int[3]; 
            System.out.println("Enter Subject Marks: ");
            for (int i = 0; i < 3; i++) {
                System.out.print("Subject " + (i + 1) + ": ");
                marks[i] = sc.nextInt();
            }

            Student student = new Student(name, marks);

            objout.writeObject(student);
            objout.flush();

            // Receive updated student details from server
            Student updatedStudent = (Student) objin.readObject();
            System.out.println("\nStudent Details:");
            System.out.println("Name: " + updatedStudent.getName());
            System.out.println("Total Marks: " + updatedStudent.getTotal());
            System.out.println("Percentage: " + updatedStudent.getPercentage());

            objout.close();
            objin.close();
            s.close();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

 */