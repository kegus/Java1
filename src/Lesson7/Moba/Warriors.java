package Lesson7.Moba;

import java.util.Random;

final class Warrior extends Hero {

    public Warrior(String name, String type, int health, int damage) {
        super(name, type, health, damage);
    }

}

final class Killer extends Hero {

    public Killer(String name, String type, int health, int damage) {
        super(name, type, health, damage);
    }

}

final class Healer extends Hero {
    public int healAvailbl;
    public Healer(String name, String type, int health, int damage, int healAvailbl) {
        super(name, type, health, damage);
        this.healAvailbl = healAvailbl;
    }
    public void healing(Hero h){
        if(h != this) causeHeal(h);
    };
    private void causeHeal(Hero h){
        if (!h.isKilled){
            h.health += healAvailbl;
        }
    }
}

public class Warriors {
    public String name;
    public int CNT_NUMEROF_WARIORS = 3;
    public Hero[] contentWarriors;
    private Random rnd = new Random();

    public Warriors(String name) {
        this.name = name;
        contentWarriors = new Hero[CNT_NUMEROF_WARIORS];
        generateWarriors();
    }
    private void generateWarriors(){
        for (int i = 0; i < CNT_NUMEROF_WARIORS; i++) {
            switch (i % 3) {
                case 0:
                    contentWarriors[i] = new Warrior(name+" Warrior","Warrior", 100, 30);
                    break;
                case 1:
                    contentWarriors[i] = new Killer(name+" Killer","Killer", 80, 50);
                    break;
                case 2:
                    contentWarriors[i] = new Healer(name+" Healer","Healer", 70, 10, 20);
                    break;
            }
        }
    }
    private int getRndAliveAlien(Warriors alien){
        int n = -1;
        if (!alien.isDead())
        do {
            n = rnd.nextInt(alien.CNT_NUMEROF_WARIORS);
            if(!alien.contentWarriors[n].isKilled) break;
        } while (true);
        return n;
    }
    private int getRndSelfFired(){
        int n = -1;
        if (!isDead())
            do {
                n = rnd.nextInt(CNT_NUMEROF_WARIORS);
                if(!contentWarriors[n].isFired && !contentWarriors[n].isKilled) break;
            } while (true);
        return n;
    }
    public void makeTurn(Warriors alien){
        if (!isDead() && existNotFired())
        {
            int i = getRndSelfFired();
            if (i >= 0) {
                if (!alien.isDead()){
                    int j = getRndAliveAlien(alien);
                    contentWarriors[i].hit(alien.contentWarriors[j]);
                    System.out.println(contentWarriors[i].name+" hit "+contentWarriors[i].damage+" to "+alien.contentWarriors[j].name+
                            " (HP: "+alien.contentWarriors[j].health+")" + (alien.contentWarriors[j].isKilled?" убит":""));
                    if (contentWarriors[i].type == "Healer") {
                        for (int k = 0; k < CNT_NUMEROF_WARIORS; k++) if (i != k)
                        if (!contentWarriors[k].isKilled){
                            ((Healer)contentWarriors[i]).healing(contentWarriors[k]);
                            System.out.println(contentWarriors[i].name+" heal "+((Healer)contentWarriors[i]).healAvailbl+" to "+contentWarriors[k].name+
                                    " (HP: "+contentWarriors[k].health+")");
                        }
                    }
                    contentWarriors[i].isFired = true;
                }
            } else {
                System.out.println("команда "+name+" makeTurn i < 0");
                for (int j = 0; j < CNT_NUMEROF_WARIORS; j++) contentWarriors[j].isFired = false;
            }
        } else {
            System.out.println("команда "+name+" все выстрелили");
            for (int j = 0; j < CNT_NUMEROF_WARIORS; j++) contentWarriors[j].isFired = false;
        }
    }
    public boolean existNotFired(){
        int n = 0;
        for (int i = 0; i < CNT_NUMEROF_WARIORS; i++) {
            if (contentWarriors[i].isKilled || contentWarriors[i].isFired) n++;
        }
        return n != CNT_NUMEROF_WARIORS;
    }
    public boolean isDead(){
        int n = 0;
        for (int i = 0; i < CNT_NUMEROF_WARIORS; i++) {
            if (contentWarriors[i].isKilled) n++;
        }
        return n == CNT_NUMEROF_WARIORS;
    }

}
