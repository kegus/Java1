package Lesson5;

import java.util.Arrays;

public class Forester {
    private int[] counterArr = new int[20];
    private int[][] counterArr2 = new int[2][];
    private String[] workArray;
    private int[] intWorkArray;
    public Forester(String InputArr) {
        InputArr = InputArr.replaceAll("\n|\r\n", " ");
        InputArr = InputArr.replaceAll("  ", " ");
        workArray = InputArr.split(" ");
        intWorkArray = new int[workArray.length];
        for (int i = 0, j = 0; i < intWorkArray.length; i++) {
            try {
                intWorkArray[i] = Integer.parseInt(workArray[i]);
                counterArr[intWorkArray[i]-1]++;
            } catch (Exception e) {
                System.out.println("Forester constructor: Некорректный входной массив");
                return;
            }
        }
    }
    // простой подсчёт результата
    public void printResult(){
        System.out.println("Ответ1 :");
        for (int i = 0; i < counterArr.length; i++)if (counterArr[i] > 0) System.out.print(""+counterArr[i]+" ");
        System.out.println();
    }
    // усложнённый подсчёт результата через рекурсию
    // теперь нет ограничения на количество разнообразных чисел.
    // результат выводится в виде: val1 val2 ... valN
    //                             cnt1 cnt2 ... cntN
    public void printResult2(){
        counterArr2[0] = new int[0];
        counterArr2[1] = new int[0];
        counterArr2 = getCounterArray(intWorkArray);
        System.out.println("Ответ2 :");
        for (int i = 0; i < counterArr2[0].length; i++) System.out.print(""+counterArr2[0][i]+" ");
        System.out.println();
        for (int i = 0; i < counterArr2[1].length; i++) System.out.print(""+counterArr2[1][i]+" ");
        System.out.println();
    }
    private int[][] getCounterArray(int[] tmpArray){
        int tmp = tmpArray[0];
        int[] nextTmpArray = new int[tmpArray.length-1];
        boolean flagFound = false;

        for (int i = 0; i < counterArr2[0].length; i++) {
            if(counterArr2[0][i] == tmp) {
                counterArr2[1][i]++;
                flagFound = true;
                break;
            }
        }
        if (!flagFound) {
            int[] tmpValues = new int[counterArr2[0].length+1];
            int[] tmpCounter = new int[counterArr2[0].length+1];
            if(counterArr2[1].length > 0)
                System.arraycopy(counterArr2[0], 0, tmpValues, 0, tmpValues.length-1);
            tmpValues[tmpValues.length-1] = tmp;
            if(counterArr2[1].length > 0)
                System.arraycopy(counterArr2[1], 0, tmpCounter, 0, tmpCounter.length-1);
            tmpCounter[tmpCounter.length-1] = 1;
            counterArr2[0] = tmpValues;
            counterArr2[1] = tmpCounter;
        }
        System.arraycopy(tmpArray, 1, nextTmpArray, 0, nextTmpArray.length);
        if (nextTmpArray.length > 0) return getCounterArray(nextTmpArray);
        else return counterArr2;
    }
}
