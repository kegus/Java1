package Lesson5;

import java.time.Year;
import java.util.Calendar;

public class Person {
    private static Calendar now = Calendar.getInstance();   // Gets the current date and time
    private static int current_year = now.get(Calendar.YEAR);       // The current year

    private String FIO;
    private String doljnost;
    private String email;
    private String phone;
    private int salary;
    private int yearOfBorn;

    public Person(String FIO, String doljnost, String email, String phone, int salary, int age) {
        this.FIO = FIO;
        this.doljnost = doljnost;
        this.email = email;
        this.phone = phone;
        this.setSalary(salary);
        this.setAge(age);
    }
    public void setSalary(int salary) {
        if(salary < 0) System.out.println("Некорректная зарплата.");
        else this.salary = salary;
    }
    public int getYearOfBorn() {
        return this.yearOfBorn;
    }
    public void setYearOfBorn(int yearOfBorn) {
        this.yearOfBorn = yearOfBorn;
    }
    public int getAge() {
        return current_year - this.yearOfBorn;
    }
    public void setAge(int age) {
        if (age < 18) System.out.println("Некорректный возраст");
        else this.yearOfBorn = current_year - age;
    }
    public void printInfo(){
        System.out.printf("%s %s %s %s %d %d", this.FIO, this.doljnost, this.email, this.phone, this.salary, this.getAge());
    }
}
