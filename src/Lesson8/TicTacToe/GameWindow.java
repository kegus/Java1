package Lesson8.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private static final int WIN_HEIGHT = 555;
    private static final int WIN_WIDTH = 508;
    private static final int WIN_POS_X = 300;
    private static final int WIN_POS_Y = 100;

    private static Map field;
    private static StartNewGameWindow startNewGameWindow;

    public GameWindow() {
        setTitle("TicTacToe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(WIN_POS_X, WIN_POS_Y, WIN_WIDTH, WIN_HEIGHT);

        startNewGameWindow = new StartNewGameWindow(this);
        JPanel bottomPanel = new JPanel(new GridLayout(1,2));

        field = new Map();
        add(field, BorderLayout.CENTER);

        JButton btnStartGame = new JButton("Start new game");
        JButton btnExit = new JButton("Exit");

        bottomPanel.add(btnStartGame);
        bottomPanel.add(btnExit);

        add(bottomPanel, BorderLayout.SOUTH);

        btnStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGameWindow.setVisible(true);
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

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLen) {
        field.startNewGame(mode, fieldSizeX, fieldSizeY, winLen);
    }
}
