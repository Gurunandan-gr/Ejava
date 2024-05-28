import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Prog25 extends JFrame 
{
    private JTextField inputField;
    private JTextArea resultArea;

    public Prog25() 
    {
        setTitle("Factorial Calculator");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        inputField = new JTextField(10);
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() 
        {   public void actionPerformed(ActionEvent e) 
            {	String input = inputField.getText();
                try {
                    int number = Integer.parseInt(input);
                    long factorial = 1;
                    for (int i = 1; i <= number; i++)
                        factorial *= i;
                    resultArea.setText("Factorial of " + number + " is: " + factorial);
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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Prog25().setVisible(true);
        });
    }
}

