package BarkingDog.recursion;

public class Mod_example {
    public static void main(String[] args) {

//        System.out.println(fun1(6, 100, 5));

//        System.out.println(Math.pow(6, 100));

        System.out.println(func2(6, 100, 5));
    }


    // a^b mod m
    static int fun1(int a, int b, int m) {
        int val = 1;
        while (b-- > 0) {
            val *= a;
        }
        return val % m;
    }


    static long func2(long a, long b, long m) {
        long val = 1;

        while (b-- > 0) {
            val = val * a % m;
            System.out.print("func2: "+ val+" ");
        }
        return val;
    }
}
