package Lesson5;

import java.util.Arrays;

public class Forester {
    private int[] counterArr = new int[20];;
    private String[] workArray;
    public Forester(String InputArr) {
        InputArr = InputArr.replaceAll("\n|\r\n", " ");
        InputArr = InputArr.replaceAll("  ", " ");
        this.workArray = InputArr.split(" ");
        int[] tempArr = new int[this.workArray.length];
        for (int i = 0, j = 0; i < tempArr.length; i++) {
            try {
                tempArr[i] = Integer.parseInt(this.workArray[i]);
                this.counterArr[tempArr[i]-1]++;
            } catch (Exception e) {
                System.out.println("Forester constructor: Некорректный входной массив");
                return;
            }
        }
    }
    public void printResult(){
        System.out.println("Ответ:");
        for (int i = 0; i < this.counterArr.length; i++)if (this.counterArr[i] > 0) System.out.print(""+this.counterArr[i]+" ");
        System.out.println();
    }
}
