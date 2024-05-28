import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Prog31 extends JFrame {
    private JTextField inputField;
    private JTextArea resultArea;
    private Connection con;

    public Prog31() {
        setTitle("Factorial Calculator");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        inputField = new JTextField(10);
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                try {
                    int number = Integer.parseInt(input);
                    long factorial = calculateFactorial(number);
                    resultArea.setText("Factorial of " + number + " is: " + factorial);
                    
                    // Add the number and its factorial to the MySQL table
                    addToDatabase(number, factorial);
                } catch (NumberFormatException ex) {
                    resultArea.setText("Please enter a valid integer.");
                }
            }
        });

        resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);

        add(new JLabel("Enter a number: "));
        add(inputField);
        add(calculateButton);
        add(new JScrollPane(resultArea));

        // Connect to the MySQL database
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MCA", "root", "password");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Method to calculate the factorial of a number
    private long calculateFactorial(int number) {
        long factorial = 1;
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }
        return factorial;
    }

    // Method to insert the number and its factorial into the MySQL table
    private void addToDatabase(int number, long factorial) {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO factorial_table (number, factorial) VALUES (?, ?)");
            stmt.setInt(1, number);
            stmt.setLong(2, factorial);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Prog31().setVisible(true);
        });
    }
}
