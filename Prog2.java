class Employee {
    String name;
    double salary;

    public Employee(String name) {
        this.name = name;
    }

    public void calculateSalary() {
        System.out.println("Calculating salary for Employee: " + name);
    }
}

class Manager extends Employee {
    public Manager(String name) {
        super(name);
    }

    @Override
    public void calculateSalary() {
        salary = 50000; // Base salary for manager
        System.out.println("Salary for Manager " + name + ": $" + salary);
    }
}

class Clerk extends Employee {
    public Clerk(String name) {
        super(name);
    }

    @Override
    public void calculateSalary() {
        salary = 30000; // Base salary for clerk
        System.out.println("Salary for Clerk " + name + ": $" + salary);
    }
}

public class Prog2 {
    public static void main(String[] args) {
        Manager manager = new Manager("John");
        Clerk clerk = new Clerk("Alice");

        manager.calculateSalary(); // Calls calculateSalary() method of Manager class
        clerk.calculateSalary();   // Calls calculateSalary() method of Clerk class
    }
}
