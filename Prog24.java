import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Prog24 extends JFrame {
    JTextArea textArea;

    public Prog24() {
        setTitle("Key Events Example");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize text area
        textArea = new JTextArea(10, 20);
        textArea.setEditable(false);
        textArea.setFocusable(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Register key listener using Adapter class
        textArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                char keyChar = e.getKeyChar();
                appendToTextArea("Key Down: " + keyChar);
            }

            public void keyReleased(KeyEvent e) {
                char keyChar = e.getKeyChar();
                appendToTextArea("Key Up: " + keyChar);
            }
        });

        // Append prompt
        appendToTextArea("Press any key...");

        setVisible(true);
    }

    private void appendToTextArea(String text) {
        textArea.append(text + "\n");
    }

    public static void main(String[] args) {
        new Prog24();
    }
}
