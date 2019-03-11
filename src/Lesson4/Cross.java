package Lesson4;

import java.util.Random;
import java.util.Scanner;

public class Cross {
    private static int SIZE_X = 9;
    private static int SIZE_Y = 9;
    private static int COUNT_DOT_FOR_WIN = 5;

    private static char[][] field = new char[SIZE_Y][SIZE_X];

    private static char PLAYER_DOT = 'X';
    private static char AI_DOT = 'O';
    private static char EMPTY_DOT = '.';

    private static Scanner sc = new Scanner(System.in);
    private static Random rnd = new Random();
    private static int last_x, last_y;

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
        if(step.length()==3){
            int x = getStepX(step);
            int y = getStepY(step);
            if(x > 0 && y > 0) return isCellValid(y-1,x-1);
        }
        return false;
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
    // проверка хода на комбинацию из dot_count символов
    private static void calculateAIstep(int[] xy, int dot_count, char sym){
        int l = dot_count == COUNT_DOT_FOR_WIN ? 0 : 1;
        chek: for (int k = 0; k < 3; k++) {
            for (int j = l; j < SIZE_X-l; j++) {
                for (int i = l; i < SIZE_Y-l; i++) {
                    if (isCellValid(i, j)) {
                        field[i][j] = sym;
                        if (checkProbableWin(i, j, sym, dot_count, k)) {
                            xy[0] = i;
                            xy[1] = j;
                            field[i][j] = EMPTY_DOT;
                            break chek;
                        }
                        field[i][j] = EMPTY_DOT;
                    }
                }
            }
        }
    }
    // ход ПК
    private static void aiStep() {
        int[] xy = {-1,-1};
        // проверяем возможность победы AI
        calculateAIstep(xy, COUNT_DOT_FOR_WIN, AI_DOT);
        // проверяем возможность победы Игрока
        if (xy[0] < 0 || xy[1] < 0) calculateAIstep(xy, COUNT_DOT_FOR_WIN, PLAYER_DOT);
        if (xy[0] < 0 || xy[1] < 0)
        for (int i = COUNT_DOT_FOR_WIN-1; i > 1; i--) {
            // проверяем возможность вилки Игрока
            calculateAIstep(xy, i, PLAYER_DOT);
            if (!(xy[0] < 0 || xy[1] < 0)) break;
            // проверяем возможность вилки AI
            calculateAIstep(xy, i, AI_DOT);
            if (!(xy[0] < 0 || xy[1] < 0)) break;
        }
        if(xy[0] < 0 || xy[1] < 0)
        do {
            xy[1] = rnd.nextInt(SIZE_X);
            xy[0] = rnd.nextInt(SIZE_Y);
        } while (!isCellValid(xy[1],xy[0]));
        setSym(xy[0], xy[1], AI_DOT);
        last_x = xy[1];
        last_y = xy[0];
    }
    // если не встретили пустую ячейку это значит что всё поле заполнено
    private static boolean isDraw() {
        for (int i = 0; i < SIZE_Y; i++) for (int j = 0; j < SIZE_X; j++) if(field[i][j] == EMPTY_DOT) return false;
        return true;
    }
    // проверка горизонталей
    private static boolean checkHorizontal(int y, int x, char sym, int dot_count){
        int find_count;
        for (int i = x-dot_count+1; i < x+dot_count-1; i++) {
            find_count = 0;
            for (int j = 0; j < dot_count; j++) {
                if(i+j>-1 && i+j < SIZE_X){
                    if(field[y][i+j] == sym) find_count++;
                    else break;
                    if(find_count==dot_count) return true;
                } else break;
            }
        }
        return false;
    }
    // проверка вертикалей
    private static boolean checkVertical(int y, int x, char sym, int dot_count){
        int find_count;
        for (int j = y-dot_count+1; j < y+dot_count-1; j++) {
            find_count = 0;
            for (int i = 0; i < dot_count; i++) {
                if (i + j > -1 && i + j < SIZE_Y) {
                    if (field[i + j][x] == sym) find_count++;
                    else break;
                    if (find_count == dot_count) return true;
                } else break;
            }
        }
        return false;
    }
    // проверка диагоналей
    private static boolean checkDiagonal(int y, int x, char sym, int dot_count){
        int find_count;
        for (int i = y-dot_count+1, j = x-dot_count+1; i < y+dot_count-1 && j < x+dot_count-1; i++,j++) {
            find_count = 0;
            for (int k = 0; k < dot_count; k++) {
                //int xx = i+j;
                if (i+k > -1 && i+k < SIZE_Y && j+k > -1 && j+k < SIZE_X) {
                    int yy = i+k;
                    int xx = j+k;
                    if (field[yy][xx] == sym) find_count++;
                    else break;
                    if (find_count == dot_count) return true;
                } else break;
            }
        }
        for (int i = y-dot_count+1, j = x+dot_count-1; i < y+dot_count-1 && j > x-dot_count+1; i++,j--) {
            find_count = 0;
            for (int k = 0; k < dot_count; k++) {
                //int xx = i+j;
                if (i+k > -1 && i+k < SIZE_Y && j-k > -1 && j-k < SIZE_X) {
                    int yy = i+k;
                    int xx = j-k;
                    if (field[yy][xx] == sym) find_count++;
                    else break;
                    if (find_count == dot_count) return true;
                } else break;
            }
        }
        return false;
    }
    // проверка победы
    private static boolean checkWin(int y, int x, char sym, int dot_count) {
        return (checkDiagonal(y,x,sym,dot_count) ||checkHorizontal(y,x,sym,dot_count) || checkVertical(y,x,sym,dot_count));
    }
    private static boolean checkProbableWin(int y, int x, char sym, int dot_count, int mode) {
        switch (mode) {
            case 0: return checkDiagonal(y,x,sym,dot_count);
            case 1: return checkHorizontal(y,x,sym,dot_count);
            case 2: return checkVertical(y,x,sym,dot_count);
            default: return false;
        }
    }
    // main
    private static void cross() {
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
