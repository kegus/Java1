package Lesson5;

import java.util.Scanner;

public class ContactList {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        // Основное дз
        Person[] persArray = new Person[5];
        persArray[0] = new Person("test1 test2 test3","test4","test5","test6", 2000000, 35);
        persArray[1] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 40);
        persArray[2] = new Person("Petrov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 40000, 45);
        persArray[3] = new Person("Sidorov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 50000, 57);
        persArray[4] = new Person("Smirnov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 70000, 30);
        for (Person pers:persArray) if (pers.getAge() > 40) pers.printInfo();

        // Доп дз
        while (true) {
            System.out.println("Введите массив для анализа или Q для выхода");
            String inputString = sc.nextLine();
            if(inputString.equalsIgnoreCase("q")) break;
            Forester forester = new Forester(inputString);
            // метод 1
            forester.printResult();
            // метод 2 (рекурсия)
            forester.printResult2();
        }
        System.out.println("До свидания");
        sc.close();
    }
}
