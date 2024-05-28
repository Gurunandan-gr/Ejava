// Define a custom exception for handling invalid USN format
class InvalidUSNException extends Exception {
    InvalidUSNException(String message) {
        super(message);
    }
}

// Define a Student class
class Student {
    private String name;
    private String usn;

    // Constructor to initialize student name and USN
    public Student(String name, String usn) throws InvalidUSNException {
        this.name = name;
        if (!isValidUSN(usn)) {
            throw new InvalidUSNException("Invalid USN format! USN should start with '1' followed by 7 digits.");
        }
        this.usn = usn;
    }

    // Method to display student details
    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("USN: " + usn);
    }

    // Helper method to validate USN format
    private boolean isValidUSN(String usn) {
        return usn.matches("1\\d{7}");
    }
}

// Main class to demonstrate the usage of Student class and throw keyword
public class Prog11 {
    public static void main(String[] args) {
        // Example for built-in exception (ArithmeticException)
        try {
            int result = 10 / 0; // Attempting to divide by zero
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException caught: Division by zero.");
        }

        // Example for user-defined exception (InvalidUSNException)
        try {
            // Create a Student object with simple details
            Student student = new Student("John", "1234567"); // Invalid USN format
            student.displayDetails();
        } catch (InvalidUSNException e) {
            // Catch and handle the custom exception
            System.out.println("Error: " + e.getMessage());
        }
    }
}
