package Lesson7.GUIdesigner;

import javax.swing.*;
import java.awt.*;

public class TestForm extends Container {
    private JPanel rootPanel;
    private JPanel first_window;
    private JPanel second_window;
    private JPanel control_panel;
    private JButton нажмиButton;
    private JButton button2;
    private JLabel fio1;
    private JLabel fio2;
    private JTextField textField1;

    public TestForm() {
        remove(fio2);
        remove(textField1);
        remove(button2);
        repaint();
        revalidate();
    }
    public static void showForm() {
        JFrame jf = new JFrame("TestForm1");
        jf.setContentPane(new TestForm().rootPanel);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
    }
    public static void main(String[] args) {
        //new TestForm();
        showForm();
    }

}
