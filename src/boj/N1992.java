package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N1992 {

    static int N;
    static int[][] video = new int[64][64];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                video[i][j] = line.charAt(j) - '0';
            }
        }

        recur(N, 0, 0);
        System.out.println(sb.toString());

    }

    // 재귀함수
    private static void recur(int n, int r, int c) {
        // base condition
        if (n == 1) {
            sb.append(video[r][c]);
            return;
        }

        boolean zero = true, one = true;
        // 같을 떄
        for (int i = r; i < r + n; i++) {
            for (int j = c; j < c + n; j++) {
                if (video[i][j] == 1) {
                    zero = false;
                }
                if (video[i][j] == 0) {
                    one = false;
                }
            }
        }

        // zero가 true이면
        if (zero) {
            sb.append("0");
        } else if (one) {
            sb.append("1");
        }
        // 다른 수 일 경우
        else {
            sb.append("(");
            // 왼쪽 위 탐색
            recur(n / 2, r, c);
            // 오른쪽 위 탐색
            recur(n / 2, r, c + n / 2);
            // 왼쪽 아래
            recur(n / 2, r + n / 2, c);
            // 오른쪽 아래
            recur(n / 2, r + n / 2, c + n / 2);
            sb.append(")");
        }
        return;

    }


}
