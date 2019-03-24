package Lesson7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingMain {
    public static void main(String[] args) {
        new MyWindow(null);
    }
}

class MyWindow extends JFrame {
    public MyWindow parent;
    public JLabel fioLabel;
    public MyWindow(MyWindow parent) {
        this.parent = parent;
        setTitle(parent==null?"Test window":"Child");
        setResizable(false);
        setDefaultCloseOperation(parent==null?WindowConstants.EXIT_ON_CLOSE:WindowConstants.DISPOSE_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int sizeWidth = 400;
        int sizeHeigth = 300;

        int locationX = (screenSize.width - sizeWidth*(parent==null?2:1)) / 2;
        int locationY = (screenSize.height - sizeHeigth*(parent==null?2:1)) / 2;

        setBounds(locationX,locationY,sizeWidth,sizeHeigth);

        setLayout(null);

        fioLabel = new JLabel("FIO: ");
        fioLabel.setBounds(20,20,200,30);

        JTextArea areaFIO = new JTextArea("", 1, 1);;
        if (parent!=null) {
            add(fioLabel);
            areaFIO.setBounds(60,27,150,18);
            add(areaFIO);
        }

        MyWindow self = this;

        JButton jbt1 = new JButton("Ok");
        jbt1.setBounds(100,220,80,30);
        jbt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(parent == null) {
                    new MyWindow(self);
                } else {
                    parent.remove(parent.fioLabel);
                    parent.fioLabel.setText("FIO: "+areaFIO.getText());
                    parent.add(parent.fioLabel);
                    parent.repaint();
                    parent.revalidate();
                    self.dispose();
                }
                System.out.println("Ok");
            }
        });
        add(jbt1);

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
