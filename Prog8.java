public class Prog8 {
    public static void main(String[] args) {
        try {
            System.out.println("Opening a file...");
           System.out.println("File opened successfully.");
            int result = 10 / 0; // This will throw an ArithmeticException
            System.out.println("Result: " + result); // This line will not be executed
             System.out.println("This line will not be printed.");
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero.");
        } finally {
            System.out.println("Closing the file...");
            System.out.println("File closed.");
            System.out.println("Finally block executed.");
        }
    }
}
