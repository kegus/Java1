package Lesson3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Praktika {
    static Scanner sc = new Scanner(System.in);
    static Random rnd = new Random();
    public static void main(String[] args) {
        //п. 1
        guessNumber();
        //п. 2
        guessWord();
        //доп дз
        miniCalc();
        sc.close();
    }
    private static double calculate(String[] request, int nItem, double res){
        String item = request[nItem];
        if(nItem % 2 == 0){
            res = Double.parseDouble(item);
            if(nItem < request.length-1){
                if(request[nItem+1].equals("*")||request[nItem+1].equals("/")){
                    if (request[nItem + 1].equals("+"))
                        res += calculate(request, nItem + 2, res);
                    else if (request[nItem + 1].equals("-"))
                        res -= calculate(request, nItem + 2, res);
                    else if (request[nItem + 1].equals("*"))
                        res *= calculate(request, nItem + 2, res);
                    else
                        res /= calculate(request, nItem + 2, res);
                } else {
                    if (request[nItem + 1].equals("+"))
                        res += calculate(request, nItem + 2, res);
                    else if (request[nItem + 1].equals("-"))
                        res -= calculate(request, nItem + 2, res);
                    else if (request[nItem + 1].equals("*"))
                        res *= calculate(request, nItem + 2, res);
                    else
                        res /= calculate(request, nItem + 2, res);
                }
            } else {
                return res;
            }
        }
        return res;
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
            double result = calculate(request, 0, 0);
            // всё равно не понял как приоритет расставить... ((
            // теперь 2 * 2 + 2 работает неправильно
            // Да и вообще много неправильно.
            // Вообщем должен признать, что калькулятор мне сейчас не по зубам ((
            System.out.println(result);


            /*double[][] numbers = find_numbers(request);
            sort_numbers(numbers);  // не получается получить правильную сортировку... ((
                                    // поэтому 2 + 2 * 2 работает неправильно
            double result = numbers[0][0];
            for (int i = 1; i < numbers.length; i++) {
                result = getNextResult(result, numbers[i][0], numbers[i - 1][1]);
            }*/
        }
    }
    private static double[][] find_numbers(String[] request){
        int count_operations = 0;
        int last_operation = 1; // + для 1-го числа
        double[][] res = new double[request.length / 2 + 1][3];
        for (String item: request) {
            if(!(item.equals("+")||item.equals("-")||item.equals("*")||item.equals("/"))) {
                res[count_operations][0] = Double.parseDouble(item);
                res[count_operations][1] = last_operation;
            } else {
                if(item.equals("+")) last_operation = 1;
                else if(item.equals("-")) last_operation = 2;
                else if(item.equals("*")) last_operation = 3;
                else last_operation = 4;
                res[count_operations][2] = last_operation;
                count_operations++;
            }
        }
        res[count_operations][2] = 1;
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
        double temp_numbers;
        double temp_operation;
        double temp_last_operation;
        for (int i = 0; i < numbers.length; i++)
            for (int j = i + 1; j < numbers.length-1; j++)
                if ((numbers[i][2] == 1 || numbers[i][2] == 2) && (numbers[j][2] == 3 || numbers[j][2] == 4)) {
                    temp_numbers = numbers[i][0];
                    temp_last_operation = numbers[i][1];
                    temp_operation = numbers[i][2];
                    numbers[i][0] = numbers[j][0];
                    numbers[i][1] = numbers[j][1];
                    numbers[i][2] = numbers[j][2];
                    numbers[j][0] = temp_numbers;
                    numbers[j][1] = temp_last_operation;
                    numbers[j][2] = temp_operation;

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
