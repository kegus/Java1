package Lesson6;

public class ZooMarafon {
    public static void main(String[] args) {
        // Основноое дз
        Animals[] zoo = new Animals[4];
        zoo[0] = new Dog("dog1",400,.5,300);
        zoo[0].run(300);
        zoo[0].jump(200);
        zoo[0].swim(0.25);
        zoo[1] = new Dog("dog2",600,1.5,400);
        zoo[1].run(500);
        zoo[1].jump(10);
        zoo[1].swim(11);
        zoo[2] = new Cat("cat1",200,2.5,0);
        zoo[2].run(50);
        zoo[2].jump(5);
        zoo[2].swim(0);
        zoo[3] = new Cat("cat2",100,1.5,0);
        zoo[3].run(5000);
        zoo[3].jump(20);
        zoo[3].swim(10);
        /*Dog dog1 = new Dog("dog1");
        dog1.setMaxRun(400);
        dog1.setMaxJump(.5);
        dog1.setMaxSwim(300);
        Dog dog2 = new Dog("dog2");
        dog2.setMaxRun(600);
        dog2.setMaxJump(1.5);
        dog2.setMaxSwim(400);
        Cat cat1 = new Cat("cat1");
        cat1.setMaxRun(200);
        cat1.setMaxJump(2.5);
        cat1.setMaxSwim(0);
        Cat cat2 = new Cat("cat2");
        cat2.setMaxRun(100);
        cat2.setMaxJump(1.5);
        cat2.setMaxSwim(0);
        dog1.run(300);
        dog2.run(500);
        cat1.run(50);
        cat2.run(5000);
        dog1.jump(200);
        dog2.jump(10);
        cat1.jump(5);
        cat2.jump(20);
        dog1.swim(0.25);
        dog2.swim(11);
        cat1.swim(0);
        cat2.swim(10);*/

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