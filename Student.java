


import java.sql.*;
import java.util.Scanner;

public class Student {
    private Connection con;

    public Student(Connection con) {
        this.con = con;
    }

    public void insert() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student Details (Rno, Name, m1, m2, m3):");
        int rno = sc.nextInt();
        sc.nextLine(); // Consume newline character
        String name = sc.nextLine();
        int marks1 = sc.nextInt();
        int marks2 = sc.nextInt();
        int marks3 = sc.nextInt();

        int total = marks1 + marks2 + marks3;
        double average = total / 3.0;
        String grade;

        if (average >= 90) 
            grade = "A";
        else if (average >= 80) 
            grade = "B";
        else if (average >= 70) 
            grade = "C";
        else if (average >= 60) 
            grade = "D";
        else 
            grade = "F";

        PreparedStatement p = con.prepareStatement("INSERT INTO student (rno, name, marks1, marks2, marks3, total, average, grade) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        p.setInt(1, rno);
        p.setString(2, name);
        p.setInt(3, marks1);
        p.setInt(4, marks2);
        p.setInt(5, marks3);
        p.setInt(6, total);
        p.setDouble(7, average);
        p.setString(8, grade);

        System.out.println("Executing insert statement...");
        p.executeUpdate();
        System.out.println("1 Student added successfully");
    }

    public void displayAll() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM student");

        System.out.println("Rno\tName\t\tMarks1\tMarks2\tMarks3\tTotal\tAverage\tGrade");
        System.out.println("-----------------------------------------------------------------------------------");

        while (rs.next()) {
            System.out.println(rs.getInt("rno") + "\t" + rs.getString("name") + "\t\t" + 
                               rs.getInt("marks1") + "\t" + rs.getInt("marks2") + "\t" + 
                               rs.getInt("marks3") + "\t" + rs.getInt("total") + "\t" + 
                               rs.getDouble("average") + "\t" + rs.getString("grade"));
        }
    }
    //2 javac -cp ".;C:\Users\Lenovo\Desktop\2 SEM MCA\EJava\MSE\mysql-connector-j-8.3.0\mysql-connector-java-8.0.26.jar" Student.java
    //2 java -cp ".;C:\Users\Lenovo\Desktop\2 SEM MCA\EJava\MSE\mysql-connector-j-8.3.0\mysql-connector-java-8.0.26.jar" Student


    //3 javac -classpath ".;C:\Users\Lenovo\Desktop\2 SEM MCA\EJava\MSE\mysql-connector-j-8.3.0\mysql-connector-java-8.0.26.jar" Student.java
    //3 java -classpath ".;C:\Users\Lenovo\Desktop\2 SEM MCA\EJava\MSE\mysql-connector-j-8.3.0\mysql-connector-java-8.0.26.jar" Student


    public static void main(String[] args) {
        try { 
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database...");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MCA", "root", "password");
            System.out.println("Connected to database successfully.");
            
            Student student = new Student(con);
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\nStudent Operation\n1. Insert\n2. Display All Records\n3. Exit\nEnter your choice:");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Inserting a new student...");
                        student.insert();
                        break;
                    case 2:
                        System.out.println("Displaying all student records...");
                        student.displayAll();
                        break;
                    case 3:
                        System.out.println("Closing connection and exiting...");
                        con.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
