package Lesson3;

import java.util.Random;
import java.util.Scanner;

public class Praktika {
    static Scanner sc = new Scanner(System.in);
    static Random rnd = new Random();
    public static void main(String[] args) {
        //guessNumber();
        guessWord();
        sc.close();
    }
    private static void guessNumber(){
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
    }
    private static void guessWord(){
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        String secret_word = words[rnd.nextInt(words.length)];
        System.out.println("Угадайте слово");
        String guess_word;
        System.out.println(secret_word);
        guess_word = sc.nextLine();
        if(guess_word.equals(secret_word))
            System.out.println("Вы угадали");
    }
}
