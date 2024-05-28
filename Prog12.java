/* //SalaryCalculation.java
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface SalaryCalculation extends Remote {
double calcDa(double basic) throws RemoteException;
double calcHra(double basic) throws RemoteException;
double calcNet(double basic) throws RemoteException;
}

//SalaryCalculationImpl.java
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class SalaryCalculationImpl extends UnicastRemoteObject implements SalaryCalculation {
SalaryCalculationImpl() throws RemoteException {
super();
}
public double calcDa(double basic){
return basic * 0.08;
}
public double calcHra(double basic){
return basic * 0.1;
}
public double calcNet(double basic){
return basic + calcDa(basic) + calcHra(basic);
}
}

//EmployeeServer.java
import java.rmi.*;
public class EmployeeServer {
public static void main(String[] args){
try {
SalaryCalculation stub = new SalaryCalculationImpl();
Naming.rebind("rmi://localhost:5001/employee", stub);
} catch (Exception e) {
System.out.println(e);
}
}
}


//EmployeeClient.java
import java.rmi.*;
import java.util.Scanner;
public class EmployeeClient {
public static void main(String[] args){
                try {
Scanner sc = new Scanner(System.in);
SalaryCalculation stub = (SalaryCalculation)
Naming.lookup("rmi://localhost:5001/employee");
System.out.println("Enter EMP no");
String empNo = sc.nextLine();
System.out.println("Enter Emp Name");
String empNmae = sc.nextLine();
System.out.println("Enter Basic salary");
double basic = sc.nextDouble();
double da, hra, net;
da = stub.calcDa(basic);
hra = stub.calcHra(basic);
net = stub.calcNet(basic);
System.out.println("Emp No : " + empNo + "\tEmp Name : " + empNmae +
"\tBasic : " + basic);
System.out.println("Da : " + da + "\tHRA : " + hra + "\tNet Salary : " + net);
     } catch (Exception e) {
System.out.println(e);
}
      }
}
 */