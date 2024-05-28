class SalaryDetails {
    double basicSalary;

    SalaryDetails(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    double calculateDA() {
        return basicSalary * 0.1; // DA is 10% of basic salary
    }

    double calculateHRA() {
        return basicSalary * 0.15; // HRA is 15% of basic salary
    }

    double calculatePF() {
        return basicSalary * 0.12; // PF is 12% of basic salary
    }

    double calculateIT() {
        return basicSalary * 0.1; // IT is 10% of basic salary
    }

    double calculateNetSalary() {
        double da = calculateDA();
        double hra = calculateHRA();
        double pf = calculatePF();
        double it = calculateIT();
        return basicSalary + da + hra - pf - it; // Net Salary = Basic + DA + HRA - PF - IT
    }
}

class Employee {
    String name;
    SalaryDetails salaryDetails;

    Employee(String name, double basicSalary) {
        this.name = name;
        this.salaryDetails = new SalaryDetails(basicSalary);
    }

    void displaySalaryDetails() {
        System.out.println("Employee Name: " + name);
        System.out.println("Basic Salary: " + salaryDetails.basicSalary);
        System.out.println("DA: " + salaryDetails.calculateDA());
        System.out.println("HRA: " + salaryDetails.calculateHRA());
        System.out.println("PF: " + salaryDetails.calculatePF());
        System.out.println("IT: " + salaryDetails.calculateIT());
        System.out.println("Net Salary: " + salaryDetails.calculateNetSalary());
    }
}

public class Prog13 {
    public static void main(String[] args) {
        Employee employee = new Employee("John Doe", 50000);
        employee.displaySalaryDetails();
    }
}
