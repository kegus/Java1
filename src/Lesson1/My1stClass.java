package Lesson1;

public class My1stClass {
    public static void main(String[] args) {
        System.out.println("п.1 Ok");
        boolean v_bool = true;
        byte v_byte = 10;
        short v_short = 1000;
        int v_int = 1000000;
        long v_long = 1000000000000000L;
        float v_float = 10.0255f;
        double v_double = 10.6456498;
        char v_char1 = 'k';
        char v_char2 = 45;
        String v_str = "Hello";
        System.out.println("п.2 Ok");
//      п.3
        System.out.println("п.3 "+calcFormula(12.5,78.45,123.789,45.1235));
//      п.4
        System.out.println("п.4 "+checkInterval(12,8,20,40));
//      п.5
        System.out.print("п.5 ");
        checkSign(-5);
//      п.6
        System.out.println("п.6 "+bCheckSign(-5));
//      п.7
        System.out.print("п.7 ");
        printMessage("Константин");
//      п.8
        System.out.print("п.8 ");
        checkYear(2100);
    }

    private static double calcFormula(double a, double b, double c, double d) {
        return d == 0 ? 0 : a * (b + (c / d));
    }
    private static boolean checkInterval(double a, double b, double c, double d) {
        return a + b >= c &&  a + b <= d;
    }
    private static void checkSign(int a){
        System.out.println(a<0?"отрицательное":"положительное");
    }
    private static boolean bCheckSign(int a){
        return a<0;
    }
    private static void printMessage(String name){
        System.out.println("Привет, "+name);
    }
    private static void checkYear(int year){
        boolean res;
        if(year < 0 || year > 9999){
            System.out.println("Год вне допустимых пределов");
            return;
        }
        res = year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
        System.out.println(res ? "високосный" : "не високосный");
    }
}
