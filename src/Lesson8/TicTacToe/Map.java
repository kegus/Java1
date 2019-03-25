package Lesson8.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Lesson4.Cross;

public class Map extends JPanel {

    public static final int MODE_H_V_A = 0;
    public static final int MODE_H_V_H = 1;

    int fieldSizeX;
    int fieldSizeY;
    int mode;

    int[][] field;

    int winLen;

    int cellHeigth;
    int cellWidth;

    boolean finished;

    boolean player1 = false;

    Cross cross;

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
    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLen) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLen = winLen;
        this.mode = mode;
        field = new int[fieldSizeY][fieldSizeX];
        cross = new Cross(fieldSizeX,fieldSizeY,winLen);

        finished = false;
        isInitialized = true;
        repaint();
    }
    void checkState(int x, int y){
        if (cross.isDraw()) {
            finished = true;
            System.out.println("Ничья");
        }
        if (mode == MODE_H_V_A) {
            if (x > -1 && y > -1) {
                if (cross.checkWin(y, x, cross.PLAYER_DOT, winLen)) {
                    finished = true;
                    System.out.println("Игрок победил");
                }
                if (cross.checkWin(y, x, cross.AI_DOT, winLen)) {
                    finished = true;
                    System.out.println("AI победил");
                }
            }
        } else {
            if (x > -1 && y > -1) {
                if (cross.checkWin(y, x, cross.PLAYER_DOT, winLen)) {
                    finished = true;
                    System.out.println("Игрок1 победил");
                }
                if (cross.checkWin(y, x, cross.AI_DOT, winLen)) {
                    finished = true;
                    System.out.println("Игрок2 победил");
                }
            }
        }
    }
    void update(MouseEvent e) {
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeigth;

        System.out.println("x: " + cellX + " y: " + cellY);
        if (mode == MODE_H_V_A) {
            if (cross.isCellValid(cellY, cellX)) {
                field[cellY][cellX] = 1;
                cross.playerStep(cellX, cellY);
                checkState(cellX, cellY);
                if (finished) {
                    startNewGame(mode, fieldSizeX, fieldSizeY, winLen);
                    return;
                }
                cross.aiStep();
                cellX = cross.last_x;
                cellY = cross.last_y;
                field[cellY][cellX] = 2;
                checkState(cellX, cellY);
                if (finished) {
                    startNewGame(mode, fieldSizeX, fieldSizeY, winLen);
                    return;
                }
            } else System.out.println("Неверная ячейка");
        } else {
            if (cross.isCellValid(cellY, cellX)) {
                player1 = !player1;
                if (player1){
                    field[cellY][cellX] = 1;
                    cross.playerStep(cellX, cellY);
                    checkState(cellX, cellY);
                    if (finished) {
                        startNewGame(mode, fieldSizeX, fieldSizeY, winLen);
                        return;
                    }
                } else {
                    field[cellY][cellX] = 2;
                    cross.playerStep2(cellX, cellY);
                    checkState(cellX, cellY);
                    if (finished) {
                        startNewGame(mode, fieldSizeX, fieldSizeY, winLen);
                        return;
                    }
                }
            } else System.out.println("Неверная ячейка");
        }

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
        cellHeigth = panelHeigth / fieldSizeY;
        cellWidth = panelWidth / fieldSizeX;
        for (int i = 0; i < fieldSizeY; i++) g.drawLine(0,i * cellHeigth,panelWidth,i * cellHeigth);
        for (int i = 0; i < fieldSizeX; i++) g.drawLine(i * cellWidth,0,i * cellWidth,panelHeigth);
        for (int i = 0; i < fieldSizeY; i++) for (int j = 0; j < fieldSizeX; j++) drawSym(g, i, j);
    }
    void drawEmpty(Graphics2D g, int i, int j) {    }
    void drawO(Graphics2D g, int i, int j) {
        int x = j * cellWidth + cellWidth / 20;
        int y = i * cellHeigth + cellHeigth / 20;
        int w = cellWidth * 9 / 10;
        int h = cellHeigth * 9 / 10;
        g.fillOval(x,y,w,h);
    }
    void drawX(Graphics2D g, int i, int j) {
        int x1 = j * cellWidth + cellWidth / 10;
        int x2 = j * cellWidth + cellWidth * 9 / 10;
        int y1 = i * cellHeigth + cellHeigth / 10;
        int y2 = i * cellHeigth + cellHeigth * 9 / 10;
        g.drawLine(x1,y1,x2,y2);
        g.drawLine(x2,y1,x1,y2);
    }
    void drawSym(Graphics gr, int i, int j) {
        Graphics2D g = (Graphics2D)gr;
        //создаем "кисть" для рисования
        BasicStroke pen = new BasicStroke(20-fieldSizeY*2); //толщина линии 20
        g.setStroke(pen);
        switch (field[i][j]) {
            case 1:
                drawX(g, i, j);
                break;
            case 2:
                drawO(g, i, j);
                break;
            default:
                drawEmpty(g, i, j);
                break;
        }
    }
}
