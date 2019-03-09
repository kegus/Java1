package Lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Cross {
    static int SIZE_X = 7;
    static int SIZE_Y = 5;
    static int COUNT_DOT_FOR_WIN = 4;

    static char[][] field = new char[SIZE_Y][SIZE_X];

    private static char PLAYER_DOT = 'X';
    private static char AI_DOT = 'O';
    private static char EMPTY_DOT = '.';

    static Scanner sc = new Scanner(System.in);
    static Random rnd = new Random();
    static int last_x, last_y;

    // заполнить поле
    private static void initField() {
        for (int i = 0; i < SIZE_Y; i++) for (int j = 0; j < SIZE_X; j++) field[i][j] = EMPTY_DOT;
    }
    // печать верхней и нижней границ
    private static void printBorder(int count){
        System.out.print("  -");
        for (int i = 0; i < count; i++) System.out.print("-");
        System.out.println();
    }
    // метод для вывода на консоль поля
    private static void printField() {
        System.out.print("   ");
        for (int i = 0; i < SIZE_X; i++) System.out.print(""+(i+1)+" ");
        System.out.println();
        printBorder(SIZE_X*2);
        for (int i = 0; i < SIZE_Y; i++) {
            System.out.print(""+(i+1)+" |");
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
    private static boolean playerStep() {
        int x;
        int y;
        String step;
        do {
            System.out.println("Введите координаты: X (1 - "+SIZE_X+") Y (1 - "+SIZE_Y+")");
            step = sc.nextLine();
            if(step.equals("exit")) return false;
        } while (!isStepValid(step));
        x = getStepX(step)-1;
        y = getStepY(step)-1;
        setSym(y, x, PLAYER_DOT);
        last_x = x;
        last_y = y;
        return true;
    }
    // ход ПК
    private static void aiStep() {
        int x = -1;
        int y = -1;
        for (int k = COUNT_DOT_FOR_WIN; k > COUNT_DOT_FOR_WIN-2; k--) {
            // проверяем возможность победы AI или Игрока
            if(k == COUNT_DOT_FOR_WIN) {
                if (x < 0 || y < 0)
                    chek1:for (int j = 0; j < SIZE_X; j++) {
                        for (int i = 0; i < SIZE_Y; i++) {
                            if (isCellValid(i, j)) {
                                field[i][j] = AI_DOT;
                                if (checkWin(i, j, AI_DOT, k)) {
                                    x = j;
                                    y = i;
                                    field[i][j] = EMPTY_DOT;
                                    break chek1;
                                }
                                field[i][j] = EMPTY_DOT;
                            }
                        }
                    }
                if (x < 0 || y < 0)
                    chek2:for (int j = 0; j < SIZE_X; j++) {
                        for (int i = 0; i < SIZE_Y; i++) {
                            if (isCellValid(i, j)) {
                                field[i][j] = PLAYER_DOT;
                                if (checkWin(i, j, PLAYER_DOT, k)) {
                                    x = j;
                                    y = i;
                                    field[i][j] = EMPTY_DOT;
                                    break chek2;
                                }
                                field[i][j] = EMPTY_DOT;
                            }
                        }
                    }
            } else {
                // проверяем возможность вилки Игрока
                if (x < 0 || y < 0)
                    chek1:for (int j = 0; j < SIZE_X; j++) {
                        for (int i = 0; i < SIZE_Y; i++) {
                            if (isCellValid(i, j)) {
                                field[i][j] = AI_DOT;
                                if (checkWin(i, j, AI_DOT, k)) {
                                    x = j;
                                    y = i;
                                    field[i][j] = EMPTY_DOT;
                                    break chek1;
                                }
                                field[i][j] = EMPTY_DOT;
                            }
                        }
                    }
                // проверяем возможность вилки AI
                if (x < 0 || y < 0)
                    chek2:for (int j = 0; j < SIZE_X; j++) {
                        for (int i = 0; i < SIZE_Y; i++) {
                            if (isCellValid(i, j)) {
                                field[i][j] = PLAYER_DOT;
                                if (checkWin(i, j, PLAYER_DOT, k)) {
                                    x = j;
                                    y = i;
                                    field[i][j] = EMPTY_DOT;
                                    break chek2;
                                }
                                field[i][j] = EMPTY_DOT;
                            }
                        }
                    }
            }
        }
        if(x<0||y<0)
        do {
            x = rnd.nextInt(SIZE_X);
            y = rnd.nextInt(SIZE_Y);
        } while (!isCellValid(y,x));
        setSym(y, x, AI_DOT);
        last_x = x;
        last_y = y;
    }
    // если не встретили пустую ячейку это значит что всё поле заполнено
    private static boolean isDraw() {
        for (int i = 0; i < SIZE_Y; i++) for (int j = 0; j < SIZE_X; j++) if(field[i][j] == EMPTY_DOT) return false;
        return true;
    }
    private static boolean checkHorizontal(int y, int x, char sym, int dot_count){
        int find_count;
        for (int i = x-dot_count+1; i < x+dot_count-1; i++) {
            find_count = 0;
            for (int j = 0; j < dot_count; j++) {
                if(i+j>-1 && i+j < field[0].length){
                    if(field[y][i+j] == sym) find_count++;
                    else break;
                    if(find_count==dot_count) return true;
                } else break;
            }
        }
        return false;
    }
    private static boolean checkVertical(int y, int x, char sym, int dot_count){
        int find_count;
        for (int j = y-dot_count+1; j < y+dot_count-1; j++) {
            find_count = 0;
            for (int i = 0; i < dot_count; i++) {
                if (i + j > -1 && i + j < field.length) {
                    if (field[i + j][x] == sym) find_count++;
                    else break;
                    if (find_count == dot_count) return true;
                } else break;
            }
        }
        return false;
    }
    private static boolean checkDiagonal(int y, int x, char sym, int dot_count){
        int find_count;
        for (int i = y-dot_count+1, j = x-dot_count+1; i < y+dot_count-1 && j < x+dot_count-1; i++,j++) {
            find_count = 0;
            for (int k = 0; k < dot_count; k++) {
                //int xx = i+j;
                if (i+k > -1 && i+k < field.length && j+k > -1 && j+k < field[0].length) {
                    int xx = i+k;
                    int yy = j+k;
                    if (field[xx][yy] == sym) find_count++;
                    else break;
                    if (find_count == dot_count) return true;
                } else break;
            }
        }
        for (int i = y-dot_count+1, j = x+dot_count-1; i < y+dot_count-1 && j > x-dot_count+1; i++,j--) {
            find_count = 0;
            for (int k = 0; k < dot_count; k++) {
                //int xx = i+j;
                if (i+k > -1 && i+k < field.length && j-k > -1 && j-k < field[0].length) {
                    int xx = i+k;
                    int yy = j-k;
                    if (field[xx][yy] == sym) find_count++;
                    else break;
                    if (find_count == dot_count) return true;
                } else break;
            }
        }
        return false;
    }
    private static boolean checkWin(int y, int x, char sym, int dot_count) {
        return checkHorizontal(y,x,sym,dot_count) || checkVertical(y,x,sym,dot_count) || checkDiagonal(y,x,sym,dot_count);
    }
    public static void cross() {
        exit: while (true){
            System.out.println();
            System.out.println("Сыграем в крестики-нолики до "+COUNT_DOT_FOR_WIN);
            initField();
            printField();

            while (true) {
                if(!playerStep()) {
                    System.out.println("До свидания");
                    break exit;
                }
                printField();
                if(checkWin(last_y,last_x,PLAYER_DOT,COUNT_DOT_FOR_WIN)) {
                    System.out.println("Player WIN!");
                    break;
                }
                if(isDraw()) {
                    System.out.println("DRAW");
                    break;
                }

                aiStep();
                printField();
                if(checkWin(last_y,last_x,AI_DOT,COUNT_DOT_FOR_WIN)) {
                    System.out.println("SKYNET WIN!");
                    break;
                }
                if(isDraw()) {
                    System.out.println("DRAW");
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {
        cross();
        sc.close();
    }
}
