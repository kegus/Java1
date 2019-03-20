package Lesson7.Moba;

public  class Hero {
    protected String name;
    protected String type;
    protected int health;
    protected int damage;
    protected boolean isKilled;
    protected int countKill;
    public boolean isFired;

    public Hero(String name, String type, int health, int damage) {
        this.name = name;
        this.type = type;
        this.health = health;
        this.damage = damage;
    }

    public void hit(Hero h){
        if(h != this) h.causeDamage(damage);
        if (h.isKilled) countKill++;
    };

    void causeDamage(int damage) {
        health -= damage;
        if (health <= 0) isKilled = true;
    }

    void info() {
        System.out.println(name + " (" + type + ") HP: " + health + " D:" + damage+ " K:" + countKill + " Rip: " + isKilled);
    }
}
