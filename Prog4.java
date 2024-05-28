import java.sql.*;  
import java.util.Scanner;

public class Prog4 {  
    public static void main(String[] args) {  
        try {  
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MCA", "root", "password");
            
            Scanner sc = new Scanner(System.in);
            System.out.println("Employee Management System");
            System.out.println("1. Calculate Total Salary");
            System.out.println("2. Update Employee Record");
            System.out.println("3. Delete Employee Record");
            System.out.println("Enter your choice:");
            int choice = sc.nextInt();
            
            switch (choice) {
                case 1: calculateTotalSalary(con); break;
                case 2: updateEmployeeRecord(con); break;
                case 3: deleteEmployeeRecord(con); break;
                default: System.out.println("Invalid choice. Please enter a valid option.");
            }
            
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }  
    }  
    
    public static void calculateTotalSalary(Connection con) throws SQLException {
        try {  
            CallableStatement stmt = con.prepareCall("{call calculate_total_salary(?, ?)}");  
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter ID of Employee whose Total Salary You Want to Calculate: ");
            int eid = sc.nextInt();
            
            stmt.setInt(1, eid);
            stmt.registerOutParameter(2, java.sql.Types.DECIMAL);
            stmt.execute();  	         
            
            double tsal = stmt.getDouble(2);
            if (tsal != 0) 
                System.out.println("Employee with ID " + eid + " has Total Salary " + tsal);
            else 
                System.out.println("No employee found with ID " + eid);
            
        } catch(Exception e) {
            System.out.println("Error occurred while calculating total salary: " + e.getMessage());
        }  
    }
    
    public static void updateEmployeeRecord(Connection con) throws SQLException {
        try {  
            CallableStatement stmt = con.prepareCall("{call update_employee_record(?, ?)}");  
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter ID of Employee whose record you want to update: ");
            int eid = sc.nextInt();
            System.out.print("Enter new salary: ");
            double newSalary = sc.nextDouble();
            
            stmt.setInt(1, eid);
            stmt.setDouble(2, newSalary);
            stmt.execute();
            
            System.out.println("Employee record updated successfully.");
            
        } catch(Exception e) {
            System.out.println("Error occurred while updating employee record: " + e.getMessage());
        }  
    }
    
    public static void deleteEmployeeRecord(Connection con) throws SQLException {
        try {  
            CallableStatement stmt = con.prepareCall("{call delete_employee_record(?)}");  
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter ID of Employee whose record you want to delete: ");
            int eid = sc.nextInt();
            
            stmt.setInt(1, eid);
            stmt.execute();
            
            System.out.println("Employee record deleted successfully.");
            
        } catch(Exception e) {
            System.out.println("Error occurred while deleting employee record: " + e.getMessage());
        }  
    }
}

/*

Calculate Total Salary:
DELIMITER //
CREATE PROCEDURE calculate_total_salary(IN eid INT, OUT total_salary DECIMAL(10,2))
BEGIN
    DECLARE sal DECIMAL(10,2);
    DECLARE da DECIMAL(10,2); 
    DECLARE hra DECIMAL(10,2);
    DECLARE gross_pay DECIMAL(10,2);
    DECLARE pf DECIMAL(10,2);
    DECLARE pt DECIMAL(10,2);
    
    SELECT salary INTO sal FROM Employee WHERE id = eid;
    SET da = sal * 0.1;
    SET hra = sal * 0.15;
    SET gross_pay = sal + da + hra;
    SET pf = sal * 0.12;
    SET pt = sal * 0.2; 
    
    SET total_salary = gross_pay - (pf + pt);
END //
DELIMITER ;


Update Employee Record:
DELIMITER //
CREATE PROCEDURE update_employee_record(IN eid INT, IN new_salary DECIMAL(10,2))
BEGIN
    UPDATE Employee SET salary = new_salary WHERE id = eid;
END //
DELIMITER ;

Delete Employee Record:
DELIMITER //
CREATE PROCEDURE delete_employee_record(IN eid INT)
BEGIN
    DELETE FROM Employee WHERE id = eid;
END //
DELIMITER ;




 */