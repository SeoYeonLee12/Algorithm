package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1074 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        System.out.println(func(n, r, c));
    }

    // 함수 정의
    // 2^n*^n 배열에서 (r, c)를 방문하는 순서를 반환하는 함수
    static int func(int n, int r, int c) {
        // base condition
        if (n == 0) {
            return 0;
        }

        int half = (int) Math.pow(2, n - 1);

        // 1번 사각형
        if (r < half && c < half) {
            return func(n - 1, r, c);
        }
        // 2번 사각형 : 2번 사각형 부터는 n-1 개의 사각형을 지나왔으믄로 half*half 곱함
        if (r < half && c >= half) {
            return half * half + func(n - 1, r, c - half);
        }
        // 3번 사각형
        if (r >= half && c < half) {
            return 2 * half * half + func(n - 1, r - half, c);
        }
        return 3 * half * half + func(n - 1, r - half, c - half);
    }
}
