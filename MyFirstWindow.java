import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFirstWindow {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My First GUI");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JButton button = new JButton("Click me");
        button.setBounds(0, 0, 100, 50);
        button.setBackground(Color.PINK);
        button.addActionListener(e -> System.out.println("Button 1 clicked"));

        JButton button2 = new JButton("Click me again");
        button2.setBounds(0, 100, 150, 50);
        button2.setBackground(Color.GREEN);
        button2.addActionListener(e -> System.out.println("Button 2 clicked"));

        frame.add(button);
        frame.add(button2);
        frame.setVisible(true);
    }
}