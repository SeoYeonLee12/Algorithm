package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class N7576 {
    static int N, M;
    static int[][] farm;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Deque<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        farm = new int[N][M];
        queue = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                farm[i][j] = Integer.parseInt(st.nextToken());
                if (farm[i][j] == 1) {
                    queue.add(new int[] {i, j});
                }
            }
        } // 농장의 토마토 상태 입력 받기+토마토가 있으면 큐에 넣기

        bfs();

        int maxDate=Integer.MIN_VALUE;
        // 결과 출력
        for (int i = 0; i < N; i++) {
            for(int j=0; j<M; j++){
                if(farm[i][j]==0){
                    bw.write("-1");
                    bw.flush();
                    return;
                }

                if(maxDate<farm[i][j]){
                    maxDate= farm[i][j];
                }
            }
        }

        bw.write(Integer.toString(maxDate-1));
        bw.flush();


    }

    // 최소 일수를 찾는 함수
    private static void bfs() {
        // 큐가 비어있지 않을 때 탐색함.
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int r = curr[0];
            int c = curr[1];

            // 4방향 탐색
            for (int i = 0; i < dr.length; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 좌표가 배열 범위를 넘어가면 다른 범위 탐색
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }

                if (farm[nr][nc] != 0) {
                    continue;
                }

                farm[nr][nc] = farm[r][c] + 1;
                queue.add(new int[] {nr, nc});

            }
        }
    } // bfs 함수
}
