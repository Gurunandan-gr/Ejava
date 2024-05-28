import java.util.*;
import java.sql.*;

class Student {
    Connection con;

    public Student(Connection con) {
        this.con = con;
    }

    public void insert() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student Details (Rno, Name, Marks1, Marks2, Marks3):");
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

        PreparedStatement p = con.prepareStatement("INSERT INTO student (rno, sname, marks1, marks2, marks3, total, average, grade) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
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
            System.out.println(rs.getInt("rno") + "\t" + rs.getString("sname") + "\t\t" + 
                               rs.getInt("marks1") + "\t" + rs.getInt("marks2") + "\t" + 
                               rs.getInt("marks3") + "\t" + rs.getInt("total") + "\t" + 
                               rs.getDouble("average") + "\t" + rs.getString("grade"));
        }
    }

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MCA", "root", "password");
            Student student = new Student(con);
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\nStudent Operation\n1. Insert\n2. Display All Records\n3. Exit\nEnter your choice:");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        student.insert();
                        break;
                    case 2:
                        student.displayAll();
                        break;
                    case 3:
                        con.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
