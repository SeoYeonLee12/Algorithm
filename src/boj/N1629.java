package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int mod = Integer.parseInt(st.nextToken());

        System.out.println(func1(a, b, mod));
    }

    private static long func1(long a, long b, long mod) {
        // base condition
        if (b == 1) {
            return a % mod;
        }

        // 재귀부
        long val = func1(a, b / 2, mod);
        val = val * val % mod;

        if (b % 2 == 0) {
            return val;
        }

        return val * a % mod;
    }
}
