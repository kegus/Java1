package Lesson7.Moba;

public class TestGame {
    public static void main(String[] args) {
        Hero h1 = new Warrior(100, "war1", 20);
        Hero h2 = new Warrior(100, "war2", 30);

        h1.info();
        h2.info();

        h1.hit(h2);

        h1.info();
        h2.info();
    }
}
