package Lesson6;

public class ZooMarafon {
    public static void main(String[] args) {
        // Основноое дз
        Dog dog1 = new Dog("dog1");
        Dog dog2 = new Dog("dog2");
        Cat cat1 = new Cat("cat1");
        Cat cat2 = new Cat("cat2");
        dog1.run(100);
        dog2.run(1000);
        cat1.run(50);
        cat2.run(5000);
        dog1.jump(200);
        dog2.jump(10);
        cat1.jump(5);
        cat2.jump(20);
        dog1.swim(0.25);
        dog2.swim(11);
        cat1.swim(0);
        cat2.swim(10);

        // Доп дз
        String s = "Предложение  один     Теперь предложение    два   Предложение   три";
        StringChecker sc = new StringChecker(s);

        System.out.println("До свидания");
    }
}
// Приведение строки к нормальному виду
class StringChecker {
    public StringChecker(String inputStr) {
        StringBuilder sb = new StringBuilder(inputStr.replaceAll(" +", " "));
        for (int i = 1; i < sb.length(); i++) if(sb.charAt(i) >= 'А' && sb.charAt(i) <= 'Я') sb.insert(++i-2, '.');
        sb.insert(sb.length(), '.');
        System.out.println(sb.toString());
    }
}