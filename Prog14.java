import java.util.*;
import java.io.*;
import java.sql.*;

class EMS {
    Connection con;

    void addEmployee(int empno, String ename, int basic) throws SQLException {
        double da = (8.0 / 100) * basic; 
        double hra = (10.0 / 100) * basic; 
        double netSalary = basic + da + hra;

        PreparedStatement p = con.prepareStatement("INSERT INTO EMS (empno, ename, basic, da, hra, netsalary) VALUES (?, ?, ?, ?, ?, ?)");
        p.setInt(1, empno);
        p.setString(2, ename);
        p.setInt(3, basic);
        p.setDouble(4, da);
        p.setDouble(5, hra);
        p.setDouble(6, netSalary);
        p.executeUpdate();
        System.out.println("Record added successfully");
    }

    void generateEmployeeReport() {
        try {
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM EMS");

            System.out.println("Employee Report:");
            System.out.println("EmpNo\tName\tBasic\tDA\tHRA\tNetSalary");
            while(r.next()) {
                int empno = r.getInt("empno");
                String ename = r.getString("ename");
                int basic = r.getInt("basic");
                double da = r.getDouble("da");
                double hra = r.getDouble("hra");
                double netSalary = r.getDouble("netsalary");
                System.out.println(empno + "\t" + ename + "\t" + basic + "\t" + da + "\t" + hra + "\t" + netSalary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            EMS E = new EMS();
            int empno, basic;
            String ename;
            Scanner sc = new Scanner(System.in);
            E.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MCA", "root", "password");
            while (true) {
                System.out.println("\nEmployee Operation\n1.Add Employee\n2.Display Employee\n3.Exit\nEnter your choice:");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Enter Employee Number:");
                        empno = sc.nextInt();
                        sc.nextLine(); // consume newline
                        System.out.println("Enter Employee Name:");
                        ename = sc.nextLine();
                        System.out.println("Enter Employee Basic Salary:");
                        basic = sc.nextInt();
                        E.addEmployee(empno, ename, basic);
                        break;
                    case 2:
                        E.generateEmployeeReport();
                        break;
                    case 3:
                        E.con.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
