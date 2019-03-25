package Lesson7.Moba;

import javax.swing.*;

public class BatleField {
    private Warriors[] warriors;

    public BatleField(int countWarriors) {
        if (countWarriors < 1 || countWarriors > 2) {
            System.out.println("Недопустимое количество команд");
            return;
        }
        warriors = new Warriors[countWarriors];
        for (int i = 0; i < countWarriors; i++) {
            warriors[i] = new Warriors("Команда "+i);
        }
    }

    public BatleField(Warriors[] warriors) {
        this.warriors = warriors;
    }
    public void fightGraph(JTextArea resTextArea){
        while (true){
            int n = 0;
            for (int i = 0; i < warriors.length; i++) {
                if (warriors[i].isDead()){
                    resTextArea.append(warriors[i].name + " мертва. \n");
                    n++;
                } else {
                    for (int j = 0; j < warriors.length; j++) if (j != i) {
                        if (!warriors[i].existNotFired()) {
                            resTextArea.append("команда " + warriors[i].name + " все выстрелили \n");
                            for (int k = 0; k < warriors[i].CNT_NUMEROF_WARIORS; k++)
                                warriors[i].contentWarriors[k].isFired = false;
                        }
                        resTextArea.append(warriors[i].name + " makeTurn to " + warriors[j].name + "\n");
                        warriors[i].makeTurnGaph(warriors[j], resTextArea);
                    }
                }
            }
            if (warriors.length - n < 2){
                resTextArea.append("Игра окончена. \n");
                for (int i = 0; i < warriors.length; i++) {
                    for (int j = 0; j < warriors[i].CNT_NUMEROF_WARIORS; j++) {
                        warriors[i].contentWarriors[j].infoGraph(resTextArea);
                    }
                }
                break;
            }
        }
    }
    public void fight(){
        while (true){
            int n = 0;
            for (int i = 0; i < warriors.length; i++) {
                if (warriors[i].isDead()){
                    System.out.println(warriors[i].name + " мертва.");
                    n++;
                } else {
                    for (int j = 0; j < warriors.length; j++) if (j != i) {
                        if (!warriors[i].existNotFired()) {
                            System.out.println("команда " + warriors[i].name + " все выстрелили");
                            for (int k = 0; k < warriors[i].CNT_NUMEROF_WARIORS; k++)
                                warriors[i].contentWarriors[k].isFired = false;
                        }
                        System.out.println(warriors[i].name + " makeTurn to " + warriors[j].name);
                        warriors[i].makeTurn(warriors[j]);
                    }
                }
            }
            if (warriors.length - n < 2){
                System.out.println("Игра окончена.");
                for (int i = 0; i < warriors.length; i++) {
                    for (int j = 0; j < warriors[i].CNT_NUMEROF_WARIORS; j++) {
                        warriors[i].contentWarriors[j].info();
                    }
                }
                break;
            }
        }
    }
}
