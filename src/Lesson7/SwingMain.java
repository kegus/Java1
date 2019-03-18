package Lesson7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingMain {
    public static void main(String[] args) {
        new MyWindow();
    }
}

class MyWindow extends JFrame {
    public MyWindow() {
        setTitle("Test window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(500,300,400,400);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int sizeWidth = 800;
        int sizeHeigth = 600;

        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeigth) / 2;

        setBounds(locationX,locationY,sizeWidth,sizeHeigth);

        setLayout(null);

        JButton jbt1 = new JButton("Ok");
        jbt1.setBounds(100,50,80,30);

        add(jbt1);

        jbt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ok");
            }
        });


//
//        setResizable(false);
//
//        add(jbt1);

//        JButton jbt1 = new JButton("Ok");
//        JButton jbt2 = new JButton("Cancel");
//
//        JButton[] jbs = new JButton[10];
//
//        setLayout(new FlowLayout(0));
//
//
//        for (int i = 0; i < jbs.length ; i++) {
//            jbs[i] = new JButton("#" + i);
//
//            add(jbs[i]);
//        }

      //  jbt1.setPreferredSize(new Dimension(300,300));

//        JPanel jPanel = new JPanel(new GridLayout(1,2));
//
//        jPanel.add(jbt1);
//        jPanel.add(jbt2);
//
//        add(jPanel, BorderLayout.NORTH);


        setVisible(true);
    }
}
