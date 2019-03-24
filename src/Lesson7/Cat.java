package Lesson7;

public class Cat {
    private String name;
    private int appetite;

    private boolean isFull;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        isFull = false;
    }

    public void eat(Plate p) {
        if(p.getFood() >= appetite) {
            p.decreaseFood(appetite);
            isFull = true;
            System.out.println(name +  " Кот покушал");
        } else {
            System.out.println(name +  " не Кот покушал");
            isFull = false;
        }
    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }

    public boolean isFull() {
        return isFull;
    }
}
