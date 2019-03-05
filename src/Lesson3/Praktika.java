package Lesson3;

import java.util.Random;
import java.util.Scanner;

public class Praktika {
    static Scanner sc = new Scanner(System.in);
    static Random rnd = new Random();
    public static void main(String[] args) {
        //guessNumber();
        //guessWord();
        miniCalc();
        sc.close();
    }
    private static double[][] find_numbers(String[] request){
        int count_operations = 0;
        double[][] res = new double[request.length / 2 + 1][2];
        for (String item: request) {
            if(!(item.equals("+")||item.equals("-")||item.equals("*")||item.equals("/")))
                res[count_operations][0] = Integer.parseInt(item);
            else {
                if(item.equals("+")) res[count_operations][1] = 1;
                else if(item.equals("-")) res[count_operations][1] = 2;
                else if(item.equals("*")) res[count_operations][1] = 3;
                else res[count_operations][1] = 4;
                count_operations++;
            }
        }
        return res;
    }
    private static double getNextResult(double left, double right, double operations){
        double res;
        switch ((int)operations) {
            case 1:
                res = left + right;
                break;
            case 2:
                res = left - right;
                break;
            case 3:
                res = left * right;
                break;
            case 4:
                res = left / right;
                break;
            default:
                res = left;
        }
        return res;
    }
    private static void sort_numbers(double[][] numbers) {

    }
    private static void miniCalc() {
        while (true) {
            System.out.println("Введите запрос");
            String[] request = sc.nextLine().split(" ");
            if(request.length < 3 || request.length % 2 == 0){
                if(request.length > 0 && request[0].equals("exit")) {
                    System.out.println("До свидания");
                    break;
                } else {
                    System.out.println("Ошибка в запросе");
                    continue;
                }
            }
            double[][] numbers = find_numbers(request);
            sort_numbers(numbers);
            double result = numbers[0][0];
            for (int i = 1; i < numbers.length; i++) {
                result = getNextResult(result, numbers[i][0], numbers[i - 1][1]);
            }
            System.out.println(result);
        }
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
    private static void guessWord() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        String secret_word = words[rnd.nextInt(words.length)];
        char[] arr_secret_word = secret_word.toCharArray();
        char[] arr_guesed_char = new char[15];
        for (int i = 0; i < arr_guesed_char.length; i++) arr_guesed_char[i] = '*';


        System.out.println("Угадайте слово");
        String guess_word;
        //System.out.println(secret_word);
        while (true) {
            guess_word = sc.nextLine();
            if (guess_word.equals(secret_word)) {
                System.out.println("Вы угадали");
                return;
            } else {
                char[] arr_guess_word = guess_word.toCharArray();
                int min_length = arr_guess_word.length > arr_secret_word.length ? arr_secret_word.length : arr_guess_word.length;
                for (int i = 0; i < min_length; i++)
                    if (arr_guess_word[i] == arr_secret_word[i]) arr_guesed_char[i] = arr_guess_word[i];
                System.out.println(String.valueOf(arr_guesed_char));
            }

        }
    }
}
