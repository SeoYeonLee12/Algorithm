package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2630 {
    static int N;
    static int[][] paper = new int[200][200];
    static int white;
    static int blue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0, 0, N);
        System.out.println(white);
        System.out.println(blue);

    }

    // 재귀 함수
    private static void recur(int r, int c, int n) {
        // base condition
        if (check(r, c, n)) {
            if (paper[r][c] == 0) {
                white++;
            } else {
                blue++;
            }
            return;
        }

        // 재귀부
        n /= 2;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                recur(r + i * n, c + j * n, n);
            }
        }

    }

    // 영역이 같은 크기인지 확인
    private static boolean check(int r, int c, int n) {

        for (int i = r; i < r + n; i++) {
            for (int j = c; j < c + n; j++) {
                if (paper[r][c] != paper[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }


}
