package Lesson2;

import java.util.Arrays;
import java.util.Random;

public class mainHomeWork {
    public static void main(String[] args) {
//      п.1
        int[] arr1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        changeArr01(arr1);
//      п.2
        int[] arr2 = new int[8];
        fillArr(arr2);
//      п.3
        int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        checkArrL6(arr3);
//      п.4
        int[][] arr4 = new int[7][7];
        fill2dArr(arr4);
//      п.5
        int[] arr5 = {4, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 21};
        findMinMax(arr5);
//      п.6
        int[] arr6 = {2, 2, 2, 1, 2, 2, 10, 1};
        System.out.println(haveEqualsParts(arr6));
//      п.7 v.0  пошаговый единичный сдвиг в цикле делать не стал из-за большой ресурсозатратности алгоритма
//      п.7 v.1  Массив создаётся внутри метода
        int shift = -8;
        int[] arr7 = {1, 2, 3, 4, 5, 6, 7, 8};
        arr7 = moveArr(arr7, shift);
        System.out.println(Arrays.toString(arr7));
//      п.7 v.2 используется 2-х мерный массив размером [2][8]
        int[][] arr7_1 = {{1, 2, 3, 4, 5, 6, 7, 8},{0, 0, 0, 0, 0, 0, 0, 0}};
        moveArr_1(arr7_1, shift);
        System.out.println(Arrays.toString(arr7_1[1]));
//        arr7_1[0][0] = 10;
//        arr7_1[1][1] = 10;
//        System.out.println(Arrays.toString(arr7_1[0]));
//        System.out.println(Arrays.toString(arr7_1[1]));

//      п.7 v.3 наиболее оптимальный
        int[] arr7_2 = {1, 2, 3, 4, 5, 6, 7, 8};
        moveArr_2(arr7_2, shift);
        System.out.println(Arrays.toString(arr7_2));
//      доп. ДЗ п.1
        int start = 1;
        int end = 99;
        printArrNechet(start, end);
//      доп. ДЗ п.2
        checkRndArr(15);
//      доп. ДЗ п.3
        calcMidleSum2Arr(5);
    }
    private static void changeArr01(int[] arr){
        for (int i = 0; i < arr.length; i++) arr[i] = Math.abs(arr[i]-1);
        System.out.println(Arrays.toString(arr));
    }
    private static void fillArr(int[] arr){
        for (int i = 0; i < arr.length; i++) arr[i] = 3*i;
        System.out.println(Arrays.toString(arr));
    }
    private static void checkArrL6(int[] arr){
        for (int i = 0; i < arr.length; i++) if (arr[i] < 6) arr[i] *= 2;
        System.out.println(Arrays.toString(arr));
    }
    private static void fill2dArr(int[][] arr){
        for (int i = 0; i < arr.length; i++){
            arr[i][i] = 1;
            arr[arr[0].length-i-1][i] = 1;
        }
        for (int[] item : arr) System.out.println(Arrays.toString(item));
    }
    private static void findMinMax(int[] arr){
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++){
            if (arr[i] < min) min = arr[i];
            if (arr[i] > max) max = arr[i];
        }
        System.out.println("min = "+min+"  max = "+max);
    }
    private static int getLeftPart(int[] arr, int n){
        if (n < 0 || n > arr.length-1) return 0;
        int res = 0;
        for (int i = 0; i < n; i++) res += arr[i];
        return res;
    }
    private static int getRightPart(int[] arr, int n){
        if (n < 0 || n > arr.length-1) return 0;
        int res = 0;
        for (int i = n; i < arr.length; i++) res += arr[i];
        return res;
    }
    private static boolean haveEqualsParts(int[] arr){
        for (int i = 0; i < arr.length; i++)
            if (getLeftPart(arr, i) == getRightPart(arr, i)) return true;
        return false;
    }
    private static int[] moveArr(int[] arr, int n){
        int i = -n % arr.length; //выравниваю сдвиг и знак
        if (n == 0) return arr;
        int[] a_arr = new int[arr.length];
        if (i>0)
            for (int j = 0; j < arr.length; j++)
                if (j+i < arr.length)
                    a_arr[j] = arr[j+i];
                else
                    a_arr[j] = arr[j+i-arr.length];
        else
            for (int j = 0; j < arr.length; j++)
                if (j+i < 0)
                    a_arr[j] = arr[arr.length+j+i];
                else
                    a_arr[j] = arr[j+i];
        return a_arr;
    }
    private static void moveArr_1(int[][] arr, int n){
        int i = -n % arr[0].length; //выравниваю сдвиг и знак
        if (i == 0){
            arr[1] = arr[0];
            return;
        }
        if (i>0)
            for (int j = 0; j < arr[0].length; j++)
                if (j+i < arr[0].length)
                    arr[1][j] = arr[0][j+i];
                else
                    arr[1][j] = arr[0][j+i-arr[0].length];
        else
            for (int j = 0; j < arr[0].length; j++)
                if (j+i < 0)
                    arr[1][j] = arr[0][arr[0].length+j+i];
                else
                    arr[1][j] = arr[0][j+i];
    }
    private static int getDivider(int a, int b){
        if (b == 0)return Math.abs(a);
        return getDivider(b, a % b);
    }
    private static void moveArr_2(int[] arr, int shift){
        int cIndex, mIndex, val;
        int n = -shift % arr.length; //выравниваю сдвиг и знак
        if (n == 0) return;

        for (int i = 0; i < getDivider(n, arr.length); i++) {
            val = arr[i];
            cIndex = i;
            if (n > 0)
                while (true) {
                    mIndex = cIndex + n;
                    if (mIndex >= arr.length)
                        mIndex = mIndex - arr.length;
                    if (mIndex == i)
                        break;
                    arr[cIndex] = arr[mIndex];
                    cIndex = mIndex;
                }
            else
                while (true) {
                    mIndex = cIndex + n;
                    if (mIndex < 0)
                        mIndex = arr.length + mIndex;
                    if (mIndex == i)
                        break;
                    arr[cIndex] = arr[mIndex];
                    cIndex = mIndex;
                }
            arr[cIndex] = val;
        }
    }
    private static void printArrNechet(int start, int end){
        int count = 0;
        int j = 0;
        for (int i = start; i <= end; i++)
            if (i % 2 != 0) count++;
        int[] arr = new int[count];
        for (int i = start; i <= end; i++)
            if (i % 2 != 0) arr[j++] = i;
        for (int i = 0; i < count; i++)
            System.out.print(" "+arr[i]);
        System.out.println();
    }
    private static void checkRndArr(int count){
        int[] arr = new int[count];
        Random rnd = new Random();
        int count_chet = 0;
        for (int i = 0; i < count; i++) {
            arr[i] = rnd.nextInt(10);
            if (arr[i] % 2 == 0) count_chet++;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("Чётных чисел "+count_chet);
    }
    private static void calcMidleSum2Arr(int count){
        int[] arr1 = new int[count];
        int[] arr2 = new int[count];
        int sum1 = 0;
        int sum2 = 0;
        Random rnd = new Random();
        for (int i = 0; i < count; i++) {
            arr1[i] = rnd.nextInt(6);
            while(arr1[i]==0) arr1[i] = rnd.nextInt(6);
            arr2[i] = rnd.nextInt(6);
            while(arr2[i]==0) arr2[i] = rnd.nextInt(6);
            sum1 += arr1[i];
            sum2 += arr2[i];
        }
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        if (sum1 > sum2)
            System.out.println("Ср.Ариф.1 > Ср.Ариф.2");
        else if (sum1 < sum2)
            System.out.println("Ср.Ариф.1 < Ср.Ариф.2");
        else
            System.out.println("Ср.Ариф.1 = Ср.Ариф.2");
    }
}