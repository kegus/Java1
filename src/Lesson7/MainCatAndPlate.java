package Lesson7;

public class MainCatAndPlate {
    public static void main(String[] args) {
        String yes = "да";
        String no = "нет";

        Cat[] cats = {new Cat("Barsik", 5),
                new Cat("Jorick", 150),
                new Cat("Vaska", 15)};

        Plate plate = new Plate(100);

        for (Cat c: cats) {
            c.eat(plate);
            System.out.println(c.getName() + " сыт? " + (c.isFull() == true ? yes : no));
        }

        plate.info();
        plate.addFood(20);
        plate.info();
    }
}
