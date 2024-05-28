// Interface Bank
interface Bank {
    void readCustomerInfo();
}

// Interface ICICI extending Bank
interface ICICI extends Bank {
    void calculateInterest();
}

// Interface Axis extending Bank
interface Axis extends Bank {
    void displayDetails();
}

// Class BankImplementation implementing ICICI and Axis interfaces
class BankImplementation implements ICICI, Axis {
    private int accno;
    private double balance;

    // Constructor
    public BankImplementation(int accno, double balance) {
        this.accno = accno;
        this.balance = balance;
    }

    // Implementing method from Bank interface
    public void readCustomerInfo() {
        System.out.println("Reading customer information...");
    }

    // Implementing method from ICICI interface
    public void calculateInterest() {
        System.out.println("Calculating interest for ICICI customers...");
    }

    // Implementing method from Axis interface
    public void displayDetails() {
        System.out.println("Displaying details for Axis customers...");
    }
}

// Main class to test the implementation
public class Prog15 {
    public static void main(String[] args) {
        BankImplementation bankImpl = new BankImplementation(123456789, 50000.0);

        // Calling methods
        bankImpl.readCustomerInfo();
        bankImpl.calculateInterest();
        bankImpl.displayDetails();
    }
}
