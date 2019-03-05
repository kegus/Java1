package Lesson3;

import java.util.Random;
import java.util.Scanner;

public class Praktika {
    public static void main(String[] args) {
        guessNumber();
    }
    private static void guessNumber(){
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();
        int n_try = 3;
        int n_guess;
        int rnd_num = rnd.nextInt(10);
        while (n_try > 0){
            System.out.println("Угадате число от 0 до 9 (есть "+n_try+" попытки)");
            n_guess = sc.nextInt();
            if(rnd_num > n_guess)
                System.out.println("Загаданное число больше");
            else if(rnd_num > n_guess)
                System.out.println("Загаданное число меньше");
            else {
                System.out.println("Вы угадали");
                break;
            }
            n_try--;
        }
        if (n_try == 0)
            System.out.println("Вы проиграли");
        sc.close();
    }
}
