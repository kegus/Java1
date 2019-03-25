package Lesson8.Moba_GRAPH;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Lesson7.Moba.*;

public class MobaWindow extends JFrame {
    private static final int WIN_HEIGHT = 625;
    private static final int WIN_WIDTH = 1008;
    private static final int WIN_POS_X = 100;
    private static final int WIN_POS_Y = 50;

    private static final int MIN_CNT_HEROES = 3;
    private static final int MAX_CNT_HEROES = 9;

    private int cnt1 = 0;
    private int cnt2 = 0;

    private JSlider slContHero1;
    private JSlider slContHero2;

    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea resTextArea;

//    private Hero[] hero1;
//    private Hero[] hero2;
    private Warriors warriors1;
    private Warriors warriors2;

    public MobaWindow() {
        setTitle("Moba");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(WIN_POS_X, WIN_POS_Y, WIN_WIDTH, WIN_HEIGHT);

        JPanel topPanel = new JPanel(new GridLayout(2,3));

        JPanel topLeftPanel = new JPanel(new GridLayout(4,2));
        JPanel topRightPanel = new JPanel(new GridLayout(4,2));
        JPanel topCenterPanel = new JPanel(new GridLayout(1,1));
        JPanel topCenterPanel1 = new JPanel(new GridLayout(1,1));

        String[] items = {"Воин", "Килер", "Лекарь"};

        warriors1 = new Warriors("Команда 1", MIN_CNT_HEROES);
        warriors2 = new Warriors("Команда 2", MIN_CNT_HEROES);

        final JLabel lCntH1 = new JLabel("Count heros1: "+MIN_CNT_HEROES);
        topLeftPanel.add(lCntH1);
        slContHero1 = new JSlider(MIN_CNT_HEROES, MAX_CNT_HEROES, MIN_CNT_HEROES);
        slContHero1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int cntHeros = slContHero1.getValue();
                warriors1 = new Warriors("Команда 1", cntHeros);
                lCntH1.setText("Count heros1: "+cntHeros);
            }
        });
        topLeftPanel.add(slContHero1);
        comboBox1 = new JComboBox(items);
        topLeftPanel.add(comboBox1);
        JButton btnAdd1 = new JButton("Добавить");
        btnAdd1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cnt1 < slContHero1.getValue()){
                    warriors1.addHero("Команда 1 " + cnt1, comboBox1.getSelectedIndex(), cnt1);
                    warriors1.CNT_NUMEROF_WARIORS = ++cnt1;
                    String item = (String)comboBox1.getSelectedItem()+"\n";;
                    textArea1.append(item);
                }
            }
        });
        topLeftPanel.add(btnAdd1);

        topCenterPanel.add(new JLabel(" "));
        topCenterPanel1.add(new JLabel(" "));

        final JLabel lCntH2 = new JLabel("Count heros2: "+MIN_CNT_HEROES);
        topRightPanel.add(lCntH2);
        slContHero2 = new JSlider(MIN_CNT_HEROES, MAX_CNT_HEROES, MIN_CNT_HEROES);
        slContHero2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int cntHeros = slContHero2.getValue();
                warriors2 = new Warriors("Команда 2", cntHeros);
                lCntH2.setText("Count heros2: "+cntHeros);
            }
        });
        topRightPanel.add(slContHero2);
        comboBox2 = new JComboBox(items);
        topRightPanel.add(comboBox2);
        JButton btnAdd2 = new JButton("Добавить");
        btnAdd2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cnt2 < slContHero2.getValue()) {
                    warriors2.addHero("Команда 2 " + cnt2, comboBox2.getSelectedIndex(), cnt2);
                    warriors2.CNT_NUMEROF_WARIORS = ++cnt2;
                    String item = (String) comboBox2.getSelectedItem()+"\n";
                    textArea2.append(item);
                }
            }
        });
        topRightPanel.add(btnAdd2);

        topPanel.add(topLeftPanel);

        topPanel.add(topCenterPanel);

        topPanel.add(topRightPanel);

        textArea1 = new JTextArea(MAX_CNT_HEROES, 1);
        topPanel.add(textArea1);

        topPanel.add(topCenterPanel1);

        textArea2 = new JTextArea(MAX_CNT_HEROES, 1);
        topPanel.add(textArea2);

        add(topPanel, BorderLayout.NORTH);

        resTextArea = new JTextArea(1, 1);
        JScrollPane sp = new JScrollPane(resTextArea);
        add(sp, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1,2));
        JButton btnStartGame = new JButton("Start new game");
        JButton btnExit = new JButton("Exit");
        bottomPanel.add(btnStartGame);
        bottomPanel.add(btnExit);
        add(bottomPanel, BorderLayout.SOUTH);
        btnStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Warriors[] warriors = new Warriors[2];
                warriors[0] = warriors1;
                warriors[1] = warriors2;
                BatleField bf = new BatleField(warriors);
                bf.fightGraph(resTextArea);
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setResizable(false);
        setVisible(true);
    }
}
