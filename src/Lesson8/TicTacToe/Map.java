package Lesson8.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Map extends JPanel {

    public static final int MODE_H_V_A = 0;
    public static final int MODE_H_V_H = 1;

    int fieldSizeX;
    int fieldSizeY;

    int[][] field;

    int winLen;

    int cellheigth;
    int cellWidth;

    boolean isInitialized = false;

    public Map() {

        setBackground(Color.ORANGE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });

    }

    void update(MouseEvent e) {
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellheigth;

        System.out.println("x: " + cellX + " y: " + cellY);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    void render(Graphics g) {

        if (!isInitialized) return;

        int panelWidth = getWidth();
        int panelHeigth = getHeight();

        cellheigth = panelHeigth / fieldSizeY;
        cellWidth = panelWidth / fieldSizeX;

        g.fillOval(20,20,20,20);

        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellheigth;
            g.drawLine(0,y,panelWidth,y);
        }

        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x,0,x,panelHeigth);
        }

    }

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLen) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLen = winLen;
        field = new int[fieldSizeY][fieldSizeX];

        isInitialized = true;
        repaint();
    }
}
