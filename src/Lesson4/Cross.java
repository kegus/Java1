package Lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Cross {
    static int SIZE_X = 3;
    static int SIZE_Y = 3;
    static int COUNT_DOT_FOR_WIN = 3;

    static char[][] field = new char[SIZE_Y][SIZE_X];

    private static char PLAYER_DOT = 'X';
    private static char AI_DOT = 'O';
    private static char EMPTY_DOT = '.';

    static Scanner sc = new Scanner(System.in);
    static Random rnd = new Random();
    // заполнить поле
    private static void initField() {
        for (int i = 0; i < SIZE_Y; i++) for (int j = 0; j < SIZE_X; j++) field[i][j] = EMPTY_DOT;
    }
    // печать верхней и нижней границ
    private static void printBorder(int count){
        System.out.print(" -");
        for (int i = 0; i < count; i++) System.out.print("-");
        System.out.println();
    }
    // метод для вывода на консоль поля
    private static void printField() {
        System.out.print("  ");
        for (int i = 0; i < SIZE_X; i++) System.out.print(""+(i+1)+" ");
        System.out.println();
        printBorder(SIZE_X*2);
        for (int i = 0; i < SIZE_Y; i++) {
            System.out.print(""+(i+1)+"|");
            for (int j = 0; j < SIZE_X; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
        printBorder(SIZE_X*2);
    }
    // метод для установки символа
    private static void setSym(int y, int x, char sym) {
        field[y][x] = sym;
    }
    // проверка валидности ячейки
    private static boolean isCellValid(int y, int x) {
        return field[y][x] == EMPTY_DOT;
    }
    // проверка валидности введённого хода
    private static boolean isStepValid(String step){
        return !(step.length()!=3||getStepX(step)==0||getStepY(step)==0);
    }
    private static int getStepX(String step){
        int res = 0;
        try {
            res = Integer.parseInt(step.substring(0,1));
            if(res<1||res>SIZE_X) res = 0;
        }catch (Exception e){
            System.out.println("Не верная координата X");
        }
        return res;
    }
    private static int getStepY(String step){
        int res = 0;
        try {
            res = Integer.parseInt(step.substring(2));
            if(res<1||res>SIZE_Y) res = 0;
        }catch (Exception e){
            System.out.println("Не верная координата Y");
        }
        return res;
    }
    // ход человека
    private static void playerStep() {
        int x;
        int y;
        String step;
        do {
            System.out.println("Введите координаты: X (1 - "+SIZE_X+") Y (1 - "+SIZE_Y+")");
            step = sc.nextLine();
        } while (!isStepValid(step));
        x = getStepX(step)-1;
        y = getStepY(step)-1;
        setSym(y, x, PLAYER_DOT);
    }
    // ход ПК
    private static void aiStep() {
        int x;
        int y;
        do {
            x = rnd.nextInt(SIZE_X);
            y = rnd.nextInt(SIZE_Y);
        } while (!isCellValid(y,x));
        setSym(y, x, AI_DOT);
    }
    // если не встретили пустую ячейку это значит что всё поле заполнено
    private static boolean isDraw() {
        for (int i = 0; i < SIZE_Y; i++) for (int j = 0; j < SIZE_X; j++) if(field[i][j] == EMPTY_DOT) return false;
        return true;
    }
    private static boolean check_horizontal(int i, int j, char sym){
        return false;
    }
    private static boolean check_vertical(int i, int j, char sym){
        return false;
    }
    private static boolean check_diagonal(char sym){
        int count_dot = 0;
        for (int i = 0; i < SIZE_Y-COUNT_DOT_FOR_WIN+1; i++) {
            for (int j = 0; j < SIZE_X-COUNT_DOT_FOR_WIN+1; j++) {

            }
        }
        return false;
    }
    private static boolean checkWin(char sym) {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X-COUNT_DOT_FOR_WIN+1; j++) {
                if (check_horizontal(i,j,sym)) return true;
            }
        }
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y-COUNT_DOT_FOR_WIN+1; j++) {
                if (check_vertical(i,j,sym)) return true;
            }
        }

        if (field[0][0] == sym && field[0][1] == sym && field[0][2] == sym) {
            return true;
        }
        if (field[1][0] == sym && field[1][1] == sym && field[1][2] == sym) {
            return true;
        }
        if (field[2][0] == sym && field[2][1] == sym && field[2][2] == sym) {
            return true;
        }

        if (field[0][0] == sym && field[1][0] == sym && field[2][0] == sym) {
            return true;
        }
        if (field[0][1] == sym && field[1][1] == sym && field[2][1] == sym) {
            return true;
        }
        if (field[0][2] == sym && field[1][2] == sym && field[2][2] == sym) {
            return true;
        }

        if (field[0][0] == sym && field[1][1] == sym && field[2][2] == sym) {
            return true;
        }
        if (field[2][0] == sym && field[1][1] == sym && field[0][2] == sym) {
            return true;
        }

        return false;
    }
    public static void cross() {
        initField();
        printField();

        while (true) {
            playerStep();
            printField();
            if(checkWin(PLAYER_DOT)) {
                System.out.println("Player WIN!");
                break;
            }
            if(isDraw()) {
                System.out.println("DRAW");
                break;
            }

            aiStep();
            printField();
            if(checkWin(AI_DOT)) {
                System.out.println("SKYNET WIN!");
                break;
            }
            if(isDraw()) {
                System.out.println("DRAW");
                break;
            }
        }
    }
    public static void main(String[] args) {
        cross();
        sc.close();
    }
}
