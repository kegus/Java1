package Lesson6;

// макет класса
abstract class PreAnimals {
    protected String name;
    abstract void run(double actLimit);
    abstract void jump(double actLimit);
    abstract void swim(double actLimit);
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

    public Animals(String name, double maxRun, double maxJump, double maxSwim) {
        this.name = name;
        setMaxRun(maxRun);
        setMaxJump(maxJump);
        setMaxSwim(maxSwim);
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
    public Dog(String name, double maxRun, double maxJump, double maxSwim) {
        super(name, maxRun, maxJump, maxSwim);
    }
}
class Cat extends Animals {
    public Cat(String name, double maxRun, double maxJump, double maxSwim) {
        super(name, maxRun, maxJump, maxSwim);
    }
}
