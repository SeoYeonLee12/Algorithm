package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N15649 {

    static int N, M;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[10];
        visited = new boolean[10];

        backTracking(0);
    }

    private static void backTracking(int depth) {

        // base condition
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= N; i++) {
            // 유망성 검사
            if (!visited[i]) {
                // 채우기
                arr[depth] = i;
                visited[i] = true;
                backTracking(depth + 1);
                visited[i] = false;
            }
        }

    }
}
