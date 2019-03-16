package Lesson6;

// макет класса
abstract class PreAnimals {
    protected String name;
    abstract void run(double actLimit);
    abstract void jump(double actLimit);
    abstract void swim(double actLimit);
    public String getName(){
        return name;
    }
}
// родительский класс
public class Animals extends PreAnimals {
    protected double maxRun;
    protected double maxJump;
    protected double maxSwim;

    public void setMaxRun(double maxRun) {
        this.maxRun = maxRun;
    }
    public void setMaxJump(double maxJump) {
        this.maxJump = maxJump;
    }
    public void setMaxSwim(double maxSwim) {
        this.maxSwim = maxSwim;
    }

    public Animals(String name) {
        this.name = name;
    }
    @Override
    void run(double actLimit) {
        if(actLimit > maxRun) System.out.println(name+".run("+actLimit+");-> результат: run: false");
        else System.out.println(name+".run("+actLimit+");-> результат: run: true");
    }
    @Override
    void jump(double actLimit) {
        if(actLimit > maxJump) System.out.println(name+".jump("+actLimit+");-> результат: jump: false");
        else System.out.println(name+".jump("+actLimit+");-> результат: jump: true");
    }
    @Override
    void swim(double actLimit) {
        if(actLimit > maxSwim) System.out.println(name+".swim("+actLimit+");-> результат: swim: false");
        else System.out.println(name+".swim("+actLimit+");-> результат: swim: true");
    }
}
// потомки
class Dog extends Animals {
    public Dog(String name) {
        super(name);
        maxRun = 500;
        maxJump = .5;
        maxSwim = 10;
    }
}
class Cat extends Animals {
    public Cat(String name) {
        super(name);
        maxRun = 200;
        maxJump = 2;
        maxSwim = 0;
    }
}
