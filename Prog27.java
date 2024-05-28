import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Prog27 extends JFrame {
    private JTextField inputField;
    private JTextArea resultArea;

    public Prog27() {
        setTitle("Fibonacci Display");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        inputField = new JTextField(10);
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                try {
                    int count = Integer.parseInt(input);
                    StringBuilder fibonacciSequence = new StringBuilder();
                    int n1 = 0, n2 = 1;
                    fibonacciSequence.append(n1).append(", ");
                    for (int i = 1; i < count; i++) {
                        fibonacciSequence.append(n2).append(", ");
                        int sum = n1 + n2;
                        n1 = n2;
                        n2 = sum;
                    }
                    resultArea.setText("Fibonacci sequence: " + fibonacciSequence);
                } catch (NumberFormatException ex) {
                    resultArea.setText("Please enter a valid integer.");
                }
            }
        });

        resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);

        add(new JLabel("Enter the number of Fibonacci numbers to display: "));
        add(inputField);
        add(calculateButton);
        add(new JScrollPane(resultArea));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Prog27().setVisible(true);
        });
    }
}
