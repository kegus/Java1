package Lesson7.Moba;

public class Warrior extends Hero {

    public Warrior(int health, String type, int damage) {
        super(health, type, damage);
    }

    @Override
    void hit(Hero h) {
        if(h != this) {
            h.causeDamage(damage);
        }
    }

    @Override
    void healing(Hero h) {
        System.out.println("Войны не умеют лечить!");
    }
}
