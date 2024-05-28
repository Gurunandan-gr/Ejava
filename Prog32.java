import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Prog32 extends JFrame {
    private JTextField inputField1;
    private JTextField inputField2;
    private JTextArea resultArea;
    private Connection con;

    public Prog32() {
        setTitle("String Concatenator");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        inputField1 = new JTextField(10);
        inputField2 = new JTextField(10);
        JButton concatenateButton = new JButton("Concatenate");
        concatenateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str1 = inputField1.getText();
                String str2 = inputField2.getText();
                String concatenatedString = str1 + " " + str2;
                resultArea.setText("Concatenated String: " + concatenatedString);
                
                // Copy the strings and concatenated string to the database table
                copyToDatabase(str1, str2, concatenatedString);
            }
        });

        resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);

        add(new JLabel("Enter String 1: "));
        add(inputField1);
        add(new JLabel("Enter String 2: "));
        add(inputField2);
        add(concatenateButton);
        add(new JScrollPane(resultArea));

        // Connect to the MySQL database
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "username", "password");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Method to insert the strings and concatenated string into the MySQL table
    private void copyToDatabase(String str1, String str2, String concatenatedString) {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO string_table (string1, string2, concatenated_string) VALUES (?, ?, ?)");
            stmt.setString(1, str1);
            stmt.setString(2, str2);
            stmt.setString(3, concatenatedString);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Prog32().setVisible(true);
        });
    }
}
